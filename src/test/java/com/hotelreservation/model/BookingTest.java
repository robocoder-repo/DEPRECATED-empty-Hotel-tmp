
package com.hotelreservation.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class BookingTest {

    @Test
    public void testBookingCreation() {
        LocalDate inDate = LocalDate.of(2023, 6, 1);
        LocalDate outDate = LocalDate.of(2023, 6, 5);
        Booking booking = new Booking("B001", "G001", "R001", "V001", inDate, outDate, "No smoking", "Paid", "Confirmed");
        
        assertEquals("B001", booking.getBookingId());
        assertEquals("G001", booking.getGuestId());
        assertEquals("R001", booking.getRoomId());
        assertEquals("V001", booking.getVoucherNumber());
        assertEquals(inDate, booking.getInDate());
        assertEquals(outDate, booking.getOutDate());
        assertEquals("No smoking", booking.getSpecialPreference());
        assertEquals("Paid", booking.getPaymentStatus());
        assertEquals("Confirmed", booking.getBookingStatus());
    }

    @Test
    public void testBookingSetters() {
        LocalDate inDate = LocalDate.of(2023, 6, 1);
        LocalDate outDate = LocalDate.of(2023, 6, 5);
        Booking booking = new Booking("B001", "G001", "R001", "V001", inDate, outDate, "No smoking", "Paid", "Confirmed");
        
        LocalDate newInDate = LocalDate.of(2023, 7, 1);
        LocalDate newOutDate = LocalDate.of(2023, 7, 5);
        booking.setBookingId("B002");
        booking.setGuestId("G002");
        booking.setRoomId("R002");
        booking.setVoucherNumber("V002");
        booking.setInDate(newInDate);
        booking.setOutDate(newOutDate);
        booking.setSpecialPreference("Extra pillows");
        booking.setPaymentStatus("Pending");
        booking.setBookingStatus("Cancelled");
        
        assertEquals("B002", booking.getBookingId());
        assertEquals("G002", booking.getGuestId());
        assertEquals("R002", booking.getRoomId());
        assertEquals("V002", booking.getVoucherNumber());
        assertEquals(newInDate, booking.getInDate());
        assertEquals(newOutDate, booking.getOutDate());
        assertEquals("Extra pillows", booking.getSpecialPreference());
        assertEquals("Pending", booking.getPaymentStatus());
        assertEquals("Cancelled", booking.getBookingStatus());
    }

    @Test
    public void testGuestAssociation() {
        LocalDate inDate = LocalDate.of(2023, 6, 1);
        LocalDate outDate = LocalDate.of(2023, 6, 5);
        Booking booking = new Booking("B001", "G001", "R001", "V001", inDate, outDate, "No smoking", "Paid", "Confirmed");
        
        Guest guest = new Guest("G001", "John", "Doe", "M", "password123", "1234567890");
        booking.setGuest(guest);
        
        assertNotNull(booking.getGuest());
        assertEquals("John", booking.getGuest().getFirstName());
        assertEquals("Doe", booking.getGuest().getLastName());
    }
}
