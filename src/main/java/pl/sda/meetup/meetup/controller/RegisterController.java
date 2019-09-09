package pl.sda.meetup.meetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.exception.UserExistsException;
import pl.sda.meetup.meetup.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegisterController {

    private static final String REGISTER_FORM = "registerForm";
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return REGISTER_FORM;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return REGISTER_FORM;
        }
        try {
            userService.save(userDto);
        } catch (UserExistsException e) {
            bindingResult.rejectValue("email", "700", e.getMessage());
            return REGISTER_FORM;
        }
        return "redirect:/index";
    }
}
