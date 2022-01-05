package edu.neu.careerfoster.controller;

import java.util.List;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import edu.neu.careerfoster.dao.AppUsersDAO;
import edu.neu.careerfoster.dao.EmployerDAO;
import edu.neu.careerfoster.dao.RoleDAO;
import edu.neu.careerfoster.dao.StudentDAO;
import edu.neu.careerfoster.exception.JobsPostedException;
import edu.neu.careerfoster.exception.StudentClassException;
import edu.neu.careerfoster.pojo.AppUsers;
import edu.neu.careerfoster.pojo.JobDetails;
import edu.neu.careerfoster.pojo.Role;


@Controller
@RequestMapping(value = "/")
@SessionAttributes("/logout.htm")
public class HomePageController {
//	controller to register the user
	@RequestMapping(value = "register.htm", method = RequestMethod.POST)
	public String register(HttpServletRequest request, AppUsersDAO appUsersDao, ModelMap map) {
		boolean token = true;
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String email = request.getParameter("email");
		String username = request.getParameter("uname");
		String password = request.getParameter("pwd");
		String r= request.getParameter("role");
		AppUsers user = new AppUsers();
		Role role = new Role();
		token = appUsersDao.registerCriteria(email, username);
		if(token == true) {
			map.addAttribute("errorMessage", "Username or emailId has already been registered");
			return "home";
		}else {
			role.setRole_name(r);
			user.setFname(firstname);
			user.setLname(lastname);
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			user.setRole(role);
			try {
				user = appUsersDao.registerUser(user);
				map.addAttribute("successMessage", "Registration Successful!");
				return "home";
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
//	controller for logout
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	protected ModelAndView logout(HttpServletRequest request, ModelMap map, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView( "home");
	}
//	controller to register page
	@RequestMapping(value = "/signup.htm", method = RequestMethod.GET)
	public ModelAndView signup(HttpServletRequest request) throws Exception{
		return new ModelAndView( "register");
	}
	
// conrtoller to pass the login request
	@RequestMapping(value = "/login.htm", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView loginRequest(HttpServletRequest request, HttpServletResponse response, AppUsersDAO appUsersDao, RoleDAO roleDao, ModelMap map,EmployerDAO employerDao, StudentDAO studentDao){
		HttpSession httpSession = request.getSession(true);
		String username = request.getParameter("uname");
		String password = request.getParameter("pwd");
		try {
			AppUsers users = appUsersDao.get(username, password);
			if(users != null) {
				httpSession.setAttribute("name", users);				
					if(users.getRole().getRole_name().equalsIgnoreCase("employer")) {
						try {
							AppUsers user = (AppUsers) request.getSession().getAttribute("name");
							List<JobDetails> jobPost = employerDao.listJobPosts(user);
							return new ModelAndView("PostedJobList", "jobPost", jobPost);
						} catch (JobsPostedException e) {
							return new ModelAndView("errors", "errorMessage", "Please refresh and login again");
						}
					}else if(users.getRole().getRole_name().equalsIgnoreCase("student")) {
						AppUsers appUsers = (AppUsers) request.getSession().getAttribute("name");
						try {
							request.getSession().setAttribute("name", appUsers);
							List<JobDetails> jobDetails = studentDao.listAllJobs();
							request.getSession().setAttribute("jobID", jobDetails);
							return new ModelAndView("student-home", "errorMessage",
									"Login Successfull!");
						} catch (StudentClassException e) {
							return new ModelAndView("errors", "errorMessage", "Please refresh and login again");
						}
					}else {
						map.addAttribute("errorMessage", "Invalid username/password!");
						return new ModelAndView("home");
					}
			}else {
				map.addAttribute("errorMessage", "Invalid username/password!");
				return new ModelAndView("home");
			}
		}catch(Exception e) {			
			e.printStackTrace();
		}
		return null;
	}
}
