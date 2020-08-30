package com.showbooking.bookings;

import com.showbooking.payments.Payable;
import com.showbooking.events.Show;
import com.showbooking.payments.Payment;
import com.showbooking.payments.PaymentStatus;
import com.showbooking.payments.exception.PaymentFailedException;
import com.showbooking.repository.BookingRepository;
import com.showbooking.repository.EventRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    public static Long bookEvent(Payable paymentService, BookingRepository bookingRepository, EventRepository eventRepository,
                                 Long userId, Long eventId, Long screenId, List<Long> seatIds) {
        List<Show> availableShows = eventRepository.fetchAllShows(eventId).stream().filter(
                show -> show.getScreenId().equals(screenId)).collect(Collectors.toList());
        Show selectedShow = Collections.min(availableShows, Comparator.comparing(Show::getId));

        try {
            if (!selectedShow.bookSeats(seatIds)) {
                return null;
            }

            Payment payment = paymentService.makePayment(userId, selectedShow.getPrice());
            if(payment.getStatus().equals(PaymentStatus.SUCCESS)) {
                Booking booking = bookingRepository.addBooking(new Booking(userId, selectedShow, payment, seatIds));
                return booking.getId();
            } else {
                throw new PaymentFailedException();
            }
        } catch (PaymentFailedException exc) {
            selectedShow.unBookSeats(seatIds);
            return null;
        }
    }

    public static Boolean cancelBooking(Payable paymentService, BookingRepository bookingRepository, Long bookingId) {
        Booking booking = bookingRepository.fetchBooking(bookingId);
        List<Long> bookedSeats = booking.getSeatIds();
        Show bookedShow = booking.getShow();
        if (booking.getIsActive()) {
            paymentService.refundPayment(booking.getPayment());
            bookedShow.unBookSeats(bookedSeats);
            booking.setIsActiveFalse();
            return true;
        }
        return false;
    }

    public static Booking fetchBooking(BookingRepository bookingRepository, long bookingId) {
        return bookingRepository.fetchBooking(bookingId);
    }
}
