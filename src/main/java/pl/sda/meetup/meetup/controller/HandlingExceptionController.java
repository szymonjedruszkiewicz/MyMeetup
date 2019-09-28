package pl.sda.meetup.meetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.meetup.meetup.exception.NoEventException;
import pl.sda.meetup.meetup.exception.NoUserException;

@Slf4j
@ControllerAdvice
public class HandlingExceptionController {
    private static final String NOT_FOUND_VIEW = "errors/error404";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){
        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoEventException.class)
    public ModelAndView handleEventNotFound(Exception exception){
        log.error("Handling No Event Exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(NOT_FOUND_VIEW);
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoUserException.class)
    public ModelAndView handleUserNotFound(Exception exception){
        log.error("Handling No User Exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(NOT_FOUND_VIEW);
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }


}
