
package com.hotelreservation.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void testRoomCreation() {
        Room room = new Room("R001", "Deluxe", 150.0, true);
        
        assertEquals("R001", room.getRoomId());
        assertEquals("Deluxe", room.getRoomType());
        assertEquals(150.0, room.getRoomPrice(), 0.001);
        assertTrue(room.isAvailability());
    }

    @Test
    public void testRoomSetters() {
        Room room = new Room("R001", "Deluxe", 150.0, true);
        
        room.setRoomId("R002");
        room.setRoomType("Suite");
        room.setRoomPrice(200.0);
        room.setAvailability(false);
        
        assertEquals("R002", room.getRoomId());
        assertEquals("Suite", room.getRoomType());
        assertEquals(200.0, room.getRoomPrice(), 0.001);
        assertFalse(room.isAvailability());
    }
}
