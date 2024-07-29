package com.patika.bloghubservice.exception;

import com.patika.bloghubservice.messages.CustomMessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class UserNotApprovedException extends RuntimeException{

    public UserNotApprovedException(){
        super(CustomMessageSource.getMessageForLocale("user.not.approved", LocaleContextHolder.getLocale()));
    }

}