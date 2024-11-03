package com.server.JavaServer.validation;

import jakarta.validation.*;
import java.util.regex.*;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
	private Pattern pattern;
	private Matcher matcher;
	private static final String passwordPattern = "^(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{9,}$";
	
	@Override
	public void initialize(ValidPassword constraintAnnotation) { }
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context){
	pattern = Pattern.compile(passwordPattern);
	if(password == null){
		return false;
	}
	matcher = pattern.matcher(password);
	return matcher.matches();
	}
}
