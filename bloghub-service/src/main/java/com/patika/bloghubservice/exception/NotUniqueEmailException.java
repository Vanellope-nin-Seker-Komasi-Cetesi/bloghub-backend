package com.patika.bloghubservice.exception;

import com.patika.bloghubservice.messages.CustomMessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends RuntimeException{
    public NotUniqueEmailException() {
        super(CustomMessageSource.getMessageForLocale("error.validation", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors(){
        return Collections.singletonMap("email", CustomMessageSource.getMessageForLocale("email.constraint.validation.notUnique", LocaleContextHolder.getLocale()));
    }
}
