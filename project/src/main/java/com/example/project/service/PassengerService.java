package com.example.project.service;

import com.example.project.models.Passenger;
import com.example.project.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger registerPassenger(Passenger passenger){
        return passengerRepository.save(passenger);
    }

    public Passenger getPassenger(String email) {
        return passengerRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Passenger not found:"+email));
    }
}
