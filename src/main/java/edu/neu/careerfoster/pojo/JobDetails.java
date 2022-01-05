package edu.neu.careerfoster.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "JOB_DETAILS")
public class JobDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique=true, nullable = false)
	private long id;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "userid")
	private AppUsers user;
	
	@Column(name = "JOB_TITLE")
	private String jobTitle;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "JOB_TYPE")
	private String jobType;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "JOB_POST_URL")
	private String jobUrl;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date postedOn;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<JobApplication> application = new ArrayList<JobApplication>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getJobUrl() {
		return jobUrl;
	}
	public void setJobUrl(String jobUrl) {
		this.jobUrl = jobUrl;
	}
	public AppUsers getUser() {
		return user;
	}
	public void setUser(AppUsers user) {
		this.user = user;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public List<JobApplication> getApplication() {
		return application;
	}
	public void setApplication(List<JobApplication> application) {
		this.application = application;
	}
}
