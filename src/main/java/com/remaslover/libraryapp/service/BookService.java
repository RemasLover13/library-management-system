package com.remaslover.libraryapp.service;

import com.remaslover.libraryapp.entity.Book;

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
}
