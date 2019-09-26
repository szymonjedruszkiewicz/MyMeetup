package pl.sda.meetup.meetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.meetup.meetup.service.EventService;

@Controller
public class IndexController {

    private final EventService eventService;

    public IndexController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping({"/index", "/",})
    public String showIndexPage(Model model) {
        model.addAttribute("events", eventService.getEventList());
        return "index";
    }

    @GetMapping("/admin")
    public String showAdminPage(){
        return "adminPage";
    }

    @GetMapping("/error403")
    public String showErrorPage(){
        return "error403";
    }

}
