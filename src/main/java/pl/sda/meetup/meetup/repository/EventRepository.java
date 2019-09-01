package pl.sda.meetup.meetup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.meetup.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
