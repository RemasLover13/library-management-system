package com.remaslover.libraryapp.service;

import com.remaslover.libraryapp.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookService {

     List<Book> getAllBooks();

     Book getById(Long id);

     Book save(Book book);

     Book update(Book book);

     void delete(Long id);

     Optional<Book> getUserByBookId(Long id);

     List<Book> getTop5Books();

     List<Book> getAllBooksByOrderByDate();

     List<Book> getBooksByUserId(Long userId);

     List<Book> getByTitleStartingWithIgnoreCase(String title);

     Page<Book> getBooksByPage(Pageable pageable);
}
