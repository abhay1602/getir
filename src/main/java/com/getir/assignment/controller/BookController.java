package com.getir.assignment.controller;

import com.getir.assignment.dtos.BookDto;
import com.getir.assignment.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/api/v1/book"}, produces = APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    private static final Logger logger =
            LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public void addBookToStock(@RequestBody BookDto bookDto) {
        bookService.addBookToStock(bookDto);
    }

    @DeleteMapping("/delete/{isbn}")
    public void removeBookFromStock(@PathVariable Integer isbn) {
        bookService.deleteBookFromStock(isbn);
    }

}
