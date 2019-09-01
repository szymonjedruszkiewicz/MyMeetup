package pl.sda.meetup.meetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.meetup.meetup.dto.EventDto;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.service.UserContextService;
import pl.sda.meetup.meetup.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EventController {

    private final UserContextService userContextService;
    private final UserService userService;

    public EventController(UserContextService userContextService, UserService userService) {
        this.userContextService = userContextService;
        this.userService = userService;
    }

    @GetMapping("/events")
    public String showEvents() {
        return "";
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

        return "redirect:/index";
    }


}
