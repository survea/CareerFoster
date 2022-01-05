package edu.neu.careerfoster.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import edu.neu.careerfoster.exception.JobsPostedException;
import edu.neu.careerfoster.pojo.AppUsers;
import edu.neu.careerfoster.pojo.JobApplication;
import edu.neu.careerfoster.pojo.JobDetails;

public class EmployerDAO extends DAO {
	
	//method to create a new job
	public JobDetails postJob(JobDetails job) {
		try {
			begin();
			getSession().save(job);
			commit();
			close();
			return job;
		}catch(HibernateException e) {
			rollback();
			e.printStackTrace();
			return null;
		}
	}
	
	//method to list all jobs
	public List<JobDetails> listJobPosts(AppUsers user) throws JobsPostedException{
		try {
			begin();
			int userid = user.getUserid();
			Query query = getSession().createQuery("from JobDetails where user = :userid");
			query.setInteger("userid", userid);
			List<JobDetails> jobPost = query.list();
			commit();
			close();
			return jobPost;
		}catch(HibernateException e) {
			rollback();
			throw new JobsPostedException("Could not find any posts for you!", e);
		}
	}
	
	//method to delete a job
	public boolean delete(long jobid) throws JobsPostedException{
		try {
			begin();
			Query query = getSession().createQuery("delete from JobDetails where id = :jobid");
			query.setLong("jobid", jobid);
			query.executeUpdate();
			commit();
			close();
			return true;
		}catch(HibernateException e) {
			rollback();
			throw new JobsPostedException("Error in deleting the job post", e);
		}
	}
	
//	method to update a job
	public JobDetails updateJobPost(long jobid) throws JobsPostedException{
		try {
			begin();
			Query query = getSession().createQuery("from JobDetails where id = :jobid");
			query.setLong("jobid", jobid);
			JobDetails jobdetails = (JobDetails) query.uniqueResult();
			commit();
			close();
			return jobdetails;
		}catch(HibernateException e) {
			rollback();
			throw new JobsPostedException("Could not update the job at the DAO level", e);
		}
	}
	
//	method to create a new job
	public JobDetails onClickUpdateJobPost(JobDetails jobdetails, long newJobid) throws JobsPostedException{
		try {
			begin();
			String title = jobdetails.getJobTitle();
			String company = jobdetails.getCompanyName();
			String jobType = jobdetails.getJobType();
			String state = jobdetails.getState();
			String url = jobdetails.getJobUrl();
			String description = jobdetails.getDescription();
			
			Query query = getSession().createQuery("update JobDetails set jobTitle = :title, companyName = :company, jobType = :jobType, state = :state, jobUrl = :url, description = :description where id = :newJobid");
			query.setString("title", title);
			query.setString("company", company);
			query.setString("jobType", jobType);
			query.setString("state", state);
			query.setString("url", url);
			query.setString("description", description);
			query.setLong("newJobid", newJobid);
			query.executeUpdate();
			commit();
			close();
			return jobdetails;
		}catch(HibernateException e) {
			rollback();
			throw new JobsPostedException("Error occured while updating the job at the DAO Level", e);
		}
	}
	
	//method to get list of candidates for a job
	public List<JobApplication> listOfCandidatesApplied(long jobid) throws JobsPostedException{
		try {
			begin();
			Query query = getSession().createQuery("from JobApplication where jobdetails= :jobid");
			query.setLong("jobid", jobid);
			List<JobApplication> application = query.list();
			commit();
			close();
			return application;
		}catch (HibernateException e) {
            rollback();
            throw new JobsPostedException("Could not find candidate", e);
        }
	}
	
	//method to get candidates information
	public List<AppUsers> getUserInfo(int userid) throws JobsPostedException{
		try {
			begin();
			System.out.println("PART2: INSIDE getUserInfo DAO METHOD ");
			Query query = getSession().createQuery("from AppUsers where userid= :userid");
			query.setInteger("userid", userid);
			List<AppUsers> userinfo = query.list();
			commit();
			close();
			return userinfo;
		}catch (HibernateException e) {
            rollback();
            throw new JobsPostedException("Could not find candidate info", e);
        }
	}
	
}

