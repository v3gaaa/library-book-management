package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

    // Nueva ruta que devuelve un JSON hardcodeado con libros de prueba
    @GetMapping("/mock-books")
    public List<Book> getMockBooks(@RequestParam(required = false) String search) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Book> books = Arrays.asList(
            new Book(1, "The Pragmatic Programmer", "Andrew Hunt", "Programming", dateFormat.parse("1999-10-30"), "978-0201616224", true),
            new Book(2, "Clean Code", "Robert C. Martin", "Programming", dateFormat.parse("2008-08-01"), "978-0132350884", true),
            new Book(3, "The Mythical Man-Month", "Frederick P. Brooks Jr.", "Software Engineering", dateFormat.parse("1975-01-01"), "978-0201835957", false),
            new Book(4, "Introduction to the Theory of Computation", "Michael Sipser", "Computer Science", dateFormat.parse("2005-02-15"), "978-0534950972", true),
            new Book(5, "Design Patterns", "Erich Gamma", "Software Engineering", dateFormat.parse("1994-10-31"), "978-0201633610", true),
            new Book(6, "Refactoring", "Martin Fowler", "Programming", dateFormat.parse("1999-07-08"), "978-0201485677", true),
            new Book(7, "Code Complete", "Steve McConnell", "Programming", dateFormat.parse("2004-06-09"), "978-0735619678", true),
            new Book(8, "The Art of Computer Programming", "Donald E. Knuth", "Computer Science", dateFormat.parse("1968-01-01"), "978-0201896831", false),
            new Book(9, "Structure and Interpretation of Computer Programs", "Harold Abelson", "Computer Science", dateFormat.parse("1996-07-25"), "978-0262510875", true),
            new Book(10, "Introduction to Algorithms", "Thomas H. Cormen", "Computer Science", dateFormat.parse("2009-07-31"), "978-0262033848", true),
            new Book(11, "The C Programming Language", "Brian W. Kernighan", "Programming", dateFormat.parse("1988-04-01"), "978-0131103627", true),
            new Book(12, "Operating System Concepts", "Abraham Silberschatz", "Computer Science", dateFormat.parse("2008-07-29"), "978-0470128725", true),
            new Book(13, "Artificial Intelligence: A Modern Approach", "Stuart Russell", "Computer Science", dateFormat.parse("2009-12-11"), "978-0136042594", true),
            new Book(14, "Computer Networks", "Andrew S. Tanenbaum", "Computer Science", dateFormat.parse("2002-07-19"), "978-0132126953", true),
            new Book(15, "Database System Concepts", "Abraham Silberschatz", "Computer Science", dateFormat.parse("2005-05-18"), "978-0073523323", true)
        );

        if (search != null && !search.trim().isEmpty()) {
            String searchLower = search.toLowerCase();
            books = books.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(searchLower)
                            || book.getAuthor().toLowerCase().contains(searchLower)
                            || book.getGenre().toLowerCase().contains(searchLower)
                            || book.getIsbn().contains(search))
                    .toList();
        }

        return books;
    }
}
