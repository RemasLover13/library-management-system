package com.remaslover.libraryapp.integration;


import com.remaslover.libraryapp.entity.Book;
import com.remaslover.libraryapp.entity.User;
import com.remaslover.libraryapp.repository.BookRepository;
import com.remaslover.libraryapp.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class IBookServiceTest {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    private Book testBook;
    private User testUser;

    @BeforeEach
    public void setUp() {
        // Создание книги для тестов
        testUser = new User(1L, "Ivan Ivanov", "2001", List.of());
        testBook = new Book("Effective Java", "Joshua Bloch", 2008, testUser);
        bookRepository.save(testBook);
    }

    @Test
    public void testGetBookById() {
        Book foundBook = bookService.getById(testBook.getId());
        assertNotNull(foundBook, "Book should not be null");
        assertEquals(testBook.getId(), foundBook.getId(), "Book ID should match");
        assertEquals(testBook.getTitle(), foundBook.getTitle(), "Book title should match");
        assertEquals(testBook.getAuthor(), foundBook.getAuthor(), "Book author should match");
    }

    @Test
    public void testGetBooks() {
        List<Book> books = bookService.getAllBooks();
        assertNotNull(books, "Books list should not be null");
        assertFalse(books.isEmpty(), "Books list should not be empty");
    }

    @Test
    public void testSaveBook() {
        Book newBook = new Book("Clean Code", "Robert C. Martin", 2008, testUser);
        Book savedBook = bookService.save(newBook);
        assertNotNull(savedBook, "Saved book should not be null");
        assertEquals(newBook.getTitle(), savedBook.getTitle(), "Saved book's title should match");
        assertEquals(newBook.getAuthor(), savedBook.getAuthor(), "Saved book's author should match");
    }

    @Test
    public void testDeleteBook() {
        Long bookId = testBook.getId();
        bookService.delete(bookId);
        assertFalse(bookRepository.existsById(bookId), "Book should be deleted");
    }

    @Test
    public void testGetBookById_Mvc() throws Exception {
        mockMvc.perform(get("/books/" + testBook.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testBook.getId()))
                .andExpect(jsonPath("$.title").value(testBook.getTitle()))
                .andExpect(jsonPath("$.author").value(testBook.getAuthor()));
    }

    @Test
    public void testCreateBook_Mvc() throws Exception {
        Book newBook = new Book("Design Patterns", "Erich Gamma, Richard Helm", 1994, testUser);
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"" + newBook.getTitle() + "\", \"author\": \"" + newBook.getAuthor() + "\" " + newBook.getYear() + "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(newBook.getTitle()))
                .andExpect(jsonPath("$.author").value(newBook.getAuthor()));
    }

    @Test
    public void testDeleteBook_Mvc() throws Exception {
        Long bookId = testBook.getId();
        mockMvc.perform(delete("/books/" + bookId))
                .andExpect(status().isNoContent());

        assertFalse(bookRepository.existsById(bookId), "Book should be deleted");
    }
}

