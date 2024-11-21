
package com.hotelreservation.view;

import com.hotelreservation.App;
import com.hotelreservation.model.Room;
import com.hotelreservation.model.Staff;
import com.hotelreservation.viewmodel.RoomManagementViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomManagementViewController {

    private static final Logger logger = LoggerFactory.getLogger(RoomManagementViewController.class);

    @FXML
    private TableView<Room> roomsTable;

    @FXML
    private TableColumn<Room, String> roomNumberColumn;

    @FXML
    private TableColumn<Room, String> roomTypeColumn;

    @FXML
    private TableColumn<Room, Double> roomPriceColumn;

    @FXML
    private TableColumn<Room, Boolean> availabilityColumn;

    @FXML
    private TextField roomNumberField;

    @FXML
    private ComboBox<String> roomTypeComboBox;

    @FXML
    private TextField roomPriceField;

    @FXML
    private CheckBox availabilityCheckBox;

    private RoomManagementViewModel viewModel;
    private Staff currentStaff;

    public RoomManagementViewController() {
        viewModel = new RoomManagementViewModel();
    }

    @FXML
    private void initialize() {
        logger.info("Initializing RoomManagementViewController");
        
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        roomPriceColumn.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        roomsTable.setItems(viewModel.getRooms());

        roomTypeComboBox.setItems(viewModel.getRoomTypes());

        roomsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });

        viewModel.loadRooms();
    }

    private void populateFields(Room room) {
        roomNumberField.setText(room.getRoomId());
        roomTypeComboBox.setValue(room.getRoomType());
        roomPriceField.setText(String.valueOf(room.getRoomPrice()));
        availabilityCheckBox.setSelected(room.isAvailability());
    }

    @FXML
    private void handleAddRoom() {
        logger.info("Adding new room");
        String roomNumber = roomNumberField.getText();
        String roomType = roomTypeComboBox.getValue();
        double roomPrice = Double.parseDouble(roomPriceField.getText());
        boolean availability = availabilityCheckBox.isSelected();

        if (viewModel.addRoom(roomNumber, roomType, roomPrice, availability)) {
            clearFields();
            App.showInfoAlert("Success", "Room added successfully.");
        } else {
            App.showErrorAlert("Error", "Failed to add room.");
        }
    }

    @FXML
    private void handleUpdateRoom() {
        logger.info("Updating room");
        Room selectedRoom = roomsTable.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            App.showErrorAlert("Error", "Please select a room to update.");
            return;
        }

        String roomNumber = roomNumberField.getText();
        String roomType = roomTypeComboBox.getValue();
        double roomPrice = Double.parseDouble(roomPriceField.getText());
        boolean availability = availabilityCheckBox.isSelected();

        if (viewModel.updateRoom(selectedRoom, roomNumber, roomType, roomPrice, availability)) {
            clearFields();
            App.showInfoAlert("Success", "Room updated successfully.");
        } else {
            App.showErrorAlert("Error", "Failed to update room.");
        }
    }

    @FXML
    private void handleDeleteRoom() {
        logger.info("Deleting room");
        Room selectedRoom = roomsTable.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            App.showErrorAlert("Error", "Please select a room to delete.");
            return;
        }

        if (viewModel.deleteRoom(selectedRoom)) {
            clearFields();
            App.showInfoAlert("Success", "Room deleted successfully.");
        } else {
            App.showErrorAlert("Error", "Failed to delete room.");
        }
    }

    private void clearFields() {
        roomNumberField.clear();
        roomTypeComboBox.getSelectionModel().clearSelection();
        roomPriceField.clear();
        availabilityCheckBox.setSelected(false);
    }

    public void setCurrentStaff(Staff staff) {
        this.currentStaff = staff;
    }
}
