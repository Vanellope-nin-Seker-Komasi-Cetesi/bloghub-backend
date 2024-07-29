package com.patika.bloghubservice.exception;

import com.patika.bloghubservice.messages.CustomMessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class UnauthorizedModificationException extends RuntimeException{

    public UnauthorizedModificationException(){
        super(CustomMessageSource.getMessageForLocale("unauthorized.modification", LocaleContextHolder.getLocale()));
    }

}