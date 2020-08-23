package com.bms.repository;

import com.bms.inventory.Booking;
import com.bms.user.User;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private volatile static BookingRepository instance;
    private final List<Booking> allBookings;

    private BookingRepository() {
        allBookings = new ArrayList<Booking>();
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

    public void addBooking(Booking userBooking) {
        allBookings.add(userBooking);
    }

    public boolean removeBooking(Booking userBooking) {
        boolean removed = false;
        for(Booking booking: allBookings) {
            if(booking == userBooking) {
                allBookings.remove(booking);
                removed = true;
                break;
            }
        }

        return removed;
    }

    public List<Booking> fetchBookings(User user) {
        List<Booking> userBookings = new ArrayList<>();
        for(Booking booking:allBookings) {
            if(booking.getUser() == user) {
                userBookings.add(booking);
            }
        }

        return userBookings;
    }
}
