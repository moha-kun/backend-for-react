package fr.norsys.techno_maker.backendforreact.services;

import fr.norsys.techno_maker.backendforreact.entities.Book;
import fr.norsys.techno_maker.backendforreact.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LendService {

    private final UserService userService;

    public List<Book> findAllUserBooks(long userId) {
        User user = userService.findUserById(userId);
        return user.getBooks();
    }

}
