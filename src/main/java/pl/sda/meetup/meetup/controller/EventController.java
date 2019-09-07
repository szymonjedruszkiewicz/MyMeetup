package pl.sda.meetup.meetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.meetup.meetup.dto.CommentDto;
import pl.sda.meetup.meetup.dto.EventDto;
import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.exception.NoEventException;
import pl.sda.meetup.meetup.exception.NoUserException;
import pl.sda.meetup.meetup.mapper.manual.ManualUserMapper;
import pl.sda.meetup.meetup.model.Event;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.repository.EventRepository;
import pl.sda.meetup.meetup.service.CommentService;
import pl.sda.meetup.meetup.service.EventService;
import pl.sda.meetup.meetup.service.UserContextService;
import pl.sda.meetup.meetup.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
public class EventController {

    private final UserContextService userContextService;
    private final UserService userService;
    private final EventService eventService;
    private final CommentService commentService;
    private final ManualUserMapper manualUserMapper;
    private final EventRepository eventRepository;

    public EventController(UserContextService userContextService, UserService userService, EventService eventService, CommentService commentService, ManualUserMapper manualUserMapper, EventRepository eventRepository) {
        this.userContextService = userContextService;
        this.userService = userService;
        this.eventService = eventService;
        this.commentService = commentService;
        this.manualUserMapper = manualUserMapper;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/event/add")
    public String showEventForm(Model model) {
        model.addAttribute("eventDto", new EventDto());
        return "eventForm";
    }

    @PostMapping("/event/add")
    public String addEvent(@ModelAttribute @Valid EventDto eventDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "eventForm";
        }
        eventService.saveEvent(eventDto);
        return "redirect:/index";
    }

    @GetMapping("/search")
    public String showSearchResults(@RequestParam(value = "q", required = false) String query, @RequestParam(value = "type", required = false)
            String type, Model model) {

        List<EventDto> searchResults = eventService.getSearchResults(query, type);
        model.addAttribute("results", searchResults);
        model.addAttribute("q", query);
        model.addAttribute("type", type);
        return "searchResults";
    }

    @GetMapping("/event/{id}")
    public String showDetailedEvent(@PathVariable String id, Model model) {
        Set<UserDto> registeredUsersSet = userService.listUsersRegisteredToEvent(Long.valueOf(id));
        model.addAttribute("isRegistered", userService.checkIfIsRegistered(Long.valueOf(id)));
        model.addAttribute("registeredUsersSet", registeredUsersSet);
        model.addAttribute("event", eventService.findEventById(Long.valueOf(id)));
        model.addAttribute("id", id);
        model.addAttribute("commentList", commentService.getCommentByEvent(Long.valueOf(id)));

        return "eventDetails";
    }

    @PostMapping("/event/{id}")
    public String postComment(@PathVariable String id, @RequestParam(name = "comment") String comment) {
        commentService.saveCommentSting(comment, Long.valueOf(id));
        return "redirect:/event/" + id;
    }

    @PostMapping(path = "/sign/{id}")
    public String signToEvent(@PathVariable String id, @RequestParam String sign){
        userService.manageEventRegistration(sign, Long.valueOf(id));
        return "redirect:/event/" + id;
    }

}
