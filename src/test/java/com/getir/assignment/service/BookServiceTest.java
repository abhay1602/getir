package com.getir.assignment.service;

import com.getir.assignment.dtos.BookDto;
import com.getir.assignment.dtos.OrderDto;
import com.getir.assignment.model.Book;
import com.getir.assignment.model.Customer;
import com.getir.assignment.model.Order;
import com.getir.assignment.repository.BookRepository;
import com.getir.assignment.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    BookService bookService;

    @BeforeEach
    void initUseCase() {
        bookService = new BookService(bookRepository);
    }

    @Test
    void addBookToStockTest() {
        BookDto bookDto = new BookDto();
        Book book = new Book();
        book.setBookName("a");
        Mockito.when(bookRepository.save(ArgumentMatchers.any(Book.class))).thenReturn(book);
        Assertions.assertNotNull(bookService.addBookToStock(bookDto));
    }

    @Test
    void deleteBookFromStockTest() {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn(1);
        bookService.deleteBookFromStock(bookDto.getIsbn());
    }

    @Test
    void updateBookStockTest() {
        Book book = new Book();
        Mockito.when(bookRepository.save(ArgumentMatchers.any(Book.class))).thenReturn(book);
        bookService.updateBookStock(book);
    }
}