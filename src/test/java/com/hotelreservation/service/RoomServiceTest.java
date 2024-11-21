
package com.hotelreservation.service;

import com.hotelreservation.model.Room;
import com.hotelreservation.util.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoomServiceTest {

    @Mock
    private DatabaseConnection dbConnection;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private Statement statement;

    @Mock
    private ResultSet resultSet;

    private RoomService roomService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(dbConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        roomService = new RoomService(dbConnection);
    }

    @Test
    void testGetAllRoomTypes() throws Exception {
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getString("roomType")).thenReturn("Single", "Double");

        List<String> roomTypes = roomService.getAllRoomTypes();

        assertEquals(2, roomTypes.size());
        assertTrue(roomTypes.contains("Single"));
        assertTrue(roomTypes.contains("Double"));
    }

    @Test
    void testGetAvailableRooms() throws Exception {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("roomId")).thenReturn("R001");
        when(resultSet.getString("roomType")).thenReturn("Single");
        when(resultSet.getDouble("roomPrice")).thenReturn(100.0);
        when(resultSet.getBoolean("availability")).thenReturn(true);

        List<Room> availableRooms = roomService.getAvailableRooms("Single", LocalDate.now(), LocalDate.now().plusDays(1));

        assertEquals(1, availableRooms.size());
        assertEquals("R001", availableRooms.get(0).getRoomId());
        assertEquals("Single", availableRooms.get(0).getRoomType());
        assertEquals(100.0, availableRooms.get(0).getRoomPrice());
        assertTrue(availableRooms.get(0).isAvailability());
    }

    @Test
    void testAddRoom() throws Exception {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Room room = new Room("R001", "Single", 100.0, true);
        boolean result = roomService.addRoom(room);

        assertTrue(result);
        verify(preparedStatement).setString(1, "R001");
        verify(preparedStatement).setString(2, "Single");
        verify(preparedStatement).setDouble(3, 100.0);
        verify(preparedStatement).setBoolean(4, true);
    }

    // Add more tests for updateRoom, deleteRoom, and getAllRooms methods
}
