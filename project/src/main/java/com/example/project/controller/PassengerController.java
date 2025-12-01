package com.example.project.controller;

import com.example.project.models.Ticket;
import com.example.project.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{passengerId}/tickets")
    public ResponseEntity<List<Ticket>> gettickets(@PathVariable Long passengerId){
        List<Ticket> Tickets=ticketService.getTicketsByPassenger(passengerId);

        if(Tickets.isEmpty()){
            return new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Tickets);
    }

}
