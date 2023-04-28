package com.kolefni.tracker.dto;

import com.kolefni.tracker.model.Car;
import com.kolefni.tracker.model.Unit;
import lombok.Getter;
import lombok.Setter;

public class RideDto {

    @Getter
    @Setter
    private Unit unit;

    @Getter
    @Setter
    private double distance;

    @Getter
    @Setter
    private Car car;
}
