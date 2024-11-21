
package com.hotelreservation.view;

import com.hotelreservation.App;
import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Guest;
import com.hotelreservation.model.Room;
import com.hotelreservation.model.Staff;
import com.hotelreservation.viewmodel.NewReservationViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class NewReservationViewController {

    @FXML
    private ComboBox<String> roomTypeComboBox;

    @FXML
    private DatePicker checkInDatePicker;

    @FXML
    private DatePicker checkOutDatePicker;

    @FXML
    private TableView<Room> availableRoomsTable;

    @FXML
    private TextField guestNameField;

    @FXML
    private TextField guestContactField;

    @FXML
    private TextField voucherNumberField;

    @FXML
    private TextArea specialPreferenceArea;

    private NewReservationViewModel viewModel;
    private Staff currentStaff;

    public NewReservationViewController() {
        viewModel = new NewReservationViewModel();
    }

    @FXML
    private void initialize() {
        roomTypeComboBox.setItems(viewModel.getRoomTypes());
        availableRoomsTable.setItems(viewModel.getAvailableRooms());

        // Set up table columns
        // ... (set up table columns for room number, type, price, etc.)
    }

    @FXML
    private void handleSearchRooms() {
        String roomType = roomTypeComboBox.getValue();
        LocalDate checkInDate = checkInDatePicker.getValue();
        LocalDate checkOutDate = checkOutDatePicker.getValue();

        if (roomType == null || checkInDate == null || checkOutDate == null) {
            App.showErrorAlert("Error", "Please fill in all fields.");
            return;
        }

        if (checkOutDate.isBefore(checkInDate)) {
            App.showErrorAlert("Error", "Check-out date must be after check-in date.");
            return;
        }

        viewModel.searchAvailableRooms(roomType, checkInDate, checkOutDate);
    }

    @FXML
    private void handleMakeReservation() {
        Room selectedRoom = availableRoomsTable.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            App.showErrorAlert("Error", "Please select a room.");
            return;
        }

        String guestName = guestNameField.getText();
        String guestContact = guestContactField.getText();
        String voucherNumber = voucherNumberField.getText();
        String specialPreference = specialPreferenceArea.getText();

        if (guestName.isEmpty() || guestContact.isEmpty()) {
            App.showErrorAlert("Error", "Please enter guest information.");
            return;
        }

        Guest guest = new Guest(null, guestName, "", "", "", guestContact);
        Booking booking = viewModel.createBooking(selectedRoom, guest, checkInDatePicker.getValue(),
                checkOutDatePicker.getValue(), voucherNumber, specialPreference);

        if (booking != null) {
            App.showInfoAlert("Success", "Reservation created successfully.");
            closeWindow();
        } else {
            App.showErrorAlert("Error", "Failed to create reservation.");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) roomTypeComboBox.getScene().getWindow();
        stage.close();
    }

    public void setCurrentStaff(Staff staff) {
        this.currentStaff = staff;
    }
}
