package fr.norsys.techno_maker.backendforreact.services;

import fr.norsys.techno_maker.backendforreact.entities.Book;
import fr.norsys.techno_maker.backendforreact.entities.User;
import fr.norsys.techno_maker.backendforreact.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        user.setId(0L);
        return userRepository.save(user);
    }

    public User updateUser(long id, User user) {
        User oldUser = findUserById(id);
        user.setBooks(oldUser.getBooks());
        user.setId(id);
        return userRepository.save(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no User with such ID"));
    }

    public List<User> findUsersByBook(Book book) {
        return userRepository.findUsersByBook(book);
    }

    public void deleteUserById(long id) {
        User oldUser = findUserById(id);
        List<Book> books = oldUser.getBooks();
        books.forEach(book -> book.setAvailable(true));
        userRepository.save(oldUser);
        oldUser.setBooks(new ArrayList<>());
        userRepository.deleteById(id);
    }

}
