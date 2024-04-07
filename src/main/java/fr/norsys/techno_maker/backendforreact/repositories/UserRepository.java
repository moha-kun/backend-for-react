package fr.norsys.techno_maker.backendforreact.repositories;

import fr.norsys.techno_maker.backendforreact.entities.Book;
import fr.norsys.techno_maker.backendforreact.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            select user from User user
            inner join user.books book
            where book = :book
            """)
    public List<User> findUsersByBook(Book book);

}
