package pl.sda.meetup.meetup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoEventException extends RuntimeException {

    public NoEventException(String message) {
        super(message);
    }
}
