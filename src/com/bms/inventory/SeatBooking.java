package com.bms.inventory;

import com.bms.inventory.exception.BookingAlreadyCancelledException;
import com.bms.inventory.exception.SeatUnavailable;
import com.bms.payment.Payable;
import com.bms.payment.Payment;
import com.bms.payment.PaymentStatus;
import com.bms.payment.exception.PaymentFailedException;
import com.bms.repository.BookingRepository;
import com.bms.user.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SeatBooking {
    private final Payable pg;
    private final BookingRepository bookingRepository;
    static Map<Show, Object> showLocks = new ConcurrentHashMap<>();
    public SeatBooking(Payable pg, BookingRepository bookingRepository) {
        this.pg = pg;
        this.bookingRepository = bookingRepository;
    }

    public static void selectSeats(Show show, int[] seatIds) throws SeatUnavailable {
        if(!show.seatsAvailable(seatIds))
            throw new SeatUnavailable();
    }

    public Booking bookSeats(User user, Show show, int[] seatIds) throws SeatUnavailable, PaymentFailedException {
        Booking latestBooking = null;
        synchronized (showLocks.computeIfAbsent(show, k -> new Object())) {
            if (!show.seatsAvailable(seatIds))
                throw new SeatUnavailable();

            show.bookSeats(seatIds);
        }

        try {
            Payment paymentDetails = pg.makePayment(user, show.getPrice() * seatIds.length);
            if (paymentDetails.getStatus() == PaymentStatus.SUCCESS) {
                latestBooking = new Booking(user, show, seatIds, paymentDetails);
                bookingRepository.addBooking(latestBooking);
            }
        } catch (Exception ex) {
            show.unbookSeats(seatIds);
            throw ex;
        }

        return latestBooking;
    }

    public void cancelBooking(Booking booking) throws BookingAlreadyCancelledException {
        if(booking == null)
            return;

        if(!booking.getIsValid())
            throw new BookingAlreadyCancelledException();

        Show show = booking.getShow();
        User currentUser = booking.getUser();
        Payment paymentDetails = booking.getPayment();

        booking.getLock().lock();
        try {
            if (booking.getIsValid()) {
                pg.refundPayment(currentUser, paymentDetails.getAmount());
                booking.invalidateBooking();
                show.unbookSeats(booking.getBookedSeatIds());
                bookingRepository.removeBooking(booking);
            } else {
                throw new BookingAlreadyCancelledException();
            }
        } finally {
            booking.getLock().unlock();
        }
    }
}
