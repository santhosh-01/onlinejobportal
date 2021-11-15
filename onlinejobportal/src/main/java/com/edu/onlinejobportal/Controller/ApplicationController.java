package com.edu.onlinejobportal.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.onlinejobportal.Dao.ApplicantDAO;
import com.edu.onlinejobportal.Dao.ApplicationDAO;
import com.edu.onlinejobportal.Dao.JobDescriptionDAO;
import com.edu.onlinejobportal.Dao.JobsDAO;
import com.edu.onlinejobportal.Pojo.Applicant;
import com.edu.onlinejobportal.Pojo.Application;
import com.edu.onlinejobportal.Pojo.JobDescription;
import com.edu.onlinejobportal.Pojo.Jobs;

@Controller
public class ApplicationController {
	
	@Autowired
	JobsDAO jobsDAO;
	
	@Autowired
	ApplicationDAO applicationDAO;
	
	@Autowired
	ApplicantDAO applicantDAO;
	
	@Autowired
	Application application;

	@Autowired
	JobDescriptionDAO jobDescriptionDAO;
	
	@RequestMapping(value = "/applyJobs.php", method = {RequestMethod.POST, RequestMethod.GET})
	public String actionRedirect(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		System.out.println("inside apply jobs controller");
		
		String[] jobId = request.getParameterValues("jobs");
		
		Applicant applicant = (Applicant)session.getAttribute("loggedApplicant");
		
		if(applicant==null) {
			response.sendRedirect("/action.php?type=applicant");
		}
		try {
		for(String s: jobId) {
			Jobs job = jobsDAO.get(Long.valueOf(s));
			Set<Jobs> localJob = new HashSet<Jobs>();
			
			localJob.add(job);
			
			Date date = new Date();
			
			application.setApplydate(date);
			
			application.setJobs(job);
			
			Set<Application> localApplication = new HashSet<Application>();
			localApplication.add(application);
			
			applicant.setApplication(localApplication);
			application.setApplicant(applicant);
			
			applicationDAO.create(application);			
		}}catch (Exception e) {
			
			request.setAttribute("Error", "Please select atleast one to proceed!");
			
			List<Application> applicationList = applicationDAO.list(applicant.getId());
			
			List<JobDescription> jobDescList = jobDescriptionDAO.totalList();
			
			for(Application app : applicationList) {
				Iterator<JobDescription> itr = jobDescList.iterator();
				while(itr.hasNext()) {
					JobDescription job = itr.next();
					if(job.getId()==app.getJobs().getId()) {
						itr.remove();
					}
				}
			}
			request.setAttribute("jobs",jobDescList );
			return ("AvailableJobs");
		}
		
		return("ApplicantHome");
		
	}

	@RequestMapping(value = "/cancelJobs.php", method = {RequestMethod.POST, RequestMethod.GET})
	public String cancelJob(HttpSession session,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		
		System.out.println("inside cancel jobs controller");
		
		String[] jobId = request.getParameterValues("jobs");
		
		Applicant applicant = (Applicant)session.getAttribute("loggedApplicant");
		
		if(applicant==null) {
			response.sendRedirect("/action.php?type=applicant");
		}
		try {
		for(String s: jobId) {
			
			Application application = applicationDAO.get(Long.valueOf(s));
			applicationDAO.delete(application);				
		}} catch(Exception e) {
			request.setAttribute("Error", "Please select at least on application to delete!");
			long id = applicant.getId();
			
			request.setAttribute("jobs",applicationDAO.list(id));
			
			return("AppliedJobs");
		}
		
		return("ApplicantHome");
		
	}
}
