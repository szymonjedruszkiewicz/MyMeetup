package pl.sda.meetup.meetup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.meetup.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findEventsByTitleContains(String query);

    List<Event> findEventsByStartIsAfterAndEndIsBefore(LocalDate start, LocalDate end);

}
