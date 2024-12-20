package com.remaslover.libraryapp.unit.service;

import com.remaslover.libraryapp.entity.Book;
import com.remaslover.libraryapp.repository.BookRepository;
import com.remaslover.libraryapp.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Создаем пример книги
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Author Name");
        book.setYear(2023);
    }

    @Test
    void testGetAllBooks() {
        // Подготовка данных
        List<Book> books = List.of(book);
        when(bookRepository.findAll()).thenReturn(books);

        // Вызов метода
        List<Book> result = bookService.getAllBooks();

        // Проверки
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());

        // Проверяем, что метод findAll() был вызван один раз
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetById() {
        // Подготовка данных
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Вызов метода
        Book result = bookService.getById(1L);

        // Проверки
        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());

        // Проверяем, что метод findById() был вызван один раз
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveBook() {
        // Подготовка данных
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Вызов метода
        Book result = bookService.save(book);

        // Проверки
        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());

        // Проверяем, что метод save() был вызван один раз
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testUpdateBook() {
        // Подготовка данных
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Вызов метода
        Book result = bookService.update(book);

        // Проверки
        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());

        // Проверяем, что метод save() был вызван один раз
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testDeleteBook() {
        // Вызов метода
        bookService.delete(1L);

        // Проверяем, что метод deleteById() был вызван один раз
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetUserByBookId() {
        // Подготовка данных
        when(bookRepository.findBookWithUser(1L)).thenReturn(Optional.of(book));

        // Вызов метода
        Optional<Book> result = bookService.getUserByBookId(1L);

        // Проверки
        assertTrue(result.isPresent());
        assertEquals("Test Book", result.get().getTitle());

        // Проверяем, что метод findBookWithUser() был вызван один раз
        verify(bookRepository, times(1)).findBookWithUser(1L);
    }

    @Test
    void testGetTop5Books() {
        // Подготовка данных
        Pageable pageable = PageRequest.of(0, 5);
        List<Book> books = List.of(book);
        when(bookRepository.findTop5Books(pageable)).thenReturn(books);

        // Вызов метода
        List<Book> result = bookService.getTop5Books();

        // Проверки
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());

        // Проверяем, что метод findTop5Books() был вызван один раз с правильным Pageable
        verify(bookRepository, times(1)).findTop5Books(pageable);
    }
}
