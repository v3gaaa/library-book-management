package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173") // Permitir conexión desde React
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            return bookService.searchBooks(search);
        }
        return bookService.getAllBooks();
    }
}
