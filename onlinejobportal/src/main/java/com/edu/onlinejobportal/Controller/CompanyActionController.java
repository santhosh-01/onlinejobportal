package com.edu.onlinejobportal.Controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.edu.onlinejobportal.Dao.CompanyDAO;
import com.edu.onlinejobportal.Dao.JobDescriptionDAO;
import com.edu.onlinejobportal.Dao.JobsDAO;
import com.edu.onlinejobportal.Pojo.Applicant;
import com.edu.onlinejobportal.Pojo.Company;
import com.edu.onlinejobportal.Pojo.JobDescription;
import com.edu.onlinejobportal.Pojo.Jobs;

@Controller
public class CompanyActionController {
	
	@Autowired
	JobsDAO jobsDAO;
	
	@Autowired
	JobDescriptionDAO jobDescriptionDAO;
	
	@Autowired
	CompanyDAO companyDAO;

	@RequestMapping(value = "/addJobs.php", method = RequestMethod.POST)
	public String addjob(@ModelAttribute("addJobs") Jobs jobs, HttpSession session,HttpServletResponse response) throws IOException {
		
		try {
		Company company = (Company) session.getAttribute("loggedCompany");
		if(company==null) {
			response.sendRedirect("/");
		}

		Set<Jobs> localjobs = new HashSet<Jobs>();
		localjobs.add(jobs);
		
		company.setJobs(localjobs);
		
		jobs.setCompany(company);
		
		JobDescription jobDescription = new JobDescription(jobs.getCompany().getCompanyName(),jobs.getJobName(),jobs.getDescription(),jobs.getState(),jobs.getCity());
		
		company.setJobs(localjobs);
		jobsDAO.create(jobs);
		
		jobDescriptionDAO.create(jobDescription);
		}
		catch(Exception e) {
			
		}
		return("CompanyHome");
	}
	
	@RequestMapping(value = "/deleteJobs.php", method = RequestMethod.GET)
	public String deletejob(HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		try {
		Company company = (Company) session.getAttribute("loggedCompany");
		if(company==null) {
			response.sendRedirect("/");
		}
		
		String[] jobIds = request.getParameterValues("jobs");
		
		for(String s : jobIds) {
			Jobs job = jobsDAO.get(Long.valueOf(s));
			System.out.println("Delete Job with id: "+job.getId()+" Long value: "+Long.valueOf(s));
			jobsDAO.delete(job);
		}
		} catch(Exception e) {
			
		}
		Company company = (Company) session.getAttribute("loggedCompany");
		request.setAttribute("jobList", jobsDAO.list(company.getId()));
		return("CompanyViewJobs");
	}
	
}
