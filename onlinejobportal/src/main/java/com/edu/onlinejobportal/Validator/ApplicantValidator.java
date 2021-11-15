package com.edu.onlinejobportal.Validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.edu.onlinejobportal.Pojo.Applicant;

public class ApplicantValidator implements Validator
{
	
	@Override
	public boolean supports(Class<?> clazz) {
		//This Validator validates *just* User instances
		return Applicant.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//basic validations
		
		Pattern ptr = Pattern.compile("[^_\\.][\\w\\._]+[\\w]@[\\w]+\\.[a-zA-Z\\.]+[a-zA-Z]");
		
		Applicant applicant = (Applicant)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty.email", "Email is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "empty.firstName", "First Name is Required");
		
		Double gpa = applicant.getGpa();
		
		int pass_len = applicant.getPassword().length();
		
		String email = applicant.getEmail();
		
		if(!ptr.matcher(email).matches()) {
			errors.rejectValue("email","empty.gpa","Invalid Email");
		}
		
		if(pass_len<5) {
			errors.rejectValue("password","empty.password","Password should be minimum of 5 characters");
		}
		
		if(gpa>4.0 || gpa<0.0) {
			errors.rejectValue("gpa","empty.gpa","GPA has to be between 0-4");
		}
		
		
		//add additional custom validations below
	}
}
