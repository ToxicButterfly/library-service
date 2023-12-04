package com.example.libraryservice.service;

import com.example.libraryservice.dao.LibraryDao;
import com.example.libraryservice.feign.LibraryFeign;
import com.example.libraryservice.model.Book;
import com.example.libraryservice.model.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    LibraryDao libraryDao;
    @Autowired
    LibraryFeign libraryFeign;

    public ResponseEntity<List<Book>> getFree() {
        List<Integer> bookIDs = libraryDao.findAllFree();
        List<Book> books = new ArrayList<>();
        for (int bookID: bookIDs) {
            books.add(libraryFeign.getBookById(bookID).getBody());
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public String updateStatus(int id) {
        BookStatus book = libraryDao.findBookStatusByBookId(id).get();
        if (book.getTakenDate()==null) {
            book.setTakenDate(LocalDate.now());
            book.setReturnDate(LocalDate.now().plusDays(14));
            libraryDao.save(book);
            return "Book was taken";
        }
        else {
            book.setTakenDate(null);
            book.setReturnDate(null);
            libraryDao.save(book);
            return "Book was returned";
        }
    }

    public ResponseEntity<String> addBook(int id) {
        if (libraryDao.findById(id).isEmpty()) {
            BookStatus book = new BookStatus();
            book.setBookId(id);
            book.setTakenDate(null);
            book.setReturnDate(null);
            libraryDao.save(book);
            return new ResponseEntity<>("Book was saved", HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("There's already such book", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> deleteBook(int id) {
        if(libraryDao.findBookStatusByBookId(id).isPresent()) {
            libraryDao.delete(libraryDao.findBookStatusByBookId(id).get());
            return new ResponseEntity<>("Book was deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("There's no such book", HttpStatus.NOT_FOUND);
    }
}
