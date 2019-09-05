package pl.sda.meetup.meetup.exception;

public class NoUserException extends RuntimeException {

    public NoUserException(String message) {
        super(message);
    }
}
