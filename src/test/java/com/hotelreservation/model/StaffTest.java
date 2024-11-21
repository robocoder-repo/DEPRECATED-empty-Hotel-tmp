
package com.hotelreservation.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StaffTest {

    @Test
    public void testStaffCreation() {
        Staff staff = new Staff("S001", "John", "Doe", "M", "password123", "Manager");
        
        assertEquals("S001", staff.getStaffId());
        assertEquals("John", staff.getFirstName());
        assertEquals("Doe", staff.getLastName());
        assertEquals("M", staff.getMiddleName());
        assertEquals("password123", staff.getPassword());
        assertEquals("Manager", staff.getPosition());
    }

    @Test
    public void testStaffSetters() {
        Staff staff = new Staff("S001", "John", "Doe", "M", "password123", "Manager");
        
        staff.setStaffId("S002");
        staff.setFirstName("Jane");
        staff.setLastName("Smith");
        staff.setMiddleName("A");
        staff.setPassword("newpassword");
        staff.setPosition("Receptionist");
        
        assertEquals("S002", staff.getStaffId());
        assertEquals("Jane", staff.getFirstName());
        assertEquals("Smith", staff.getLastName());
        assertEquals("A", staff.getMiddleName());
        assertEquals("newpassword", staff.getPassword());
        assertEquals("Receptionist", staff.getPosition());
    }
}
