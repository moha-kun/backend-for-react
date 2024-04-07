package fr.norsys.techno_maker.backendforreact.rest;

import fr.norsys.techno_maker.backendforreact.entities.Book;
import fr.norsys.techno_maker.backendforreact.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAllBooks();
    }

    @GetMapping("/available")
    public List<Book> findAvailableBooks() {
        return bookService.findAvailableBooks();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        bookService.deleteBookById(id);
    }

}
