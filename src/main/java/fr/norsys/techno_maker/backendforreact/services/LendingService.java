package fr.norsys.techno_maker.backendforreact.services;

import fr.norsys.techno_maker.backendforreact.entities.Book;
import fr.norsys.techno_maker.backendforreact.entities.User;
import fr.norsys.techno_maker.backendforreact.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LendingService {

    private final UserService userService;
    private final BookService bookService;

    public List<Book> findAllUserBooks(long userId) {
        User user = userService.findUserById(userId);
        return user.getBooks();
    }

    public void lendBookToUser(long userId, long bookId) {
        User user = userService.findUserById(userId);
        Book book = bookService.findBookById(bookId);
        book.setAvailable(false);
        user.getBooks().add(book);
        bookService.saveBook(book);
        userService.saveUser(user);
    }

    public void returnBookFromUser(long userId, long bookId) {
        User user = userService.findUserById(userId);
        Book book = bookService.findBookById(bookId);
        book.setAvailable(true);
        user.getBooks().remove(book);
        bookService.saveBook(book);
        userService.saveUser(user);
    }

}
