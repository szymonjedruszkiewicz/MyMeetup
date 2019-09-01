package pl.sda.meetup.meetup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.meetup.meetup.dto.EventDto;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final UserContextService userContextService;

    public EventServiceImpl(UserContextService userContextService) {
        this.userContextService = userContextService;
    }


    @Override
    public void save(EventDto eventDto, String loggedUserEmail) {
        

    }
}
