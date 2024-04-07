package fr.norsys.techno_maker.backendforreact.services;

import fr.norsys.techno_maker.backendforreact.entities.Book;
import fr.norsys.techno_maker.backendforreact.entities.User;
import fr.norsys.techno_maker.backendforreact.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserService userService;

    public Book createBook(Book book) {
        book.setId(0L);
        return bookRepository.save(book);
    }

    public Book updateBook(long id, Book book) {
        if (!bookRepository.existsById(id))
            throw new RuntimeException("There is no Book with such ID");
        book.setId(id);
        return bookRepository.save(book);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findAvailableBooks() {
        Specification<Book> available = bookRepository.available();
        return bookRepository.findAll(available);
    }

    public Book findBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no Book with such ID"));
    }

    public void deleteBookById(long id) {
        if (!bookRepository.existsById(id))
            throw new RuntimeException("There is no Book with such ID");
        Book book = findBookById(id);
        List<User> users = userService.findUsersByBook(book);
        users.forEach(user -> user.getBooks().remove(book));
        userService.saveAll(users);
        bookRepository.deleteById(id);
    }

}
