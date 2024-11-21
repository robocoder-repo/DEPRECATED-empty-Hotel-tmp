
package com.hotelreservation.viewmodel;

import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Guest;
import com.hotelreservation.model.Room;
import com.hotelreservation.service.BookingService;
import com.hotelreservation.service.RoomService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class NewReservationViewModel {

    private RoomService roomService;
    private BookingService bookingService;
    private ObservableList<String> roomTypes;
    private ObservableList<Room> availableRooms;

    public NewReservationViewModel() {
        roomService = new RoomService();
        bookingService = new BookingService();
        roomTypes = FXCollections.observableArrayList();
        availableRooms = FXCollections.observableArrayList();
        loadRoomTypes();
    }

    private void loadRoomTypes() {
        List<String> types = roomService.getAllRoomTypes();
        roomTypes.setAll(types);
    }

    public ObservableList<String> getRoomTypes() {
        return roomTypes;
    }

    public ObservableList<Room> getAvailableRooms() {
        return availableRooms;
    }

    public void searchAvailableRooms(String roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> rooms = roomService.getAvailableRooms(roomType, checkInDate, checkOutDate);
        availableRooms.setAll(rooms);
    }

    public Booking createBooking(Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate, String voucherNumber, String specialPreference) {
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, guest.getGuestId(), room.getRoomId(), voucherNumber,
                checkInDate, checkOutDate, specialPreference, "Pending", "Confirmed");

        if (bookingService.createBooking(booking, guest)) {
            return booking;
        }
        return null;
    }
}
