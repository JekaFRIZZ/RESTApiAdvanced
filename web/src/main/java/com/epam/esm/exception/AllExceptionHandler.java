package com.epam.esm.exception;

import com.epam.esm.entity.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(ResourceExistenceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorData handleResourceNotFoundException(CustomException e){
        return new ErrorData(e.getMessage(), e.getErrorCode());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorData handleResourceNotUniqueException(CustomException e){
        return new ErrorData(e.getMessage(), e.getErrorCode());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public @ResponseBody ErrorData handleValidationException(CustomException e){
        return new ErrorData(e.getMessage(), e.getErrorCode());
    }

    @ExceptionHandler(FieldExistenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorData handleFieldExistException(CustomException e){
        return new ErrorData(e.getMessage(), e.getErrorCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorData handleIllegalArgumentException(IllegalArgumentException e){
        return new ErrorData(e.getMessage(), 7);
    }
}

