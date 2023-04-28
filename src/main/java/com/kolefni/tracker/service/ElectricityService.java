package com.kolefni.tracker.service;

import com.kolefni.tracker.dto.ElectricityDTO;
import com.kolefni.tracker.dto.UserDTO;
import com.kolefni.tracker.model.Electricity;

public interface ElectricityService {

    public void createElectricityBill(ElectricityDTO electricityDTO,
                                      UserDTO userDTO,
                                      Double footprint);
    public Electricity getElectricityBillsByUserId(Long id);
}
