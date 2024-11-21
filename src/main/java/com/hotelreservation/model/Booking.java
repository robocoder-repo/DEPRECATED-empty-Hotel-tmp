package com.hotelreservation.model;

import java.time.LocalDate;

public class Booking {
    private String bookingId;
    private String guestId;
    private String roomId;
    private String voucherNumber;
    private LocalDate inDate;
    private LocalDate outDate;
    private String specialPreference;
    private String paymentStatus;
    private String bookingStatus;
    private Guest guest;

    public Booking(String bookingId, String guestId, String roomId, String voucherNumber, LocalDate inDate, LocalDate outDate, String specialPreference, String paymentStatus, String bookingStatus) {
        this.bookingId = bookingId;
        this.guestId = guestId;
        this.roomId = roomId;
        this.voucherNumber = voucherNumber;
        this.inDate = inDate;
        this.outDate = outDate;
        this.specialPreference = specialPreference;
        this.paymentStatus = paymentStatus;
        this.bookingStatus = bookingStatus;
    }

    // Getters and setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getGuestId() { return guestId; }
    public void setGuestId(String guestId) { this.guestId = guestId; }
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public String getVoucherNumber() { return voucherNumber; }
    public void setVoucherNumber(String voucherNumber) { this.voucherNumber = voucherNumber; }
    public LocalDate getInDate() { return inDate; }
    public void setInDate(LocalDate inDate) { this.inDate = inDate; }
    public LocalDate getOutDate() { return outDate; }
    public void setOutDate(LocalDate outDate) { this.outDate = outDate; }
    public String getSpecialPreference() { return specialPreference; }
    public void setSpecialPreference(String specialPreference) { this.specialPreference = specialPreference; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }
    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }
}
