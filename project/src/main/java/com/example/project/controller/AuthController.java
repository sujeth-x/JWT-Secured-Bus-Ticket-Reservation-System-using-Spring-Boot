package com.example.project.controller;

import com.example.project.models.Passenger;
import com.example.project.repository.PassengerRepository;
import com.example.project.utils.JwtUtil;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PassengerRepository passengerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Passenger passenger){
        if (passengerRepository.findByEmail(passenger.getEmail()).isPresent()) {
            return new  ResponseEntity<>("User already exists with this email.",HttpStatus.BAD_REQUEST);

        }

        passenger.setPassword(passwordEncoder.encode(passenger.getPassword()));
        passengerRepository.save(passenger);
        return ResponseEntity.ok( "User Registered successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Passenger passenger) {
        Passenger exists = passengerRepository.findByEmail(passenger.getEmail())
                .orElse(null);

        if (exists == null || !passwordEncoder.matches(passenger.getPassword(), exists.getPassword())) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(exists.getEmail());
        return ResponseEntity.ok(token);
    }

}
