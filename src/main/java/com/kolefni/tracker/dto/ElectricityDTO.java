package com.kolefni.tracker.dto;

import com.kolefni.tracker.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class ElectricityDTO {

    @Getter
    @Setter
    private String unit;

    @Getter
    @Setter
    private double amount;

    @Getter
    @Setter
    private String country;

    @Getter
    @Setter
    private List<String> countries;

    @Getter
    @Setter
    private double footprint;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private User user;
}

