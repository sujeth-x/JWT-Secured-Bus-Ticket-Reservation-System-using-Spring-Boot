package com.example.project.controller;

import com.example.project.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/Book")
    public ResponseEntity<String> bookticket(@RequestParam Long PassengerId, @RequestParam Long BusId,@RequestParam String journeyDate){
        try{
            LocalDate date = LocalDate.parse(journeyDate);
            String response = ticketService.bookTicket(PassengerId,BusId,date);
            if(response.contains("Invalid")||response.contains("full")){
                return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error in Booking Ticket:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{ticketId}/cancel")
    public ResponseEntity<String> cancelTicket(@PathVariable Long ticketId) {
        String message = ticketService.cancelTicket(ticketId);
        return ResponseEntity.ok(message);
    }

}
