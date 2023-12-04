package com.example.libraryservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String ISBN;
    private String title;
    private String genre;
    private String description;
    private String author;
}
