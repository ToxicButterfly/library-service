package com.example.libraryservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class BookStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer bookId;
    @Basic
    @Temporal(TemporalType.DATE)
    private LocalDate takenDate;
    @Basic
    @Temporal(TemporalType.DATE)
    private LocalDate returnDate;
}
