package com.patika.bloghubservice.exception;

import com.patika.bloghubservice.messages.CustomMessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class BlogStatusException extends RuntimeException{

    public BlogStatusException(){
        super(CustomMessageSource.getMessageForLocale("not.delete.blog.status.published", LocaleContextHolder.getLocale()));
    }

}