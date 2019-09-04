package pl.sda.meetup.meetup.service;

import org.springframework.stereotype.Service;
import pl.sda.meetup.meetup.dto.CommentDto;
import pl.sda.meetup.meetup.mapper.manual.ManualCommentMapper;
import pl.sda.meetup.meetup.model.Comment;
import pl.sda.meetup.meetup.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ManualCommentMapper manualCommentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ManualCommentMapper manualCommentMapper) {
        this.commentRepository = commentRepository;
        this.manualCommentMapper = manualCommentMapper;
    }

    @Override
    public void saveComment(CommentDto commentDto) {
        commentRepository.save(manualCommentMapper.commentDtoToComment(commentDto));
    }

    @Override
    public List<CommentDto> getCommentByEvent(Long eventId) {
        List<Comment> commentList = commentRepository.findByEvent_IdOrderByDateOfCreation(eventId);
        return commentList.stream()
                .map(manualCommentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }
}
