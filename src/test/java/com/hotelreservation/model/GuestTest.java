
package com.hotelreservation.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuestTest {

    @Test
    public void testGuestCreation() {
        Guest guest = new Guest("G001", "John", "Doe", "M", "password123", "1234567890");
        
        assertEquals("G001", guest.getGuestId());
        assertEquals("John", guest.getFirstName());
        assertEquals("Doe", guest.getLastName());
        assertEquals("M", guest.getMiddleName());
        assertEquals("password123", guest.getPassword());
        assertEquals("1234567890", guest.getContactNumber());
    }

    @Test
    public void testGuestSetters() {
        Guest guest = new Guest("G001", "John", "Doe", "M", "password123", "1234567890");
        
        guest.setGuestId("G002");
        guest.setFirstName("Jane");
        guest.setLastName("Smith");
        guest.setMiddleName("A");
        guest.setPassword("newpassword");
        guest.setContactNumber("9876543210");
        
        assertEquals("G002", guest.getGuestId());
        assertEquals("Jane", guest.getFirstName());
        assertEquals("Smith", guest.getLastName());
        assertEquals("A", guest.getMiddleName());
        assertEquals("newpassword", guest.getPassword());
        assertEquals("9876543210", guest.getContactNumber());
    }

    @Test
    public void testFullNameProperty() {
        Guest guest = new Guest("G001", "John", "Doe", "M", "password123", "1234567890");
        assertEquals("John M Doe", guest.getFullName());

        guest.setMiddleName("");
        assertEquals("John Doe", guest.getFullName());

        guest.setMiddleName(null);
        assertEquals("John Doe", guest.getFullName());
    }
}
