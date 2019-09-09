package pl.sda.meetup.meetup.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.meetup.meetup.dto.EventDto;
import pl.sda.meetup.meetup.service.EventService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventRestController {

    private final EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/list")
    public List<EventDto> listEventsWithParams
            (@RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate start,
             @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate end) {

        if (start == null || end == null) {
            return eventService.getEventList();
        } else {
            return eventService.findEventsByDates(start, end);
        }
    }

}
