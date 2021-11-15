package com.edu.onlinejobportal.Validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.edu.onlinejobportal.Pojo.Company;

public class CompanyValidator implements Validator
{
	
	@Override
	public boolean supports(Class<?> clazz) {
		//This Validator validates *just* User instances
		return Company.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//basic validations
		Pattern ptr = Pattern.compile("[^_\\.][\\w\\._]+[\\w]@[\\w]+\\.[a-zA-Z\\.]+[a-zA-Z]");
		
		Company company = (Company)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty.email", "Email is Required");
		
		
		int pass_len = company.getPassword().length();
		
		String email = company.getEmail();
		
		if(!ptr.matcher(email).matches()) {
			errors.rejectValue("email","empty.gpa","Invalid Email");
		}
		
		if(pass_len<5) {
			errors.rejectValue("password","empty.password","Password should be minimum of 5 characters");
		}
		
		
		
		//add additional custom validations below
	}
}
