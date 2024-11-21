
package com.hotelreservation.view;

import com.hotelreservation.App;
import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Staff;
import com.hotelreservation.viewmodel.BookingDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookingDetailsViewController {

    @FXML
    private Label bookingIdLabel;
    @FXML
    private Label roomNumberLabel;
    @FXML
    private Label guestNameLabel;
    @FXML
    private Label checkInDateLabel;
    @FXML
    private Label checkOutDateLabel;
    @FXML
    private TextField specialPreferenceField;
    @FXML
    private Label paymentStatusLabel;
    @FXML
    private Label bookingStatusLabel;
    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;

    private BookingDetailsViewModel viewModel;
    private Staff currentStaff;

    public void setBooking(Booking booking) {
        viewModel = new BookingDetailsViewModel(booking);
        bindData();
    }

    public void setCurrentStaff(Staff staff) {
        this.currentStaff = staff;
        editButton.setVisible(currentStaff.getPosition().equals("Manager"));
    }

    private void bindData() {
        bookingIdLabel.textProperty().bind(viewModel.bookingIdProperty());
        roomNumberLabel.textProperty().bind(viewModel.roomNumberProperty());
        guestNameLabel.textProperty().bind(viewModel.guestNameProperty());
        checkInDateLabel.textProperty().bind(viewModel.checkInDateProperty());
        checkOutDateLabel.textProperty().bind(viewModel.checkOutDateProperty());
        specialPreferenceField.textProperty().bindBidirectional(viewModel.specialPreferenceProperty());
        paymentStatusLabel.textProperty().bind(viewModel.paymentStatusProperty());
        bookingStatusLabel.textProperty().bind(viewModel.bookingStatusProperty());
    }

    @FXML
    private void handleEdit() {
        specialPreferenceField.setEditable(true);
        saveButton.setVisible(true);
    }

    @FXML
    private void handleSave() {
        if (viewModel.saveChanges()) {
            specialPreferenceField.setEditable(false);
            saveButton.setVisible(false);
            App.showInfoAlert("Success", "Booking details updated successfully.");
        } else {
            App.showErrorAlert("Error", "Failed to update booking details.");
        }
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) bookingIdLabel.getScene().getWindow();
        stage.close();
    }
}
