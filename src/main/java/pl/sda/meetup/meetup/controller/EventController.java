package pl.sda.meetup.meetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.meetup.meetup.dto.CommentDto;
import pl.sda.meetup.meetup.dto.EventDto;
import pl.sda.meetup.meetup.mapper.manual.ManualUserMapper;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.service.CommentService;
import pl.sda.meetup.meetup.service.EventService;
import pl.sda.meetup.meetup.service.UserContextService;
import pl.sda.meetup.meetup.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    private final UserContextService userContextService;
    private final UserService userService;
    private final EventService eventService;
    private final CommentService commentService;
    private final ManualUserMapper manualUserMapper;

    public EventController(UserContextService userContextService, UserService userService, EventService eventService, CommentService commentService, ManualUserMapper manualUserMapper) {
        this.userContextService = userContextService;
        this.userService = userService;
        this.eventService = eventService;
        this.commentService = commentService;
        this.manualUserMapper = manualUserMapper;
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

        model.addAttribute("event", eventService.findEventById(Long.valueOf(id)));
        model.addAttribute("id", id);
        model.addAttribute("commentList", commentService.getCommentByEvent(Long.valueOf(id)));
        model.addAttribute("commentDto", new CommentDto());
        return "eventDetails";
    }

    @PostMapping("/event/{id}")
    public String postComment(@PathVariable String id, @ModelAttribute @Valid CommentDto commentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "eventDetails";
        }



        return "redirect:/index";


    }


}
