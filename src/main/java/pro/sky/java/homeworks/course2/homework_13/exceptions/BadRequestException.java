package pro.sky.java.homeworks.course2.homework_13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Your request contains unacceptable simbols";

    public BadRequestException() { super(DEFAULT_MESSAGE);}
}
