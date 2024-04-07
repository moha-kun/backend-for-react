package fr.norsys.techno_maker.backendforreact.rest;

import fr.norsys.techno_maker.backendforreact.entities.Book;
import fr.norsys.techno_maker.backendforreact.entities.User;
import fr.norsys.techno_maker.backendforreact.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        System.out.println(user);
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.deleteUserById(id);
    }

}
