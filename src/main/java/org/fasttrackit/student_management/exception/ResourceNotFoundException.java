package org.fasttrackit.student_management.exception;

import org.fasttrackit.student_management.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Logger;

import static org.fasttrackit.student_management.service.StudentService.LOGGER;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
