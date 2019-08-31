package pl.sda.meetup.meetup.mapper.manual;

import pl.sda.meetup.meetup.dto.EventDto;
import pl.sda.meetup.meetup.model.Event;

public class ManualEventMapper {

    public static EventDto eventToEventDto(Event event) {
        return EventDto.builder()
                .title(event.getTitle())
                .description(event.getDescription())
//                .userDto(event.getUser())
                .start(event.getStart())
                .end(event.getEnd())
                .build();
    }


    public static Event eventDtoToEvent(EventDto eventDto) {
        return Event.builder()
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
//                .user(eventDto.getUser())
                .start(eventDto.getStart())
                .end(eventDto.getEnd())
                .build();
    }
}
