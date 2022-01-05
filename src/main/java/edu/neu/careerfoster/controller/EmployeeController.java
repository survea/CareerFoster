package edu.neu.careerfoster.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.careerfoster.dao.EmployerDAO;
import edu.neu.careerfoster.exception.JobsPostedException;
import edu.neu.careerfoster.pojo.AppUsers;
import edu.neu.careerfoster.pojo.JobApplication;
import edu.neu.careerfoster.pojo.JobDetails;

@Controller
@RequestMapping(value = "/employeer/")
public class EmployeeController {
//	controller to post a new job
	@RequestMapping(value = "postjob.htm", method = RequestMethod.GET)
	public ModelAndView getJobPostForm(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		AppUsers appUsers = (AppUsers) request.getSession().getAttribute("name");
		request.getSession().setAttribute("name", appUsers);
		modelAndView.setViewName("JobPosting");
		return modelAndView;
	}
// controller to add new posted job in db
	@RequestMapping(value = "postjobsuccess.htm", method = RequestMethod.POST)
	public ModelAndView postAJob(HttpServletRequest request, EmployerDAO employerDao, ModelMap map) {
		String title = request.getParameter("jobtitle");
		String company = request.getParameter("job_company_name");
		String jobType = request.getParameter("job_type");
		String state = request.getParameter("state");
		String jobDescUrl = request.getParameter("job_url");
		String jobDesc = request.getParameter("job_description");
		Date postedOn = new Date();
		AppUsers appUsers = (AppUsers) request.getSession().getAttribute("name");
		JobDetails jobDetails = new JobDetails();
		jobDetails.setJobTitle(title);
		jobDetails.setCompanyName(company);
		jobDetails.setJobType(jobType);
		jobDetails.setState(state);
		jobDetails.setJobUrl(jobDescUrl);
		jobDetails.setDescription(jobDesc);
		jobDetails.setPostedOn(postedOn);
		jobDetails.setUser(appUsers);
		try {
			jobDetails = employerDao.postJob(jobDetails);
			if (jobDetails != null) {
				map.addAttribute("successMessage", "Job has been posted successfully!");
				return new ModelAndView("HomeEmp", "jobPost", jobDetails);
			} else {
				map.addAttribute("errorMessage", "Error occured in while posting your!");
				return new ModelAndView("JobPosting");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//controller to to view all the posted jobs
	@RequestMapping(value = "myjobposts.htm", method = RequestMethod.GET)
	public ModelAndView listMyPostedJobs(HttpServletRequest request, EmployerDAO employerDao) {
		try {
			AppUsers user = (AppUsers) request.getSession().getAttribute("name");
			List<JobDetails> jobPost = employerDao.listJobPosts(user);
			return new ModelAndView("PostedJobList", "jobPost", jobPost);
		} catch (JobsPostedException e) {
			return new ModelAndView("HomeEmp", "errorMessage", "Error occured while displaying your posted jobs");
		}
	}
// controller to delete the posted jobs
	@RequestMapping(value = "deleteMyJobposts.htm", method = RequestMethod.GET)
	public ModelAndView deleteJobPost(HttpServletRequest request, EmployerDAO employerDao) {
		HttpSession session = (HttpSession) request.getSession();
		String jobId = request.getParameter("jobID");
		try {
			if (jobId.equals(null)) {
				System.out.println("No Id found to delete the job post");
			} else {
				session.setAttribute("jobId", jobId);
				long id = Long.parseLong(jobId);
				employerDao.delete(id);
			}
			return new ModelAndView("JobDeleteMsg");
		} catch (JobsPostedException e) {
			return new ModelAndView("HomeEmp", "errorMessage", "Error occured while deleting the job post");
		}

	}
// controller to get the job details for form
	@RequestMapping(value = "updateJobDetails.htm", method = RequestMethod.GET)
	public ModelAndView updateJobPost(HttpServletRequest request, EmployerDAO employerDao) {
		HttpSession session =  request.getSession();
		String id = request.getParameter("jobID");
		AppUsers appUsers = (AppUsers) request.getSession().getAttribute("name");
		session.setAttribute("id", id);
		session.setAttribute("name", appUsers);
		long jobid = Long.parseLong(id);
		try {
			JobDetails jobDetails = employerDao.updateJobPost(jobid);
			request.setAttribute("jobDetails", jobDetails);
			return new ModelAndView("UpdateJob", "jobDetails", jobDetails);
		}catch(JobsPostedException e) {
			return new ModelAndView("HomeEmp", "errorMessage", "Error occured while updating the job post");
		}
	}
//	controller to update the job details
	@RequestMapping(value = "updateJobDetails.htm", method = RequestMethod.POST)
	public @ResponseBody ModelAndView onUpdateClick(HttpServletRequest request, EmployerDAO employerDao, ModelMap map, @ModelAttribute("jobdetails") JobDetails jobdetails) {
		HttpSession session =  request.getSession();
		String id = (String)session.getAttribute("id");
		AppUsers appUsers = (AppUsers) request.getSession().getAttribute("name");

		String title = request.getParameter("jobtitle");
		String company = request.getParameter("job_company_name");
		String jobType = request.getParameter("job_type");
		String state = request.getParameter("state");
		String jobDescUrl = request.getParameter("job_url");
		String jobDesc = request.getParameter("job_description");
		Date postedOn = new Date();
	
		jobdetails.setJobTitle(title);
		jobdetails.setCompanyName(company);
		jobdetails.setJobType(jobType);
		jobdetails.setState(state);
		jobdetails.setJobUrl(jobDescUrl);
		jobdetails.setDescription(jobDesc);
		jobdetails.setPostedOn(postedOn);
		try {
			long newJobId = Long.parseLong(id);
			JobDetails details = employerDao.onClickUpdateJobPost(jobdetails, newJobId);
			request.getSession().setAttribute("updatedJobDetails", details);
			session.setAttribute("name", appUsers);
			map.addAttribute("successMessage", "Job details has been updated successfully!");
			return new ModelAndView("HomeEmp", "jobDetails", details);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
// controller to view candidates
	@RequestMapping(value = "viewCandidatesApplied", method = RequestMethod.GET)
	public ModelAndView listCandidatesApplied(HttpServletRequest request,EmployerDAO employerDao) {
			AppUsers appUsers = (AppUsers) request.getSession().getAttribute("name");
			HttpSession session = request.getSession();
			String id = request.getParameter("jobID");
			session.setAttribute("id", id);
			session.setAttribute("name", appUsers);
			long jobid = Long.parseLong(id);
			try {
				List<JobApplication> listApplication = employerDao.listOfCandidatesApplied(jobid);
				if(listApplication.size()>0) {
					for(JobApplication ja : listApplication) {
						int userid = ja.getUser().getUserid();
						List<AppUsers> appUserInfo = employerDao.getUserInfo(userid);
						ModelAndView modelAndView = new ModelAndView("AppliedCandidate");
						modelAndView.addObject("listapp",listApplication);
						modelAndView.addObject("listuser",appUserInfo);
						return modelAndView;
					}
				} else {
					return new ModelAndView("HomeEmp", "errorMessage", "No Candidates have applied still");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
}
