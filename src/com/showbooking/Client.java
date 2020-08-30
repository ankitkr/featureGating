package com.showbooking;

import com.showbooking.bookings.Booking;
import com.showbooking.bookings.BookingService;
import com.showbooking.events.EventService;
import com.showbooking.events.Show;
import com.showbooking.payments.Payable;
import com.showbooking.payments.PaymentService;
import com.showbooking.repository.BookingRepository;
import com.showbooking.repository.EventRepository;

import java.util.List;
import java.util.Map;

public class Client {
    EventRepository eventRepository;
    BookingRepository bookingRepository;
    Payable paymentService;

    public Client() {
        eventRepository = EventRepository.getInstance();
        bookingRepository = BookingRepository.getInstance();
        paymentService = new PaymentService();
    }
    public List<Show> fetchEvents(Map<String, String> filter){
        return EventService.fetchShows(eventRepository, filter);
    }

    public Boolean addOrUpdateEvents(List<Map<String, Object>> events){
        for(Map<String, Object> event: events) {
            if(!EventService.addOrUpdateEvent(eventRepository, event))
                return false;
        }

        return true;
    }

    public Boolean addLayout(Boolean[][] layout, Long screenId) {
        return EventService.addScreenLayout(eventRepository, screenId, layout);
    }

    public Boolean[][] fetchEventSeatLayout(Long eventId) {
        return EventService.fetchEventSeatLayout(eventRepository, eventId);
    }

    public Long bookEvent(List<Long> seatIds, Long eventId, Long screenId, Long userId) {
        return BookingService.bookEvent(paymentService, bookingRepository, eventRepository, userId, eventId,
                screenId, seatIds);
    }

    public Booking fetchBooking(Long bookingId) {
        return BookingService.fetchBooking(bookingRepository, bookingId);
    }

    public Boolean cancelBooking(Long bookingId) {
        return BookingService.cancelBooking(paymentService, bookingRepository, bookingId);
    }
}
