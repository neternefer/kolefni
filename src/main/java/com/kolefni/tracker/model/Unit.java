package com.kolefni.tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "units")
public class Unit {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "distance", nullable = false, length = 10)
    private String distanceUnit;

    @Getter
    @Setter
    @Column(name = "weight", nullable = false, length = 10)
    private String weightUnit;

    public Unit(String distanceUnit,
                String weightUnit) {
        this.distanceUnit = distanceUnit;
        this.weightUnit = weightUnit;
    }
}
