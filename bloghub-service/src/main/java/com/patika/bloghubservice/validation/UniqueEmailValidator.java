package com.patika.bloghubservice.validation;

import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Optional<User> user=userRepository.findByEmail(value);
        if(user.isPresent())
        {
            return false;
        }
        return true;
    }
}
