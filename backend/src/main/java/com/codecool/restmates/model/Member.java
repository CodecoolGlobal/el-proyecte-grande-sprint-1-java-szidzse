package com.codecool.restmates.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Member {
    private final String id;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;

    private final List<Message> messages;
    private final Set<Accommodation> ownerOfAccommodations;
    private final List<Booking> bookings;


    public Member(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        messages = new ArrayList<>();
        ownerOfAccommodations = new HashSet<>();
        bookings = new ArrayList<>();
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", messages=" + messages +
                ", ownerOfAccommodations=" + ownerOfAccommodations +
                ", bookings=" + bookings +
                '}';
    }
}
