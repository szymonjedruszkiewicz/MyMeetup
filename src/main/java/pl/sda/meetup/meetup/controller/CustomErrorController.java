package pl.sda.meetup.meetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                log.info("User " + auth.getName() + " attempted to access resource not found on server");
                return "errors/error404";
            }

            if (statusCode == HttpStatus.FORBIDDEN.value()){
                log.info("User " + auth.getName() + " attempted to access restricted page");
                return "errors/error403";
            }
        }
        log.info("Handling general error");
        return "errors/error";
    }

    @Override
    public String getErrorPath() {
        return "errors/error";
    }
}
