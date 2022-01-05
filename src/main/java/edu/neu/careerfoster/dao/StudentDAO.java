package edu.neu.careerfoster.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import edu.neu.careerfoster.exception.JobsPostedException;
import edu.neu.careerfoster.exception.StudentClassException;
import edu.neu.careerfoster.pojo.AppUsers;
import edu.neu.careerfoster.pojo.JobApplication;
import edu.neu.careerfoster.pojo.JobDetails;

public class StudentDAO extends DAO {

	// method to get list of all jobs
	public List<JobDetails> listAllJobs() throws StudentClassException {
		try {
			begin();
			Query query = getSession().createQuery("from JobDetails");
			List<JobDetails> allJobList = query.list();
			commit();
			close();
			return allJobList;
		} catch (HibernateException e) {
			rollback();
			throw new StudentClassException("Could not find employer", e);
		}
	}
	// Method for uploading files for job application
	public void saveFiles(JobApplication uploadFile) throws StudentClassException {
			try {
				begin();
				getSession().save(uploadFile);
				close();
			}catch(HibernateException e) {
				rollback();
				e.printStackTrace();
				throw new StudentClassException("Error occured while uploading files at DAO level",e);
			}
		}
	
	//Method for fetching application id from User id
		public JobApplication getJobId(AppUsers users) throws JobsPostedException{
			try {
				begin();
				Query query = getSession().createQuery("from JobApplication where user = '" + users + "' ");
				JobApplication application = (JobApplication) query.uniqueResult();
				commit();
				close();
				return application;
			}catch(HibernateException e) {
				rollback();
				throw new JobsPostedException("Error occured while updating the job at DAO level", e);
			}
		}
		
		// Criteria method for checking if the Student has already applied for this job
		public boolean userExists(AppUsers user, JobDetails jobdetails)throws StudentClassException{
			try {
				boolean result = true;
				begin();
				Criteria criteria = getSession().createCriteria(JobApplication.class);
				criteria.add(Restrictions.eq("jobdetails",jobdetails));
				List <JobApplication> results = criteria.list();
				commit();
				close();
				if(results.size() >= 2) {
						result = true;
					}
					else {
						result = false;
					}
				return result;
				
			}catch(HibernateException e) {
				rollback();
				e.printStackTrace();
				throw new StudentClassException("Error occured while fetching users at DAO level",e);
			}
		}
	
	// Method for listing all applications of the candidate
	public List<JobApplication> listAppliedJobs(AppUsers user)throws StudentClassException{
		try {
			begin();
			int userid = user.getUserid();
			Query query = getSession().createQuery("from JobApplication where user = :userid");
			query.setInteger("userid", userid);
			List<JobApplication> jobApplications = query.list();
			commit();
			close();
			return jobApplications;
		}catch(HibernateException e) {
			rollback();
			e.printStackTrace();
			throw new StudentClassException("Error occured while fetching Job list at DAO level",e);
		}
	}
	
	//Method for listing all the job details based on the job Id
	public List<JobDetails> allJobDetails(long jobId) throws StudentClassException{
		try {
			begin();
			Query query = getSession().createQuery("from JobDetails where id = :jobId");
			query.setLong("jobId", jobId);
			List<JobDetails> jobDetails = query.list();
			commit();
			close();
			return jobDetails;
		}catch(HibernateException e) {
			rollback();
			e.printStackTrace();
			throw new StudentClassException("Error occured while fetching job list at DAO level",e);
		}
	}
	
	//Method for deleting Job Application
		public boolean delete(long jobid) throws StudentClassException{
			try {
				begin();
				Query query = getSession().createQuery("delete from JobApplication where jobdetails = :jobid");
				query.setLong("jobid", jobid);
				query.executeUpdate();
				commit();
				close();
				return true;
			}catch(HibernateException e) {
				rollback();
				throw new StudentClassException("Error occured while withdrawal of job", e);
			}
		}
}
