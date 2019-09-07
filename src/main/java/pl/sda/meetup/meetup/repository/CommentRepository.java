package pl.sda.meetup.meetup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.meetup.meetup.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByEvent_IdOrderByDateOfCreation(Long eventId);

    List<Comment> findByEvent_IdOrderByDateOfCreationDesc(Long eventId);
}
