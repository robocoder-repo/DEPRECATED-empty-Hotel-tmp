package com.hotelreservation.model;

public class Room {
    private String roomId;
    private String roomType;
    private double roomPrice;
    private boolean availability;

    public Room(String roomId, String roomType, double roomPrice, boolean availability) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.availability = availability;
    }

    // Getters and setters
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public double getRoomPrice() { return roomPrice; }
    public void setRoomPrice(double roomPrice) { this.roomPrice = roomPrice; }
    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
}
