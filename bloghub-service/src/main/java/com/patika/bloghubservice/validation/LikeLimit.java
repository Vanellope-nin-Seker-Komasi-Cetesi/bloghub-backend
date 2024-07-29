package com.patika.bloghubservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;


@Constraint(validatedBy = BlogLikeLimitValidator.class)
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LikeLimit {

    String message() default "{blog.like.limit}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}