package com.edu.onlinejobportal.Controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.onlinejobportal.Pojo.Applicant;

@Controller
public class LoginPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginPageController.class);
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public String loginPage(Model model) {
			
		return "MainPage";
	}
	
	@RequestMapping(value = "/action.php" , method = RequestMethod.GET)
	public String mainPage(HttpServletRequest request,Model model) {
		String type = request.getParameter("type");
		
		if(type.equals("applicant"))
			return "ApplicantLoginPage";
		else
			return "CompanyLoginPage";
	}
	
	@RequestMapping(value = "/Applicantsignup.php" , method = RequestMethod.GET)
	public String applicantSignUpPage(Model model) {
		
		model.addAttribute(new Applicant());
		return "Applicantsignup";
	}
	
	@RequestMapping(value = "/Companysignup.php" , method = RequestMethod.GET)
	public String companySignUpPage(Model model) {
			
		return "Companysignup";
	}

}
