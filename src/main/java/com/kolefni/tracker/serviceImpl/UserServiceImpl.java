package com.kolefni.tracker.serviceImpl;

import com.kolefni.tracker.dto.ElectricityDTO;
import com.kolefni.tracker.dto.UserDTO;
import com.kolefni.tracker.enums.Units;
import com.kolefni.tracker.model.Electricity;
import com.kolefni.tracker.model.User;
import com.kolefni.tracker.repository.UserRepository;
import com.kolefni.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public void createUser(UserDTO userDTO, String password) {
        // Convert UserDTO to User object
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(password);
        user.setCountry(userDTO.getCountry());
        userRepository.save(user);
        System.out.println("New user saved!");
        System.out.println(user.getPassword());
    }

    public String checkIfUserExists(UserDTO userDTO) {
        Map<String, Object> map = new HashMap<>();
        User userFromDatabase = userRepository.findByEmail(userDTO.getEmail());
        if(userFromDatabase != null){
            map.put("message", "User already exists!");
            return "signup";
        } else {
            String encodedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
            System.out.println(encodedPassword);
            createUser(userDTO, encodedPassword);
            map.put("message", "User created!");
            return "login";
        }
    }
}

