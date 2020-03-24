package com.easyms.training.sampleapp.validation;

import com.easyms.training.sampleapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Optional;

public class EmailExistingValidator implements ConstraintValidator<EmailExisting,String> {
    @Autowired
    ClientService clientService;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
         return !Objects.isNull(email) || Optional.ofNullable(clientService.getClientByEmail(email)).isPresent() ;
    }
}
