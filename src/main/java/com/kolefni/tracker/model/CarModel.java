package com.kolefni.tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "models")
public class CarModel {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "model", nullable = false, length = 45)
    private String carModel;

    public CarModel(String carModel) {
        this.carModel = carModel;
    }
}
