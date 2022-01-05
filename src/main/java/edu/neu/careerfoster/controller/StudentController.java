package edu.neu.careerfoster.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.careerfoster.dao.EmployerDAO;
import edu.neu.careerfoster.dao.StudentDAO;
import edu.neu.careerfoster.exception.JobsPostedException;
import edu.neu.careerfoster.exception.StudentClassException;
import edu.neu.careerfoster.pojo.AppUsers;
import edu.neu.careerfoster.pojo.JobApplication;
import edu.neu.careerfoster.pojo.JobDetails;

@Controller
@RequestMapping(value = "/student/")
public class StudentController {
//controller to view all the jobs
	@RequestMapping(value = "viewalljobs.htm", method = RequestMethod.GET)
	public ModelAndView viewAllJobs(HttpServletRequest request, StudentDAO studentDao) throws Exception {
		AppUsers appUsers = (AppUsers) request.getSession().getAttribute("name");
		try {
			request.getSession().setAttribute("name", appUsers);
			List<JobDetails> jobDetails = studentDao.listAllJobs();
			request.getSession().setAttribute("jobID", jobDetails);
			return new ModelAndView("JobsList", "allJobs", jobDetails);
		} catch (StudentClassException e) {
			return new ModelAndView("student-home", "errorMessage",
				"Please logout and login again!");
		}
	}
	
// controller to show upload pages
	@RequestMapping(value = "showUploadPage.htm", method = RequestMethod.GET)
	public ModelAndView showApplicationPage(HttpServletRequest request, EmployerDAO employerDao, StudentDAO studentDao)
			throws JobsPostedException, StudentClassException {
		HttpSession session = (HttpSession) request.getSession();
		String jobID = request.getParameter("jobID");
		long id = Long.parseLong(jobID);
		JobDetails jobdetails = employerDao.updateJobPost(id);
		session.setAttribute("jobID", jobdetails);
		AppUsers appUsers = (AppUsers) request.getSession().getAttribute("name");
		request.getSession().setAttribute("name", appUsers);
		return new ModelAndView("JobApplication");
	}
// controller to apply for a job
	@RequestMapping(value = "apply.htm", method = RequestMethod.POST)
	public ModelAndView handleFileUpload(HttpServletRequest request, StudentDAO studentDao,
			@RequestParam("fileUpload") CommonsMultipartFile[] fileUpload) throws Exception {
		boolean token = true;
		List<JobDetails> jd = new ArrayList<JobDetails>();
		JobDetails jobdetails = (JobDetails) request.getSession().getAttribute("jobID");
		AppUsers appUsers = (AppUsers) request.getSession().getAttribute("name");
		try {
			token = studentDao.userExists(appUsers, jobdetails);
			if (token == true) {
				return new ModelAndView("student-home", "errorMessage",
						"Your application has already been submitted. If you want to reapply please withdraw your application and apply again");
			} else {
				request.getSession().setAttribute("name", appUsers);
				request.getSession().setAttribute("JobID", jobdetails);
				if (fileUpload != null && fileUpload.length > 0) {
					for (CommonsMultipartFile aFile : fileUpload) {
						JobApplication uploadFile = new JobApplication();
						uploadFile.setFileName(aFile.getOriginalFilename());
						uploadFile.setData(aFile.getBytes());
						uploadFile.setUser(appUsers);
						uploadFile.setJobdetails(jobdetails);
						studentDao.saveFiles(uploadFile);
					}
				}
			}
			jd.add(jobdetails);
			request.getSession().setAttribute("jobList", jd);
			return new ModelAndView("student-home", "successMessage",
					"Your application has been submitted successfully");
		} catch (StudentClassException e) {
			return new ModelAndView("student-home", "errorMessage",
					"Please logout and login again");

		}
	}
// controller to view my jobs
	@RequestMapping(value = "viewMyJobs.htm", method = RequestMethod.GET)
	public ModelAndView listAllApplications(HttpServletRequest request, StudentDAO studentDao) throws Exception {
		try {
			AppUsers appUsers = (AppUsers) request.getSession().getAttribute("name");
			List<List<JobDetails>> jobs = new ArrayList<List<JobDetails>>();
			List<JobDetails> jobDetails = new ArrayList<JobDetails>();
			List<JobApplication> applicationsDetails = studentDao.listAppliedJobs(appUsers);
			
			if(applicationsDetails.size() > 0) {
				List<Long> id = new ArrayList<Long>();
				ModelAndView modelAndView = new ModelAndView("AppliedJobList");
				for (JobApplication ja : applicationsDetails) {
					long jobId = ja.getJobdetails().getId();
					if(!id.contains(jobId)) {
						jobDetails = studentDao.allJobDetails(jobId) ;
						jobs.add(jobDetails);
						id.add(jobId);
					}
				}
				modelAndView.addObject("jobs", jobs);
				return modelAndView;
			}
			return new ModelAndView("student-home", "successMessage",
					"You have not applied to any job. Please view the joblist and apply!");

		} catch (Exception e) {
			return new ModelAndView("student-home", "errorMessage",
					"Please try to logout and login again");

		}
	}
// controller to delete my applications
	@RequestMapping(value = "deleteMyApplication.htm", method = RequestMethod.GET)
	public ModelAndView deleteJobPost(HttpServletRequest request, StudentDAO studentDao) throws StudentClassException {
		HttpSession session = (HttpSession) request.getSession();
		String jobId = request.getParameter("jobID");
		try {
			if (jobId.equals(null)) {
				System.out.println("id not present");
			} else {
				session.setAttribute("jobId", jobId);
				long id = Long.parseLong(jobId);
				studentDao.delete(id);
			}
			return new ModelAndView("student-home", "successMessage",
					"Your application has been withdrawn successfully");
		} catch (StudentClassException e) {
			return new ModelAndView("student-home", "errorMessage",
					"Sorry not able to delete the Job. Please try again");
		}

	}

}
