package com.patika.bloghubservice.exception;

import com.patika.bloghubservice.messages.CustomMessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class BlogNotFoundException extends RuntimeException {

    public BlogNotFoundException(String title){
        super(CustomMessageSource.getMessageForLocale("blog.not.found", LocaleContextHolder.getLocale(),title));
    }
}