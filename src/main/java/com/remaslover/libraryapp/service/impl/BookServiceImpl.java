package com.remaslover.libraryapp.service.impl;


import com.remaslover.libraryapp.entity.Book;
import com.remaslover.libraryapp.repository.BookRepository;
import com.remaslover.libraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooksByOrderByDate() {
        return  bookRepository.findBooksByOrderByYear();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
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

    @Override
    public List<Book> getByTitleStartingWithIgnoreCase(String title) {
        return bookRepository.findByTitleStartingWithIgnoreCase(title);
    }

    @Override
    public Page<Book> getBooksByPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> getBooksByUserId(Long userId) {
        List<Book> books = bookRepository.findBooksByUserId(userId);
        return books.stream()
                .map(book -> {
                    book.setOverdue(book.isOverdue());
                    return book;
                })
                .collect(Collectors.toList());
    }
}
