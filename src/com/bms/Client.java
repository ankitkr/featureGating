package com.bms;

import com.bms.event.Event;
import com.bms.inventory.Booking;
import com.bms.inventory.SeatBooking;
import com.bms.inventory.Show;
import com.bms.inventory.exception.SeatUnavailable;
import com.bms.payment.PaymentGateway;
import com.bms.payment.exception.PaymentFailedException;
import com.bms.repository.BookingRepository;
import com.bms.repository.MovieRepository;
import com.bms.repository.ShowRepository;
import com.bms.repository.UserRepository;
import com.bms.user.User;
import com.bms.user.exception.UserNotFoundException;

import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        MovieRepository movieRepository = MovieRepository.getInstance();
        List<Event> moviesList = movieRepository.fetchAllMovies();
        for (Event movie: moviesList) {
            System.out.println(movie.getEventDetails());
        }

        List<Event> searchMovieList = movieRepository.searchMovie("tanhaji");
        if(searchMovieList.isEmpty()) {
            System.out.println("No movies match the search query");
            return;
        }
        for (Event movie: searchMovieList) {
            System.out.println(movie.getEventDetails());
        }

        Event toWatchMovie = searchMovieList.get(0);
        ShowRepository showRepository = ShowRepository.getInstance();
        List<Show> availableShows = showRepository.getShows(toWatchMovie);
        if(availableShows.isEmpty()) {
            System.out.println("No shows for the movie: " + toWatchMovie.getEventName());
            return;
        }
        for(Show show: availableShows) {
            System.out.println(show.getDetails());
        }

        Show toWatchShow = availableShows.get(0);
        System.out.println(toWatchShow.fetchSeatingDetails());

        int[] toSelectSeats1 = {2,3,4};
        int[] toSelectSeats2 = {1,8};
        try {
            SeatBooking.selectSeats(toWatchShow, toSelectSeats1);
        } catch (SeatUnavailable exception) {
            System.out.println(exception.getMessage());
            return;
        }


        User currentUser;
        UserRepository userRepository = UserRepository.getInstance();
        try {
            currentUser = userRepository.getUser(2);
        } catch (UserNotFoundException exp) {
            System.out.println("You are not a registered user! Please register to proceed.");
            return;
        }

        SeatBooking seatBooking = new SeatBooking(PaymentGateway.getInstance(), BookingRepository.getInstance());
        Booking currentBooking1 = null;
        try {
            currentBooking1 = seatBooking.bookSeats(currentUser, toWatchShow, toSelectSeats1);
            System.out.println("Congrats, your booking has been successful. Seat Ids: " + Arrays.toString(currentBooking1.getBookedSeatIds()));
        } catch (SeatUnavailable | PaymentFailedException exception) {
            System.out.println("Booking Could not be confirmed, Please retry!");
            System.out.println(exception.toString());
            return;
        }

        Booking currentBooking2 = null;
        try {
            currentBooking2 = seatBooking.bookSeats(currentUser, toWatchShow, toSelectSeats2);
            System.out.println("Congrats, your booking has been successful. Seat Ids: " + Arrays.toString(currentBooking2.getBookedSeatIds()));
        } catch (SeatUnavailable | PaymentFailedException exception) {
            System.out.println("Booking Could not be confirmed, Please retry!");
            System.out.println(exception.toString());
            return;
        }

        Booking currentBooking3 = null;
        try {
            currentBooking3 = seatBooking.bookSeats(currentUser, toWatchShow, toSelectSeats2);
            System.out.println("Congrats, your booking has been successful. Seat Ids: " + Arrays.toString(currentBooking3.getBookedSeatIds()));
        } catch (SeatUnavailable | PaymentFailedException exception) {
            System.out.println("Booking Could not be confirmed, Please retry!");
            System.out.println(exception.toString());
        }

        seatBooking.cancelBooking(currentBooking2);

        try {
            currentUser = userRepository.getUser(3);
        } catch (UserNotFoundException exp) {
            System.out.println("You are not a registered user! Please register to proceed.");
            return;
        }
        currentBooking3 = null;
        try {
            currentBooking3 = seatBooking.bookSeats(currentUser, toWatchShow, toSelectSeats2);
            System.out.println("Congrats, your booking has been successful. Seat Ids: " + Arrays.toString(currentBooking3.getBookedSeatIds()));
        } catch (SeatUnavailable | PaymentFailedException exception) {
            System.out.println("Booking Could not be confirmed, Please retry!");
            System.out.println(exception.toString());
            return;
        }
    }
}
