package pl.sda.meetup.meetup.mapper.manual;

import org.springframework.stereotype.Component;
import pl.sda.meetup.meetup.dto.EventDto;
import pl.sda.meetup.meetup.model.Event;

@Component
public class ManualEventMapper {

    private final ManualUserMapper manualUserMapper;

    public ManualEventMapper(ManualUserMapper manualUserMapper) {
        this.manualUserMapper = manualUserMapper;
    }


    public EventDto eventToEventDto(Event event) {
        return EventDto.builder()
                .title(event.getTitle())
                .description(event.getDescription())
                .start(event.getStart())
                .end(event.getEnd())
                .id(event.getId())
                .build();
    }


    public Event eventDtoToEvent(EventDto eventDto) {
        return Event.builder()
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .start(eventDto.getStart())
                .end(eventDto.getEnd())
                .build();
    }
}
