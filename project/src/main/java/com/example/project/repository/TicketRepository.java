package com.example.project.repository;

import com.example.project.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

          List<Ticket> findByPassengerId(Long passengerId);
          List<Ticket> findByBusId(Long busId);
}
