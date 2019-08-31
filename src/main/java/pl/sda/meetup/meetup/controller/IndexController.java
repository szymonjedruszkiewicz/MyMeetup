package pl.sda.meetup.meetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.meetup.meetup.service.UserContextService;

@Controller
public class IndexController {

    private final UserContextService userContextService;

    public IndexController(UserContextService userContextService) {
        this.userContextService = userContextService;
    }

    @GetMapping({"/index", "/"})
    public String showIndexPage() {
        return "index";
    }

}
