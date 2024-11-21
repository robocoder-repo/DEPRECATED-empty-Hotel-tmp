
package com.hotelreservation.viewmodel;

import com.hotelreservation.model.Booking;
import com.hotelreservation.service.BookingService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class MainViewModel {

    private BookingService bookingService;
    private ObservableList<Booking> bookings;
    private List<Booking> allBookings;

    public MainViewModel() {
        bookingService = new BookingService();
        bookings = FXCollections.observableArrayList();
    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    public void loadBookings() {
        allBookings = bookingService.getAllBookings();
        bookings.setAll(allBookings);
    }

    public void searchBookings(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            bookings.setAll(allBookings);
        } else {
            List<Booking> filteredBookings = allBookings.stream()
                .filter(booking -> 
                    booking.getBookingId().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    booking.getGuest().getFullName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    booking.getRoomId().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    booking.getVoucherNumber().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    booking.getInDate().toString().contains(searchTerm) ||
                    booking.getOutDate().toString().contains(searchTerm)
                )
                .collect(Collectors.toList());
            bookings.setAll(filteredBookings);
        }
    }
}
