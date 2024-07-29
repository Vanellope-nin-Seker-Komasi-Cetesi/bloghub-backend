package com.patika.bloghubservice.validation;

import com.patika.bloghubservice.model.Blog;
import com.patika.bloghubservice.repository.BlogRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class BlogLikeLimitValidator implements ConstraintValidator<LikeLimit,String> {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        Optional<Blog> blog=blogRepository.findByTitle(value);
        if(blog.isPresent())
        {
            return false;
        }
        return true;
    }


}