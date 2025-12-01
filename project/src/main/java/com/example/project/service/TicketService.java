package com.example.project.service;

import com.example.project.models.Bus;
import com.example.project.models.Passenger;
import com.example.project.models.Ticket;
import com.example.project.repository.BusRepository;
import com.example.project.repository.PassengerRepository;
import com.example.project.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BusRepository busRepository;
    private final PassengerRepository passengerRepository;

    public List<Ticket> getTicketsByPassenger(Long passengerId) {
        return ticketRepository.findByPassengerId(passengerId);
    }

    public List<Ticket> getTicketBus(Long busId) {
        return ticketRepository.findByBusId(busId);
    }

    public String bookTicket(Long passengerId, Long busId, LocalDate journeyDate) {
        Optional<Bus> busOpt = busRepository.findById(busId);
        Optional<Passenger> passengerOpt = passengerRepository.findById(passengerId);

        if (busOpt.isEmpty() || passengerOpt.isEmpty()) {
            return "Invalid Bus ID or Passenger ID";
        }

        Bus bus = busOpt.get();

        if (bus.getAvailableSeats() <= 0) {
            return "Bus is full. Please try another bus.";
        }

        Passenger passenger = passengerOpt.get();

        Ticket ticket = new Ticket();
        ticket.setTicketNumber("TICKET-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        ticket.setBus(bus);
        ticket.setPassenger(passenger);
        ticket.setJourneyDate(journeyDate);
        ticket.setPrice(bus.getPrice());
        ticket.setStatus("CONFIRMED");

        bus.setAvailableSeats(bus.getAvailableSeats() - 1);
        busRepository.save(bus);

        ticketRepository.save(ticket);

        return "Ticket booked successfully! Ticket No: " + ticket.getTicketNumber();
    }
    public String cancelTicket(Long ticketId) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
        if (ticketOpt.isEmpty()) return "Ticket not found";

        Ticket ticket = ticketOpt.get();
        Bus bus = ticket.getBus();

        ticket.setStatus("CANCELLED");
        bus.setAvailableSeats(bus.getAvailableSeats() + 1);

        busRepository.save(bus);
        ticketRepository.save(ticket);

        return "Ticket cancelled successfully!";
    }

}
