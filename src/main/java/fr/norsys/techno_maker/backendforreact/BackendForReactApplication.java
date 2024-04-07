package fr.norsys.techno_maker.backendforreact;

import fr.norsys.techno_maker.backendforreact.entities.Book;
import fr.norsys.techno_maker.backendforreact.entities.User;
import fr.norsys.techno_maker.backendforreact.services.BookService;
import fr.norsys.techno_maker.backendforreact.services.LendingService;
import fr.norsys.techno_maker.backendforreact.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendForReactApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendForReactApplication.class, args);
    }

    @Bean @Autowired
    public CommandLineRunner setUp(BookService bookService, UserService userService, LendingService lendingService) {
        return (String ...args) -> {
            for (int i = 1; i < 10; i++) {
                Book book = new Book();
                book.setTitle("Title" + i);
                book.setGenre("Genre" + i);
                book.setAuthor("Author" + i);
                book.setAvailable(true);
                bookService.createBook(book);
            }
            User user = new User();
            user.setFirstName("FirstName");
            user.setLastName("LastName");
            userService.createUser(user);
            lendingService.lendBookToUser(1L, 1L);
        };
    }

}
