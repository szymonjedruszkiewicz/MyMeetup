package pl.sda.meetup.meetup.mapper.manual;

import org.springframework.stereotype.Component;
import pl.sda.meetup.meetup.dto.EventDto;
import pl.sda.meetup.meetup.model.Event;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.repository.UserRepository;

@Component
public class ManualEventMapper {

    private final ManualUserMapper manualUserMapper;

    public ManualEventMapper(ManualUserMapper manualUserMapper) {
        this.manualUserMapper = manualUserMapper;
    }


    public EventDto eventToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .userDto(manualUserMapper.userToUserDto(event.getUser()))
                .title(event.getTitle())
                .description(event.getDescription())
                .start(event.getStart())
                .end(event.getEnd())
                .id(event.getId())
                .build();
    }


    public Event eventDtoToEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .start(eventDto.getStart())
                .end(eventDto.getEnd())
                .build();
    }
}
