package com.kolefni.tracker.controller;

import com.kolefni.tracker.dto.ElectricityDTO;
import com.kolefni.tracker.dto.UserDTO;
import com.kolefni.tracker.serviceImpl.ElectricityServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private List<String> countries;
    private final ElectricityServiceImpl electricityService;

    public HomeController(ElectricityServiceImpl electricityService) {
        this.electricityService = electricityService;
    }

    @GetMapping()
    public String viewHomePage(@ModelAttribute ("electricityDTO") ElectricityDTO electricity,
                               Model model) {
            countries = electricityService.getCountries();
            electricity.setCountries(countries);
            model.addAttribute("electricityDTO", electricity);
            return "home";
    }

    @PostMapping()
    public String saveElectricityFootprint(@ModelAttribute("electricityDTO") ElectricityDTO electricityDTO,
                                           @RequestParam(value = "saveEntry", required = false) boolean saveEntry,
                                           UserDTO userDTO,
                                           Model model) throws IOException, InterruptedException {

        double footprint = electricityService.calculateElectricityCarbonFootprint(electricityDTO);
        electricityDTO.setCountries(countries);
        model.addAttribute("electricityDTO", electricityDTO);
        if (saveEntry) {
            // Save Electricity entity to database
            electricityService.createElectricityBill(electricityDTO, userDTO, footprint);
            // Set success message and return the view
            model.addAttribute("successMessage", "Electricity carbon footprint saved successfully!");
        } else {
            // Set success message and return the view
            model.addAttribute("successMessage", "Electricity carbon footprint calculated successfully!");
        }
        return "home";
    }

}
