package com.trainservice.exceptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDate;


@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public static final Logger log= LogManager.getLogger(CustomizedResponseEntityExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDate.now(),ex.getMessage(),request.getDescription(false),"Internal Server Error");
        log.info(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(TrainDetailsNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(TrainDetailsNotFoundException ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDate.now(),ex.getMessage(),request.getDescription(false),"Not Found");
        log.info("Not Found Exception");
        return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(TrainAlreadyPresent.class)
    public final ResponseEntity<ExceptionResponse> handleConflictException(TrainAlreadyPresent ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDate.now(),ex.getMessage(),request.getDescription(false),"Conflict");
        log.info("Conflict Exception");
        return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {


        StringBuilder sb=new StringBuilder();
        for(FieldError fe:ex.getBindingResult().getFieldErrors()) {
            sb.append(fe.getField()).append(":").append(fe.getDefaultMessage()).append(".");
        }
        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDate.now(),"Validation Fails",sb.toString(),"Bad Request");
        log.info("Validation Fail");
        return new ResponseEntity<Object> (exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}

