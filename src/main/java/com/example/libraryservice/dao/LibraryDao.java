package com.example.libraryservice.dao;

import com.example.libraryservice.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryDao extends JpaRepository<BookStatus, Integer> {

    @Query(value = "SELECT book_id FROM book_status WHERE taken_date IS NULL" , nativeQuery = true)
    List<Integer> findAllFree();

    Optional<BookStatus> findBookStatusByBookId(int id);

}
