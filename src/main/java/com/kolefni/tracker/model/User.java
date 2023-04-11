package com.kolefni.tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false, length = 64)
    private String password;

    @Getter
    @Setter
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Getter
    @Setter
    @Column(name = "country", nullable = false, length = 20)
    private String country;




}
