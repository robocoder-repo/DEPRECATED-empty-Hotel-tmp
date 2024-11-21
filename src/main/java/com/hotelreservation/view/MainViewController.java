
package com.hotelreservation.view;

import com.hotelreservation.App;
import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Room;
import com.hotelreservation.model.Staff;
import com.hotelreservation.viewmodel.MainViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainViewController {

    private static final Logger logger = LoggerFactory.getLogger(MainViewController.class);

    @FXML
    private TableView<Booking> reservationsTable;

    @FXML
    private TableColumn<Booking, String> roomNumberColumn;

    @FXML
    private TableColumn<Booking, String> roomStateColumn;

    @FXML
    private TableColumn<Booking, String> roomTypeColumn;

    @FXML
    private TableColumn<Booking, String> guestNameColumn;

    @FXML
    private TableColumn<Booking, String> voucherNumberColumn;

    @FXML
    private TableColumn<Booking, String> inDateColumn;

    @FXML
    private TableColumn<Booking, String> outDateColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button newReservationButton;

    @FXML
    private Button manageRoomsButton;

    private MainViewModel mainViewModel;
    private Staff currentStaff;

    public MainViewController() {
        mainViewModel = new MainViewModel();
    }

    @FXML
    private void initialize() {
        logger.info("Initializing MainViewController");
        // Initialize the reservations table
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomStateColumn.setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomId")); // You might need to add a method to get room type from room ID
        guestNameColumn.setCellValueFactory(cellData -> cellData.getValue().getGuest().fullNameProperty());
        voucherNumberColumn.setCellValueFactory(new PropertyValueFactory<>("voucherNumber"));
        inDateColumn.setCellValueFactory(new PropertyValueFactory<>("inDate"));
        outDateColumn.setCellValueFactory(new PropertyValueFactory<>("outDate"));

        // Bind the table items to the bookings in the view model
        reservationsTable.setItems(mainViewModel.getBookings());

        // Add listener for row selection
        reservationsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showBookingDetails(newSelection);
            }
        });

        // Add listener for search field
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            logger.info("Searching bookings with term: {}", newValue);
            mainViewModel.searchBookings(newValue);
        });

        // Load the bookings
        mainViewModel.loadBookings();
    }

    @FXML
    private void handleNewReservation() {
        logger.info("Opening new reservation window");
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("new-reservation-view.fxml"));
            Parent root = loader.load();
            NewReservationViewController controller = loader.getController();
            controller.setCurrentStaff(currentStaff);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("New Reservation");
            stage.show();
        } catch (IOException e) {
            logger.error("Failed to open new reservation window", e);
            App.showErrorAlert("Error", "Failed to open new reservation window.");
        }
    }

    @FXML
    private void handleManageRooms() {
        logger.info("Opening room management window");
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("room-management-view.fxml"));
            Parent root = loader.load();
            RoomManagementViewController controller = loader.getController();
            controller.setCurrentStaff(currentStaff);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Room Management");
            stage.show();
        } catch (IOException e) {
            logger.error("Failed to open room management window", e);
            App.showErrorAlert("Error", "Failed to open room management window.");
        }
    }

    private void showBookingDetails(Booking booking) {
        logger.info("Showing booking details for booking ID: {}", booking.getBookingId());
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("booking-details-view.fxml"));
            Parent root = loader.load();
            BookingDetailsViewController controller = loader.getController();
            controller.setBooking(booking);
            controller.setCurrentStaff(currentStaff);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Booking Details");
            stage.show();
        } catch (IOException e) {
            logger.error("Failed to open booking details window", e);
            App.showErrorAlert("Error", "Failed to open booking details window.");
        }
    }

    public void setCurrentStaff(Staff staff) {
        logger.info("Setting current staff: {}", staff.getStaffId());
        this.currentStaff = staff;
        updateUIBasedOnRole();
    }

    private void updateUIBasedOnRole() {
        boolean isManager = "Manager".equalsIgnoreCase(currentStaff.getPosition());
        newReservationButton.setVisible(true); // All staff can make reservations
        manageRoomsButton.setVisible(isManager); // Only managers can manage rooms
        logger.info("UI updated based on staff role. Is manager: {}", isManager);
    }
}
