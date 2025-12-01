package com.example.project.controller;

import com.example.project.models.Bus;
import com.example.project.models.Ticket;
import com.example.project.service.BusService;
import com.example.project.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        Bus savedBus = busService.addBus(bus);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBus);
    }

    @GetMapping("/{busId}/tickets")
    public ResponseEntity<List<Ticket>> getTicketByBus(@PathVariable Long busId) {
        List<Ticket> tickets = ticketService.getTicketBus(busId);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bus>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

}

