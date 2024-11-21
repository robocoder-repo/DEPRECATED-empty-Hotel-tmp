package com.hotelreservation.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Guest {
    private String guestId;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty middleName;
    private String password;
    private StringProperty contactNumber;

    public Guest(String guestId, String firstName, String lastName, String middleName, String password, String contactNumber) {
        this.guestId = guestId;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.middleName = new SimpleStringProperty(middleName);
        this.password = password;
        this.contactNumber = new SimpleStringProperty(contactNumber);
    }

    // Getters and setters
    public String getGuestId() { return guestId; }
    public void setGuestId(String guestId) { this.guestId = guestId; }

    public String getFirstName() { return firstName.get(); }
    public StringProperty firstNameProperty() { return firstName; }
    public void setFirstName(String firstName) { this.firstName.set(firstName); }

    public String getLastName() { return lastName.get(); }
    public StringProperty lastNameProperty() { return lastName; }
    public void setLastName(String lastName) { this.lastName.set(lastName); }

    public String getMiddleName() { return middleName.get(); }
    public StringProperty middleNameProperty() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName.set(middleName); }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getContactNumber() { return contactNumber.get(); }
    public StringProperty contactNumberProperty() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber.set(contactNumber); }

    public StringProperty fullNameProperty() {
        return new SimpleStringProperty(getFullName());
    }

    public String getFullName() {
        return getFirstName() + " " + (getMiddleName() != null && !getMiddleName().isEmpty() ? getMiddleName() + " " : "") + getLastName();
    }
}
