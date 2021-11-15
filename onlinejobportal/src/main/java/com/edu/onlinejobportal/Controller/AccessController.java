package com.edu.onlinejobportal.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.edu.onlinejobportal.Dao.ApplicantDAO;
import com.edu.onlinejobportal.Dao.CompanyDAO;
import com.edu.onlinejobportal.Pojo.Applicant;
import com.edu.onlinejobportal.Pojo.Company;
import com.edu.onlinejobportal.Validator.ApplicantValidator;
import com.edu.onlinejobportal.Validator.CompanyValidator;

@Controller
public class AccessController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginPageController.class);
	
	@Autowired
	ApplicantDAO applicantDAO;
	@Autowired
	CompanyDAO companyDAO;
	
	@Autowired
	ApplicantValidator applicantValidator;
	
	@Autowired
	CompanyValidator companyValidator;
	
	@InitBinder("applicant")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(applicantValidator);
	}
	
	@InitBinder("company")
	protected void initCompanyBinder(WebDataBinder binder) {
		binder.setValidator(companyValidator);
	}

	@RequestMapping(value = "/applicant_signup_page.php" , method = RequestMethod.POST)
	public String applicantSignup(@Validated @ModelAttribute("applicant") Applicant applicant,BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		
		//ApplicantDAO applicantDAO = new ApplicantDAO();
		if (bindingResult.hasErrors()) {
			return "Applicantsignup";  //the are validation errors, go to the form view
		}
		
		List<String> email_list = applicantDAO.emails();
		
		for(String s : email_list) {
			if(s.equalsIgnoreCase(applicant.getEmail())) {
				request.setAttribute("Error", "Existing email");
				return("Applicantsignup");
			}
		}
		String localpath = "C:\\Users\\Admin\\Desktop\\work\\OnlineJobPortal\\document\\";
		
		System.out.println("sign up called");
		
		String degree = request.getParameter("degree");
		
		String resumeNewName = generateFileName(applicant.getResume());
		
		applicant.getResume().transferTo(new File(localpath,resumeNewName));
		
		applicant.setResumefile(resumeNewName);
		
		applicant.setDegree(degree);
		request.setAttribute("msg","Account created successfully" );
		applicantDAO.create(applicant);
		
		return("ApplicantLoginPage");
	}
	
	@RequestMapping(value = "/applicant_update_page.php" , method = RequestMethod.POST)
	public String applicantUpdate(@ModelAttribute("updateApplicant") Applicant applicant,HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException {
		
		//ApplicantDAO applicantDAO = new ApplicantDAO();
		
		String localpath = "C:\\Users\\Guru\\OneDrive\\Desktop\\work\\OnlineJobPortal\\document\\";
		
		Applicant origApplicant = (Applicant)session.getAttribute("loggedApplicant");
		
		System.out.println("appppp:  "+origApplicant);
	
		String resumeNewName = generateFileName(applicant.getResume());
		System.out.println("Resume:  "+resumeNewName);
		System.out.println("ID"+origApplicant.getId());
		System.out.println("AID"+applicant.getId());
		applicant.getResume().transferTo(new File(localpath,resumeNewName));

		origApplicant.setResumefile(resumeNewName);

		applicantDAO.update(origApplicant);
		System.out.println("inside update page");
		return("ApplicantHome");
	}
	
	@RequestMapping(value = "/company_signup_page.php" , method = RequestMethod.POST)
	public String companySignup(@Validated @ModelAttribute("company") Company company, BindingResult bindingResult,HttpServletRequest request) {
		
		//CompanyDAO companyDAO = new CompanyDAO();
		if (bindingResult.hasErrors()) {
			return "Companysignup";  //the are validation errors, go to the form view
		}
		
		List<String> email_list = companyDAO.emails();
		
		for(String s : email_list) {
			if(s.equalsIgnoreCase(company.getEmail())) {
				request.setAttribute("Error", "Existing email");
				return("Companysignup");
			}
		}
		
		System.out.println("company sign up called");	
		
		companyDAO.create(company);
		request.setAttribute("msg","Account created successfully" );
		return("CompanyLoginPage");
	}
	
	private String generateFileName(MultipartFile multipart) {
		return new Date().getTime() + "-"+multipart.getOriginalFilename().replace(" ", "-");
	}
	
}
