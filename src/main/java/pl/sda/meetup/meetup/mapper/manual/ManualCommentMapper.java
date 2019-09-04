package pl.sda.meetup.meetup.mapper.manual;

import org.springframework.stereotype.Component;
import pl.sda.meetup.meetup.dto.CommentDto;
import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.model.Comment;
import pl.sda.meetup.meetup.model.User;

@Component
public class ManualCommentMapper {

    private final ManualEventMapper manualEventMapper;

    public ManualCommentMapper(ManualEventMapper manualEventMapper) {
        this.manualEventMapper = manualEventMapper;
    }

    public CommentDto commentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .eventDto(manualEventMapper.eventToEventDto(comment.getEvent()))
                .description(comment.getDescription())
                .userDto(UserDto.builder()
                        .email(comment.getUser().getEmail())
                        .username(comment.getUser().getUsername())
                        .roles(comment.getUser().getRoles())
                        .build())
                .dateOfCreation(comment.getDateOfCreation())
                .build();
    }

    public Comment commentDtoToComment(CommentDto commentDto) {
        return Comment.builder()
                .event(manualEventMapper.eventDtoToEvent(commentDto.getEventDto()))
                .description(commentDto.getDescription())
                .dateOfCreation(commentDto.getDateOfCreation())
                .user(User.builder()
                        .email(commentDto.getUserDto().getEmail())
                        .username(commentDto.getUserDto().getUsername())
                        .build())
                .build();
    }
}
