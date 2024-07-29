package com.patika.bloghubservice.exception;

import com.patika.bloghubservice.dto.response.GenericResponseConstants;
import com.patika.bloghubservice.messages.CustomMessageSource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request)
    {

        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        String message= CustomMessageSource.getMessageForLocale("error.validation", LocaleContextHolder.getLocale());

        apiError.setMessage(message);
        apiError.setStatus(GenericResponseConstants.FAILED);
        apiError.setStatusCode(400);
        Map<String, String> validationErrors=new HashMap<>();
        for(var fieldError:exception.getBindingResult().getFieldErrors())
        {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(NotUniqueEmailException.class)
    ResponseEntity<ApiError> handleNotUniqueEmailException(NotUniqueEmailException exception)
    {
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(GenericResponseConstants.FAILED);
        apiError.setStatusCode(400);
        apiError.setValidationErrors(exception.getValidationErrors());
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ApiError> handleEntityNotFoundException(UserNotFoundException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(GenericResponseConstants.FAILED);
        apiError.setStatusCode(404);
        return ResponseEntity.status(404).body(apiError);
    }

    @ExceptionHandler(BlogNotFoundException.class)
    ResponseEntity<ApiError> handleBlogNotFoundException(BlogNotFoundException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(GenericResponseConstants.FAILED);
        apiError.setStatusCode(404);
        return ResponseEntity.status(404).body(apiError);
    }


    @ExceptionHandler(BlogLikeLimitException.class)
    ResponseEntity<ApiError> handleBlogLikeLimitException(BlogLikeLimitException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(GenericResponseConstants.FAILED);
        apiError.setStatusCode(400);
        apiError.setValidationErrors(exception.getValidationErrors());
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(UserNotApprovedException.class)
    ResponseEntity<ApiError> handleUserNotApprovedException(UserNotApprovedException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(GenericResponseConstants.FAILED);
        apiError.setStatusCode(400);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(BlogStatusException.class)
    ResponseEntity<ApiError> handleBlogStatusException(BlogStatusException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(GenericResponseConstants.FAILED);
        apiError.setStatusCode(400);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(UnauthorizedModificationException.class)
    ResponseEntity<ApiError> handleUnauthorizedModificationException(UnauthorizedModificationException exception, HttpServletRequest request)
    {
        ApiError apiError=new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(GenericResponseConstants.FAILED);
        apiError.setStatusCode(403);
        return ResponseEntity.badRequest().body(apiError);
    }
}
