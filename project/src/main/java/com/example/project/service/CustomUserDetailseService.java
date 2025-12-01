package com.example.project.service;

import com.example.project.models.Passenger;
import com.example.project.repository.PassengerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailseService implements UserDetailsService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Passenger passenger=passengerRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found:"+email));

        return new User(
            passenger.getEmail(),
            passenger.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                    );
        }
    }

