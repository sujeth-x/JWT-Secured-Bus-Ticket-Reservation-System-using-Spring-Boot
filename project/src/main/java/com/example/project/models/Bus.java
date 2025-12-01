package com.example.project.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;       // e.g., TN-05-8790
    private String source;          // e.g., Chennai
    private String destination;     // e.g., Coimbatore

    private int totalSeats;
    private int availableSeats;

    private double price;           // Ticket price
}
