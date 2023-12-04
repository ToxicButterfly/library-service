package com.example.libraryservice.controller;

import com.example.libraryservice.model.Book;
import com.example.libraryservice.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @GetMapping("getFree")
    public ResponseEntity<List<Book>> getFree() {
        return libraryService.getFree();
    }

    @PostMapping("updateStatus/{id}")
    public String updateStatus(@PathVariable int id){
        return libraryService.updateStatus(id);
    }

    //Method only to be called from the LIBRARIAN-SERVICE
    @PostMapping("addBook/{id}")
    public ResponseEntity<String> addBook(@PathVariable int id) {
        return libraryService.addBook(id);
    }

    //Method only to be called from the LIBRARIAN-SERVICE
    @DeleteMapping("deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        return libraryService.deleteBook(id);
    }
}
