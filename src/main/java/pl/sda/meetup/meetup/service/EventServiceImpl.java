package pl.sda.meetup.meetup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.meetup.meetup.dto.EventDto;
import pl.sda.meetup.meetup.exception.NoEventException;
import pl.sda.meetup.meetup.mapper.manual.ManualEventMapper;
import pl.sda.meetup.meetup.model.Event;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.repository.EventRepository;
import pl.sda.meetup.meetup.repository.UserRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final UserContextService userContextService;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ManualEventMapper manualEventMapper;

    public EventServiceImpl(UserContextService userContextService, UserRepository userRepository, EventRepository eventRepository, ManualEventMapper manualEventMapper) {
        this.userContextService = userContextService;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.manualEventMapper = manualEventMapper;
    }


    @Override
    public void saveEvent(EventDto eventDto) {
        User user = userRepository.findUserByEmail(userContextService.getLoggedUserName()).orElseThrow(() -> new RuntimeException("no user"));
        Event event = manualEventMapper.eventDtoToEvent(eventDto);
        event.setUser(user);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> getEventList() {
        List<Event> eventList = eventRepository.findAll();
        return eventList.stream()
                .filter(event -> event.getEnd().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Event::getStart))
                .map(manualEventMapper::eventToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getSearchResults(String query, String type) {

        List<Event> queryResult = eventRepository.findEventsByTitleContains(query);

        switch (type) {
            case "future":
                return queryResult.stream()
                        .filter(event -> event.getStart().isAfter(LocalDate.now()))
                        .map(manualEventMapper::eventToEventDto)
                        .collect(Collectors.toList());
            case "current_and_future":
                return queryResult.stream()
                        .filter(event -> event.getStart().isBefore(LocalDate.now()) && !event.getEnd().isBefore(LocalDate.now()))
                        .map(manualEventMapper::eventToEventDto)
                        .collect(Collectors.toList());
            case "all":
                return queryResult.stream()
                        .map(manualEventMapper::eventToEventDto)
                        .collect(Collectors.toList());
            default:
                throw new NoEventException("no such Events found in db");
        }
    }

    @Override
    public EventDto findEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new NoEventException("No event of id: " + id + " found in db"));
        return manualEventMapper.eventToEventDto(event);
    }

    @Override
    public List<EventDto> findEventsByDates(LocalDate start, LocalDate end) {
        return eventRepository.findEventsByStartIsAfterAndEndIsBefore(start, end).stream()
                .map(manualEventMapper::eventToEventDto)
                .collect(Collectors.toList());
    }
}
