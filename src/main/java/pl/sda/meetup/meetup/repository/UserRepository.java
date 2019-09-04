package pl.sda.meetup.meetup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.meetup.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
