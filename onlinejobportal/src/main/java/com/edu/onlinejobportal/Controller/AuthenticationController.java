package com.edu.onlinejobportal.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.onlinejobportal.Dao.ApplicantDAO;
import com.edu.onlinejobportal.Dao.CompanyDAO;
import com.edu.onlinejobportal.Pojo.Applicant;
import com.edu.onlinejobportal.Pojo.Company;

@Controller
public class AuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(LoginPageController.class);
	
	@Autowired
	ApplicantDAO applicantDAO;
	
	@Autowired
	CompanyDAO companyDAO;
	
	@RequestMapping(value = "/applicant_home_page.php",method = {RequestMethod.POST, RequestMethod.GET})
	public String applicantLogin(@ModelAttribute("applicant") Applicant applicant,HttpSession session, HttpServletResponse response,HttpServletRequest request ) {
		System.out.println(applicant.getEmail());
		System.out.println(applicant.getPassword());
		Applicant localApplicant = applicantDAO.authenticate(applicant);
		if(localApplicant == null) {
			request.setAttribute("Error","Invalid email or password");
			return "ApplicantLoginPage";
		}
		else {
			session.setAttribute("loggedApplicant",localApplicant);
			return("ApplicantHome");
		}		
	}
	
	@RequestMapping(value = "/company_home_page.php",method = {RequestMethod.POST, RequestMethod.GET})
	public String companyLogin(@ModelAttribute("company") Company company,HttpSession session, HttpServletResponse response, HttpServletRequest request ) {
		
		
		System.out.println(company.getEmail());
		System.out.println(company.getPassword());
		//CompanyDAO companyDAO = new CompanyDAO();
		Company localCompany = companyDAO.authenticate(company);
		if(localCompany == null) {
			request.setAttribute("Error","Invalid email or password");
			return "CompanyLoginPage";
		}
		else {
			session.setAttribute("loggedCompany",localCompany);
			return("CompanyHome");
		}		
	}
	
}
