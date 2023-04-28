package com.kolefni.tracker.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kolefni.tracker.dto.ElectricityDTO;
import com.kolefni.tracker.dto.UserDTO;
import com.kolefni.tracker.enums.Units;
import com.kolefni.tracker.model.Electricity;
import com.kolefni.tracker.repository.ElectricityRepository;
import com.kolefni.tracker.repository.UserRepository;
import com.kolefni.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElectricityServiceImpl {

    private final ElectricityRepository electricityRepository;
    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    private String apiUrl = "electricity_estimate";

    @Autowired
    public ElectricityServiceImpl(ElectricityRepository electricityRepository,
                                  UserRepository userRepository, ObjectMapper objectMapper) {
        this.electricityRepository = electricityRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Autowired
    public UserService userService;

    @Autowired
    public ApiServiceImpl apiService;

    public void createElectricityBill(ElectricityDTO electricityDTO,
                                      UserDTO userDTO,
                                      Double footprint) {
        // Convert ElectricityDto to Electricity object
        Electricity electricity = new Electricity();
        electricity.setUnit(Units.valueOf(electricityDTO.getUnit()));
        electricity.setAmount(electricityDTO.getAmount());
        electricity.setCountry(userDTO.getCountry());
        electricity.setDate(LocalDate.now());
        electricity.setUser(userService.getCurrentUser());
        electricity.setFootprint(footprint);
        electricityRepository.save(electricity);
        System.out.println("New bill saved!");
    }

    public Double calculateElectricityCarbonFootprint(ElectricityDTO electricity) throws IOException, InterruptedException {
        //Add query params
        String paramValues = getElectricityData(electricity);
        //Call to the API
        String apiResponse = apiService.makeApiCall(apiUrl, paramValues);
        //Extract electricity footprint
        Double footprint = extractData(apiResponse, electricity.getUnit());
        electricity.setFootprint(footprint);
        return footprint;
    }

    public String getElectricityData(ElectricityDTO electricity) {
       String queryParams = "country_name=" + electricity.getCountry() + "&electricity_value=" + electricity.getAmount() + "&electricity_unit=" + electricity.getUnit();
       return queryParams;
    }

    public Double extractData(String jsonData, String unit) throws JsonProcessingException {
        String result;
        JsonNode jsonNode = objectMapper.readTree(jsonData);
        if(unit == "mWh") {
            result = jsonNode.get("data").get("co2e_ml").asText();
        } else {
            result = jsonNode.get("data").get("co2e_kg").asText();
            System.out.println(result);
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("Here's a for loop!");
        }
        return Double.parseDouble(result);
    }

    public List<String> getCountries() {
        List<String> countries = Arrays.asList("Australia", "Austria", "Bangladesh", "Belgium", "Bhutan", "Brunei", "Bulgaria", "Cambodia", "Canada", "China", "Croatia", "Cyprus", "Czechia", "Denmark", "Estonia", "EU-27", "Finland", "France", "Germany", "Greece", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Ireland", "Italy", "Japan", "Laos", "Latvia", "Lithuania", "Luxembourg", "Macao", "Malaysia", "Maldives", "Malta", "Mongolia", "Myanmar", "Nepal", "Netherlands", "New Zealand", "North Korea", "Norway", "Pakistan", "Papua New Guinea", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Singapore", "Slovakia", "Slovenia", "South Korea", "Spain", "Sri Lanka", "Sweden", "Taiwan", "Thailand", "Turkey", "UK", "USA", "Vietnam");
        return countries;
    }

    public List<Electricity> getElectricityBillsByUserId(Long id) {
        //Retrieves all electricity bills associated with the specified user id
        //Could be searched by the user object also, which one is better?
        return electricityRepository.findByUserId(id);
    }
    public void updateElectricityBill(ElectricityDTO electricityDto) {
        //Updates an existing electricity bill based on the information in the provided ElectricityDto.
    }
    public void deleteElectricityBillById(Long id) {
        //Deletes the electricity bill with the specified ID.
        electricityRepository.deleteById(id);
    }

    public double getAverageUsageByUser(Long id) {
        List<Electricity> electricityList = electricityRepository.findByUserId(id);
        double totalUsage = electricityList.stream().mapToDouble(Electricity::getAmount).sum();
        return totalUsage / electricityList.size();
    }

    public Map<Integer, Double> getYearlyUsageByUser(Long id) {
        List<Electricity> electricityList = electricityRepository.findByUserId(id);
        Map<Integer, Double> yearlyUsage = new HashMap<>();
        for (Electricity electricity : electricityList) {
            int year = electricity.getDate().getYear();
            double amount = electricity.getAmount();
            if (yearlyUsage.containsKey(year)) {
                amount += yearlyUsage.get(year);
            }
            yearlyUsage.put(year, amount);
        }
        return yearlyUsage;
    }

    public List<Electricity> getAllElectricityBills() {
        return null;
    }
}
