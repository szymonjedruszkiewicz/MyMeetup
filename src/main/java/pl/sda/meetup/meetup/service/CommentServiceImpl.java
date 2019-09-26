package pl.sda.meetup.meetup.service;

import org.springframework.stereotype.Service;
import pl.sda.meetup.meetup.dto.CommentDto;
import pl.sda.meetup.meetup.exception.NoEventException;
import pl.sda.meetup.meetup.exception.NoUserException;
import pl.sda.meetup.meetup.mapper.manual.ManualCommentMapper;
import pl.sda.meetup.meetup.model.Comment;
import pl.sda.meetup.meetup.repository.CommentRepository;
import pl.sda.meetup.meetup.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ManualCommentMapper manualCommentMapper;
    private final UserContextService userContextService;
    private final UserService userService;
    private final EventRepository eventRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ManualCommentMapper manualCommentMapper, UserContextService userContextService, UserService userService, EventRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.manualCommentMapper = manualCommentMapper;
        this.userContextService = userContextService;
        this.userService = userService;
        this.eventRepository = eventRepository;
    }

    @Override
    public void saveComment(CommentDto commentDto) {
        commentRepository.save(manualCommentMapper.commentDtoToComment(commentDto));
    }

    @Override
    public void saveCommentString(String comment, Long eventId) {
        Comment commentToBeSaved = Comment.builder()
                .user(userService.findUserByEmail(userContextService.getLoggedUserName()).orElseThrow(() -> new NoUserException("user not found in db")))
                .dateOfCreation(LocalDateTime.now())
                .description(comment)
                .event(eventRepository.findById(eventId).orElseThrow(() -> new NoEventException("event not found in db")))
                .build();
        commentRepository.save(commentToBeSaved);
    }

    @Override
    public List<CommentDto> getCommentByEvent(Long eventId) {
        List<Comment> commentList = commentRepository.findByEvent_IdOrderByDateOfCreationDesc(eventId);
        return commentList.stream()
                .map(manualCommentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }
}
