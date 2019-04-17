package com.sister.siasat.controller.advice;

import com.sister.siasat.exception.DepartmentNotFoundException;
import com.sister.siasat.model.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DepartmentAdviceController {

    @ResponseBody
    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Exception DepartmentNotFound(DepartmentNotFoundException exception) {
        return new Exception(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

}
