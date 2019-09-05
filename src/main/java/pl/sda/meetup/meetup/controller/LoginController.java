package pl.sda.meetup.meetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.service.UserContextService;
import pl.sda.meetup.meetup.service.UserService;

@Controller
@Slf4j
public class LoginController {

     @GetMapping("/login")
    public String showLoginPage() {
        return "loginForm";
    }

}
