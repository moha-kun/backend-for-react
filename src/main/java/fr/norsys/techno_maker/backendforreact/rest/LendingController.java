package fr.norsys.techno_maker.backendforreact.rest;

import fr.norsys.techno_maker.backendforreact.entities.Book;
import fr.norsys.techno_maker.backendforreact.services.LendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lending")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LendingController {

    private final LendingService lendingService;

    @GetMapping("/{userId}")
    public List<Book> findAllUserBooks(@PathVariable long userId) {
        return lendingService.findAllUserBooks(userId);
    }

    @PostMapping
    public void lendBookToUser(@RequestParam long userId, @RequestParam long bookId) {
        lendingService.lendBookToUser(userId, bookId);
    }

    @DeleteMapping
    public void returnBookFromUser(@RequestParam long userId, @RequestParam long bookId) {
        lendingService.returnBookFromUser(userId, bookId);
    }

}
