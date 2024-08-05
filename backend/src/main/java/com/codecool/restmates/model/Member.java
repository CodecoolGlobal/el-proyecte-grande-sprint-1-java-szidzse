package com.codecool.restmates.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<Accommodation> accommodations = new ArrayList<>();

    @OneToMany(mappedBy = "guest")
    private  List<Reservation> reservations = new ArrayList<>();
}
