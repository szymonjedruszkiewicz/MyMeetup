package pl.sda.meetup.meetup.service;

import pl.sda.meetup.meetup.dto.EventDto;

import java.util.List;

public interface EventService {

    void saveEvent(EventDto eventDto);

    List<EventDto> getEventList();

    List<EventDto> getSearchResults(String query, String type);

    EventDto findEventById(Long id);



}
