
package com.hotelreservation.service;

import com.hotelreservation.model.Guest;
import com.hotelreservation.util.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuestServiceTest {

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

    private GuestService guestService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(dbConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        guestService = new GuestService(dbConnection);
    }

    @Test
    void testGetGuestById() throws Exception {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("guestId")).thenReturn("G001");
        when(resultSet.getString("firstName")).thenReturn("John");
        when(resultSet.getString("lastName")).thenReturn("Doe");
        when(resultSet.getString("middleName")).thenReturn("M");
        when(resultSet.getString("password")).thenReturn("password123");
        when(resultSet.getString("contactNumber")).thenReturn("1234567890");

        Guest guest = guestService.getGuestById("G001");

        assertNotNull(guest);
        assertEquals("G001", guest.getGuestId());
        assertEquals("John", guest.getFirstName());
        assertEquals("Doe", guest.getLastName());
        assertEquals("M", guest.getMiddleName());
        assertEquals("password123", guest.getPassword());
        assertEquals("1234567890", guest.getContactNumber());
    }

    @Test
    void testCreateGuest() throws Exception {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Guest guest = new Guest("G001", "John", "Doe", "M", "password123", "1234567890");
        boolean result = guestService.createGuest(guest);

        assertTrue(result);
        verify(preparedStatement).setString(1, "G001");
        verify(preparedStatement).setString(2, "John");
        verify(preparedStatement).setString(3, "Doe");
        verify(preparedStatement).setString(4, "M");
        verify(preparedStatement).setString(5, "password123");
        verify(preparedStatement).setString(6, "1234567890");
    }

    @Test
    void testGetAllGuests() throws Exception {
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getString("guestId")).thenReturn("G001", "G002");
        when(resultSet.getString("firstName")).thenReturn("John", "Jane");
        when(resultSet.getString("lastName")).thenReturn("Doe", "Smith");
        when(resultSet.getString("middleName")).thenReturn("M", "A");
        when(resultSet.getString("password")).thenReturn("password123", "password456");
        when(resultSet.getString("contactNumber")).thenReturn("1234567890", "0987654321");

        List<Guest> guests = guestService.getAllGuests();

        assertEquals(2, guests.size());
        assertEquals("G001", guests.get(0).getGuestId());
        assertEquals("John", guests.get(0).getFirstName());
        assertEquals("G002", guests.get(1).getGuestId());
        assertEquals("Jane", guests.get(1).getFirstName());
    }

    // Add more tests for updateGuest and deleteGuest methods
}
