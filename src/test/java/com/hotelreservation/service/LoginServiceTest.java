
package com.hotelreservation.service;

import com.hotelreservation.model.Staff;
import com.hotelreservation.util.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    @Mock
    private DatabaseConnection dbConnection;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private LoginService loginService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(dbConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        loginService = new LoginService(dbConnection);
    }

    @Test
    void testAuthenticateStaffSuccess() throws Exception {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("staffId")).thenReturn("S001");
        when(resultSet.getString("firstName")).thenReturn("John");
        when(resultSet.getString("lastName")).thenReturn("Doe");
        when(resultSet.getString("middleName")).thenReturn("M");
        when(resultSet.getString("password")).thenReturn("password123");
        when(resultSet.getString("position")).thenReturn("Manager");

        Staff staff = loginService.authenticateStaff("S001", "password123");

        assertNotNull(staff);
        assertEquals("S001", staff.getStaffId());
        assertEquals("John", staff.getFirstName());
        assertEquals("Doe", staff.getLastName());
        assertEquals("M", staff.getMiddleName());
        assertEquals("password123", staff.getPassword());
        assertEquals("Manager", staff.getPosition());

        verify(preparedStatement).setString(1, "S001");
        verify(preparedStatement).setString(2, "password123");
    }

    @Test
    void testAuthenticateStaffFailure() throws Exception {
        when(resultSet.next()).thenReturn(false);

        Staff staff = loginService.authenticateStaff("S001", "wrongpassword");

        assertNull(staff);

        verify(preparedStatement).setString(1, "S001");
        verify(preparedStatement).setString(2, "wrongpassword");
    }
}
