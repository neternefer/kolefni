package com.kolefni.tracker.dto;

import com.kolefni.tracker.model.CarMake;
import com.kolefni.tracker.model.CarModel;
import lombok.Getter;
import lombok.Setter;

public class CarDto {

    @Getter
    @Setter
    private CarMake carMake;

    @Getter
    @Setter
    private CarModel carModel;
}
