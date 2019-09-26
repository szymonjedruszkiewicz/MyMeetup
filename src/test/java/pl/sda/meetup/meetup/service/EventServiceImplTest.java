package pl.sda.meetup.meetup.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.meetup.meetup.dto.EventDto;
import pl.sda.meetup.meetup.exception.NoEventException;
import pl.sda.meetup.meetup.mapper.manual.ManualEventMapper;
import pl.sda.meetup.meetup.mapper.manual.ManualUserMapper;
import pl.sda.meetup.meetup.model.Event;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.repository.EventRepository;
import pl.sda.meetup.meetup.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceImplTest {

    EventService eventService;

    ManualEventMapper manualEventMapper;

    User testUser = User.builder()
            .id(1L)
            .email("test@test.pl")
            .build();

    Event firstEvent = Event.builder()
            .id(1L)
            .description("string")
            .title("test")
            .user(testUser)
            .start(LocalDate.of(2019, 9, 27))
            .end(LocalDate.of(2019, 10, 1))
            .build();

    Event secondEvent = Event.builder()
            .id(2L)
            .description("string")
            .title("test2")
            .start(LocalDate.of(2019, 9, 20))
            .end(LocalDate.of(2019, 10, 1))
            .build();

    @Mock
    EventRepository eventRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    UserContextService userContextService;

    @Mock
    ManualUserMapper manualUserMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        manualEventMapper = new ManualEventMapper(manualUserMapper);
        eventService = new EventServiceImpl(userContextService, userRepository, eventRepository, manualEventMapper);
    }

    @Test
    @DisplayName("should throw exception when no event was found")
    public void noEventFound() {

        Optional<Event> eventOptional = Optional.empty();

        when(eventRepository.findById(anyLong())).thenReturn(eventOptional);

        Assertions.assertThrows(NoEventException.class, () -> eventService.findEventById(1L));
    }

//    @Test
//    //TODO
//    public void getEventByIdTest() {
//
//        Optional<Event> eventDtoOptional = Optional.of(firstEvent);
//
//        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(firstEvent));
//        EventDto eventReturned = eventService.findEventById(1L);
//
//        assertNotNull(eventReturned);
//    }

    @Test
    public void getAllEventsTest() {
        List<Event> testEventList = new ArrayList<>();
        testEventList.add(firstEvent);

        when(eventRepository.findAll()).thenReturn(testEventList);

        List<EventDto> eventList = eventService.getEventList();

        assertEquals(1, eventList.size());
        verify(eventRepository, times(1)).findAll();
        verify(eventRepository, never()).findById(anyLong());
    }

    @Test
    @DisplayName("should return filtered list of events")
    public void getEventsWithDates() {

        List<Event> testEventList = new ArrayList<>();
        testEventList.add(firstEvent);
        testEventList.add(secondEvent);

        LocalDate start = LocalDate.of(2019, 9, 1);
        LocalDate end = LocalDate.of(2019, 10, 1);
        when(eventRepository.findEventsByStartIsAfterAndEndIsBefore(start, end))
                .thenReturn(testEventList);

        List<EventDto> eventsByDates = eventService.findEventsByDates(start, end);
        assertEquals(2, eventsByDates.size());
    }

    @Test
    public void shouldCorrectlySaveEvent() {

        when(userContextService.getLoggedUserName()).thenReturn(testUser.getEmail());
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(testUser));

        eventService.saveEvent(manualEventMapper.eventToEventDto(firstEvent));

        verify(eventRepository, times(1)).save(any(Event.class));

    }


}