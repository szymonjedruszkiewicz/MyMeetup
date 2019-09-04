package pl.sda.meetup.meetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.meetup.meetup.service.EventService;
import pl.sda.meetup.meetup.service.UserContextService;

@Controller
public class IndexController {

    private final UserContextService userContextService;
    private final EventService eventService;

    public IndexController(UserContextService userContextService, EventService eventService) {
        this.userContextService = userContextService;
        this.eventService = eventService;
    }

    @GetMapping({"/index", "/"})
    public String showIndexPage(Model model) {
        model.addAttribute("events", eventService.getEventList());
        return "index";
    }

}
