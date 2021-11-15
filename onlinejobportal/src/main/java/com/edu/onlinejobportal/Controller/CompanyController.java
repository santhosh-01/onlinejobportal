package com.edu.onlinejobportal.Controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.onlinejobportal.Dao.ApplicationDAO;
import com.edu.onlinejobportal.Dao.JobsDAO;
import com.edu.onlinejobportal.Pojo.Application;
import com.edu.onlinejobportal.Pojo.Company;
import com.edu.onlinejobportal.Pojo.Jobs;

@Controller
public class CompanyController {
	
	@Autowired
	JobsDAO jobsDAO;
	
	@Autowired
	ApplicationDAO applicationDAO;
	
	
	@RequestMapping(value = "/companyAction.php", method = {RequestMethod.POST, RequestMethod.GET})
	public String actionRedirect(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		Company company = (Company) session.getAttribute("loggedCompany");
		if(company==null) {
			response.sendRedirect("/onlinejobportal/action.php?type=company");
			return null;
		}
		
		String action = request.getParameter("action");
		if(action.equals("view")) {
			String s = view(session, request);
			return s;
			
		} else if(action.equals("add")) {
			
			return("CompanyAddJobs");
			
		} else if(action.equals("ViewApplicants")) {
			//Company company = (Company) session.getAttribute("loggedCompany");
			
			long c_id = company.getId();
			
			List<Jobs> list_jobs = jobsDAO.list(c_id);
			
			for(Jobs job : list_jobs) {
				System.out.println("Applications for Job: "+job.getId()+", Job name: "+job.getJobName()+", company: "+company.getCompanyName());
				List<Application> applications = applicationDAO.list_jobId(job.getId());
				for(Application application : applications) {
					System.out.println("Application id: "+application.getId());
					System.out.println("Applicant id: "+application.getApplicant().getId());
					System.out.println("Applicant Name: "+application.getApplicant().getFirstName());
				}
			}
			request.setAttribute("jobs", list_jobs);	
			return("CompanyApplicants");
			
		} else if(action.equals("logout")) {
			session.removeAttribute("loggedCompany");
			session.invalidate();
			try {
				response.sendRedirect("/onlinejobportal/action.php?type=company");
			}catch (Exception e) {

			}
			
			return(null);
		}
		return null;
		
	}
	
	public String view(HttpSession session,HttpServletRequest request) {
		Company company = (Company) session.getAttribute("loggedCompany");
		request.setAttribute("jobList", jobsDAO.list(company.getId()));
		return("CompanyViewJobs");
	}

}
