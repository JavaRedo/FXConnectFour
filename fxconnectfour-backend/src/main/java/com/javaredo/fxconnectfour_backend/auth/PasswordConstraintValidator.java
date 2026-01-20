package com.javaredo.fxconnectfour_backend.auth;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.WhitespaceRule;
import org.springframework.context.annotation.Bean;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Bean
    private PasswordValidator getValidator(){
        
        PasswordValidator pv = new PasswordValidator(
                new LengthRule(8, 16),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule());

        return pv;
    }
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        var res = getValidator().validate(new PasswordData(password));
        String message = String.join(", ", getValidator().getMessages(res));
        
        
        context.disableDefaultConstraintViolation();

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        
        return res.isValid();
    }

}
