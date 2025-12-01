package com.example.project.service;

import com.example.project.models.Bus;
import com.example.project.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

   public List<Bus> getallbus(){
        return busRepository.findAll();
    }

    public List<Bus> searchbus(String source,String destination){
       return busRepository.findBySourceAndDestination(source, destination);
    }

    public Bus addBus(Bus bus){
       bus.setAvailableSeats(bus.getTotalSeats());
       return busRepository.save(bus);
    }
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
    public Optional<Bus> getBusBYId(Long id){
       return busRepository.findById(id);
    }
}
