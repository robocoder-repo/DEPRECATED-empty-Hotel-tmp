package com.hotelreservation.model;

public class Staff {
    private String staffId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String password;
    private String position;

    public Staff(String staffId, String firstName, String lastName, String middleName, String password, String position) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.password = password;
        this.position = position;
    }

    // Getters and setters
    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}
