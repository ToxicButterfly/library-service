package com.example.libraryservice.feign;

import com.example.libraryservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("LIBRARIAN-SERVICE")
public interface LibraryFeign {
    @GetMapping("demo/book/get/ID/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id);

}
