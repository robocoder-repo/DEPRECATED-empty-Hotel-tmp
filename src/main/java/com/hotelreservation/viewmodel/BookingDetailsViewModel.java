
package com.hotelreservation.viewmodel;

import com.hotelreservation.model.Booking;
import com.hotelreservation.service.BookingService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookingDetailsViewModel {

    private Booking booking;
    private BookingService bookingService;

    private StringProperty bookingId;
    private StringProperty roomNumber;
    private StringProperty guestName;
    private StringProperty checkInDate;
    private StringProperty checkOutDate;
    private StringProperty specialPreference;
    private StringProperty paymentStatus;
    private StringProperty bookingStatus;

    public BookingDetailsViewModel(Booking booking) {
        this.booking = booking;
        this.bookingService = new BookingService();
        initializeProperties();
    }

    private void initializeProperties() {
        bookingId = new SimpleStringProperty(booking.getBookingId());
        roomNumber = new SimpleStringProperty(booking.getRoomId());
        guestName = new SimpleStringProperty(booking.getGuest().getFullName());
        checkInDate = new SimpleStringProperty(booking.getInDate().toString());
        checkOutDate = new SimpleStringProperty(booking.getOutDate().toString());
        specialPreference = new SimpleStringProperty(booking.getSpecialPreference());
        paymentStatus = new SimpleStringProperty(booking.getPaymentStatus());
        bookingStatus = new SimpleStringProperty(booking.getBookingStatus());
    }

    public boolean saveChanges() {
        booking.setSpecialPreference(specialPreference.get());
        return bookingService.updateBooking(booking);
    }

    // Getter methods for properties
    public StringProperty bookingIdProperty() {
        return bookingId;
    }

    public StringProperty roomNumberProperty() {
        return roomNumber;
    }

    public StringProperty guestNameProperty() {
        return guestName;
    }

    public StringProperty checkInDateProperty() {
        return checkInDate;
    }

    public StringProperty checkOutDateProperty() {
        return checkOutDate;
    }

    public StringProperty specialPreferenceProperty() {
        return specialPreference;
    }

    public StringProperty paymentStatusProperty() {
        return paymentStatus;
    }

    public StringProperty bookingStatusProperty() {
        return bookingStatus;
    }
}
