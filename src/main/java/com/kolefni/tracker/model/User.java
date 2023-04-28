package com.kolefni.tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "username", nullable = false, length = 20)
    private String username;

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
    @Column(name = "country", nullable = false, length = 30)
    private String country;

}
