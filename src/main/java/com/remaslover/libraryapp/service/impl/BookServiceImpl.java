package com.remaslover.libraryapp.service.impl;


import com.remaslover.libraryapp.entity.Book;
import com.remaslover.libraryapp.repository.BookRepository;
import com.remaslover.libraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> getUserByBookId(Long id) {
        return bookRepository.findBookWithUser(id);
    }

    @Override
    public List<Book> getTop5Books() {
        Pageable pageable = PageRequest.of(0, 5);
        return bookRepository.findTop5Books(pageable);
    }
}
