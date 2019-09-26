package pl.sda.meetup.meetup.service;

import pl.sda.meetup.meetup.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void saveComment(CommentDto commentDto);

    void saveCommentString(String comment, Long eventId);

    List<CommentDto> getCommentByEvent(Long eventId);
}
