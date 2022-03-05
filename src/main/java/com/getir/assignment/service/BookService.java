package com.getir.assignment.service;

import com.getir.assignment.dtos.BookDto;
import com.getir.assignment.exception.CustomerValidationException;
import com.getir.assignment.model.Book;
import com.getir.assignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBookToStock(BookDto bookDto) {
        return bookRepository.save(mapBookDtoToBook(bookDto));
    }

    public void deleteBookFromStock(Integer isbn) {
        bookRepository.deleteById(isbn);
    }

    Book mapBookDtoToBook(BookDto bookDto) {
        if (bookDto == null) {
            throw new CustomerValidationException("Book Request cannot be empty");
        }
        Book book = new Book();
        book.setAdded_date(LocalDateTime.now());
        book.setPrice(bookDto.getPrice());
        book.setIsbn(bookDto.getIsbn());
        book.setStatus(bookDto.getStatus());
        book.setBookName(bookDto.getBookName());
        return book;
    }

    public void updateBookStock(Book book) {
        bookRepository.save(book);
    }
}
