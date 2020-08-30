package com.showbooking.repository;


import com.showbooking.bookings.Booking;

import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    private volatile static BookingRepository instance;
    private final Map<Long, Booking> allBookings;

    private BookingRepository() {
        allBookings = new HashMap<>();
    }

    public static BookingRepository getInstance() {
        if(instance == null) {
            synchronized (BookingRepository.class) {
                if(instance == null) {
                    instance = new BookingRepository();
                }
            }
        }
        return instance;
    }

    public Booking fetchBooking(Long bookingId) {
        return allBookings.getOrDefault(bookingId, null);
    }

    public Booking addBooking(Booking userBooking) {
        allBookings.put(userBooking.getId(), userBooking);
        return userBooking;
    }

    public void cancelBooking(Booking userBooking) {
        allBookings.put(userBooking.getId(), userBooking);
    }
}
