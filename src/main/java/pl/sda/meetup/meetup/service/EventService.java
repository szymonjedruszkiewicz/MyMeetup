package pl.sda.meetup.meetup.service;

import pl.sda.meetup.meetup.dto.EventDto;

public interface EventService {

    void save(EventDto eventDto, String loggedUserEmail);
}
