package com.hotelreservation.service;

import com.hotelreservation.model.Room;
import com.hotelreservation.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomService {
    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    public List<String> getAllRoomTypes() {
        List<String> roomTypes = new ArrayList<>();
        String sql = "SELECT DISTINCT roomType FROM Rooms";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                roomTypes.add(rs.getString("roomType"));
            }
        } catch (SQLException e) {
            logger.error("Error fetching room types", e);
        }

        return roomTypes;
    }

    public List<Room> getAvailableRooms(String roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        String sql = "SELECT * FROM Rooms r WHERE r.roomType = ? AND r.availability = true " +
                     "AND r.roomId NOT IN (SELECT b.roomId FROM Bookings b WHERE " +
                     "(b.inDate <= ? AND b.outDate >= ?) OR " +
                     "(b.inDate >= ? AND b.inDate < ?))";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, roomType);
            pstmt.setDate(2, Date.valueOf(checkOutDate));
            pstmt.setDate(3, Date.valueOf(checkInDate));
            pstmt.setDate(4, Date.valueOf(checkInDate));
            pstmt.setDate(5, Date.valueOf(checkOutDate));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Room room = new Room(
                    rs.getString("roomId"),
                    rs.getString("roomType"),
                    rs.getDouble("roomPrice"),
                    rs.getBoolean("availability")
                );
                availableRooms.add(room);
            }
        } catch (SQLException e) {
            logger.error("Error fetching available rooms", e);
        }

        return availableRooms;
    }

    public boolean addRoom(Room room) {
        String sql = "INSERT INTO Rooms (roomId, roomType, roomPrice, availability) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, room.getRoomId());
            pstmt.setString(2, room.getRoomType());
            pstmt.setDouble(3, room.getRoomPrice());
            pstmt.setBoolean(4, room.isAvailability());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.error("Error adding room", e);
            return false;
        }
    }

    public boolean updateRoom(Room room) {
        String sql = "UPDATE Rooms SET roomType = ?, roomPrice = ?, availability = ? WHERE roomId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, room.getRoomType());
            pstmt.setDouble(2, room.getRoomPrice());
            pstmt.setBoolean(3, room.isAvailability());
            pstmt.setString(4, room.getRoomId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.error("Error updating room", e);
            return false;
        }
    }

    public boolean deleteRoom(String roomId) {
        String sql = "DELETE FROM Rooms WHERE roomId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, roomId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.error("Error deleting room", e);
            return false;
        }
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Rooms";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Room room = new Room(
                    rs.getString("roomId"),
                    rs.getString("roomType"),
                    rs.getDouble("roomPrice"),
                    rs.getBoolean("availability")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            logger.error("Error fetching all rooms", e);
        }

        return rooms;
    }
}
