
# Hotel Reservation System

This is a JavaFX-based Hotel Reservation System application.

## Prerequisites

- Java Development Kit (JDK) 17 or later
- Maven 3.6 or later

## Building the project

To build the project, run the following command in the project root directory:

```
mvn clean package
```

## Running the application

After building the project, you can run the application using the following command:

```
java --module-path /path/to/javafx-sdk-17/lib --add-modules javafx.controls,javafx.fxml -jar target/hotel-reservation-system-1.0-SNAPSHOT.jar
```

Replace `/path/to/javafx-sdk-17` with the actual path to your JavaFX SDK installation.

## Project Structure

- `src/main/java/com/hotelreservation`: Contains the Java source files
  - `model`: Data models (Staff, Guest, Room, Booking)
  - `view`: JavaFX controllers
  - `viewmodel`: View models for MVVM pattern
  - `service`: Business logic and database operations
  - `util`: Utility classes (e.g., DatabaseConnection)
- `src/main/resources/com/hotelreservation`: Contains FXML and CSS files
- `pom.xml`: Maven project configuration file

## Database Configuration

The application uses a PostgreSQL database. Update the database connection details in `src/main/java/com/hotelreservation/util/DatabaseConnection.java` if necessary.

## Contributing

Please read CONTRIBUTING.md for details on our code of conduct, and the process for submitting pull requests to us.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details
