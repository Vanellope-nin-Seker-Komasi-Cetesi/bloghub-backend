package com.patika.bloghubservice.exception;

import com.patika.bloghubservice.messages.CustomMessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String email){
        super(CustomMessageSource.getMessageForLocale("user.not.found", LocaleContextHolder.getLocale(),email));
    }
}