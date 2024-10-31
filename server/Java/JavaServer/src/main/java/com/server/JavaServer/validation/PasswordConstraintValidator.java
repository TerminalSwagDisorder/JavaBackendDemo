package com.server.JavaServer.validation;

import jakarta.validation.*;
import java.util.regex.*;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
 private Pattern pattern;
 private Matcher matcher;
 private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{9,}$";

 @Override
 public void initialize(ValidPassword constraintAnnotation) { }

 @Override
 public boolean isValid(String password, ConstraintValidatorContext context){
     pattern = Pattern.compile(PASSWORD_PATTERN);
     if(password == null){
         return false;
     }
     matcher = pattern.matcher(password);
     return matcher.matches();
 }
}
