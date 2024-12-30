package com.remaslover.libraryapp.repository;

import com.remaslover.libraryapp.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b JOIN b.user u WHERE u.id = :userId")
    List<Book> findBooksByUserId(@Param("userId") Long userId);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.user WHERE b.id = :bookId")
    Optional<Book> findBookWithUser(@Param("bookId") Long bookId);

    @Query("SELECT b FROM Book b")
    List<Book> findTop5Books(Pageable pageable);

    List<Book> findBooksByOrderByYear();

    List<Book> findByTitleStartingWithIgnoreCase(String title);

    Page<Book> findAll(Pageable pageable);



}
