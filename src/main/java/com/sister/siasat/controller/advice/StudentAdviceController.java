package com.sister.siasat.controller.advice;

import com.sister.siasat.exception.StudentNotFoundException;
import com.sister.siasat.model.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StudentAdviceController {

    @ResponseBody
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Exception StudentNotFound(StudentNotFoundException exception) {
        return new Exception(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

}
