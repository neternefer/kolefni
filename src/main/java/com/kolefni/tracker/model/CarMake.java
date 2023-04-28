package com.kolefni.tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "makes")
public class CarMake {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "make", nullable = false, length = 45)
    private String carMake;

    public CarMake(String carMake) {
        this.carMake = carMake;
    }
}
