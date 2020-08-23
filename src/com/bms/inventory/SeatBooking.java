package com.bms.inventory;

import com.bms.inventory.exception.SeatUnavailable;
import com.bms.payment.Payable;
import com.bms.payment.Payment;
import com.bms.payment.PaymentStatus;
import com.bms.payment.exception.PaymentFailedException;
import com.bms.repository.BookingRepository;
import com.bms.user.User;

public class SeatBooking {
    private final Payable pg;
    private final BookingRepository bookingRepository;
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
        if(!show.seatsAvailable(seatIds))
            throw new SeatUnavailable();

        Payment paymentDetails = pg.makePayment(user, show.getPrice());
        if(paymentDetails.getStatus() == PaymentStatus.SUCCESS) {
            show.bookSeats(seatIds);
            latestBooking = new Booking(user, show, seatIds, paymentDetails);
            bookingRepository.addBooking(latestBooking);
        }

        return latestBooking;
    }

    public void cancelBooking(Booking booking) {
        if(booking == null)
            return;

        Show show = booking.getShow();
        User currentUser = booking.getUser();
        Payment paymentDetails = booking.getPayment();
        pg.refundPayment(currentUser, paymentDetails.getAmount());
        show.unbookSeats(booking.getBookedSeatIds());
        bookingRepository.removeBooking(booking);
    }
}
