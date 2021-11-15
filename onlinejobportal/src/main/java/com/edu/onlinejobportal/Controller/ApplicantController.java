package com.edu.onlinejobportal.Controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
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

@Controller
public class ApplicantController {
	
//	@Autowired
//	ApplicantDAO applicantDAO;
	
	@Autowired
	ApplicationDAO applicationDAO;
	
	@Autowired
	JobDescriptionDAO jobDescriptionDAO;
	
	@RequestMapping(value = "/applicantAction.php", method = {RequestMethod.POST, RequestMethod.GET})
	public String actionRedirect(HttpSession session,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		
		System.out.println("inside application controller");
		
		String page = request.getParameter("action");
				
		Applicant applicant = (Applicant)session.getAttribute("loggedApplicant");
		
		System.out.println("Applicant"+applicant);
		
		if(applicant==null) {
			response.sendRedirect("/onlinejobportal/action.php?type=applicant");
			return null;
		}
		
		if (page.equals("viewAvailable")) {
			
			//Applicant applicant = (Applicant)session.getAttribute("loggedApplicant");
			
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
		} else if(page.equals("viewApplied")){
			
			System.out.println("inside applied jobs controller");
			//Applicant applicant = (Applicant)session.getAttribute("loggedApplicant");
			
			long id = applicant.getId();
			
			request.setAttribute("jobs",applicationDAO.list(id));
			
			List<Application> list1 = applicationDAO.list(id);
			
			for(Application application : list1) {
				System.out.println("JobID: "+application.getJobs().getId());
				System.out.println("JobID: "+application.getJobs().getJobName());
			}
			
			System.out.println("appliedjobs"+applicationDAO.list(id));
			
			return ("AppliedJobs");
		} else if(page.equals("update")){
			
			System.out.println("inside update account controller");
			model.addAttribute("updateApplicant",applicant);
			return ("UpdateApplicant");
			
		} else if(page.equals("logout")){
			session.removeAttribute("loggedApplicant");
			session.invalidate();
			try {
				response.sendRedirect("/onlinejobportal/action.php?type=applicant");
			}catch (Exception e) {

			}
			
			return(null);
		}
		return null;
	}
	
}
