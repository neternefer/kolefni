package com.kolefni.tracker.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

public class FlightDto {

    @Getter
    @Setter
    private double distance;

    @Getter
    @Setter
    private String depAirport;

    @Getter
    @Setter
    private String destAirport;

    @Getter
    @Setter
    private String classType;
}
