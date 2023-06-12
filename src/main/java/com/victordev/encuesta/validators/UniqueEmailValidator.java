package com.victordev.encuesta.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.victordev.encuesta.annotations.UniqueEmail;
import com.victordev.encuesta.entities.UserEntity;
import com.victordev.encuesta.repositories.UserRepository;


public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        UserEntity user = userRepository.findByEmail(value);
        if (user == null) {
            return true;
        }
        return false;
    }
    
}
