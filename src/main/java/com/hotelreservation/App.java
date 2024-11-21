
package com.hotelreservation;

import com.hotelreservation.model.Staff;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App extends Application {

    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        logger.info("Starting Hotel Reservation System");
        primaryStage = stage;
        showLoginView();
    }

    public static void showLoginView() throws IOException {
        logger.info("Showing login view");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("login-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hotel Reservation System - Login");
        primaryStage.show();
    }

    public static void showMainView(Staff authenticatedStaff) throws IOException {
        logger.info("Showing main view for staff: {}", authenticatedStaff.getStaffId());
        FXMLLoader loader = new FXMLLoader(App.class.getResource("main-view.fxml"));
        Parent root = loader.load();
        MainViewController controller = loader.getController();
        controller.setCurrentStaff(authenticatedStaff);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hotel Reservation System");
        primaryStage.show();
    }

    public static void showErrorAlert(String title, String content) {
        logger.error("Error alert: {} - {}", title, content);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showInfoAlert(String title, String content) {
        logger.info("Info alert: {} - {}", title, content);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
