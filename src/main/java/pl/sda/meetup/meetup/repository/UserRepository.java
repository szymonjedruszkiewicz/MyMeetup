package pl.sda.meetup.meetup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.meetup.model.UserDAO;

public interface UserRepository extends JpaRepository<UserDAO, Long> {
}
