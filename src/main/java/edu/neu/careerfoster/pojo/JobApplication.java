package edu.neu.careerfoster.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JOB_APPLICATIONS")
public class JobApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID", unique=true, nullable = false)
	private long id;
	
	@Column(name = "FILE_NAME", length = 100)
	private String fileName;
	@Column(name = "FILE_DATA", length = 1000000000)
	private byte[] data;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private AppUsers user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "jobId")
	private JobDetails jobdetails;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public AppUsers getUser() {
		return user;
	}

	public void setUser(AppUsers user) {
		this.user = user;
	}

	public JobDetails getJobdetails() {
		return jobdetails;
	}

	public void setJobdetails(JobDetails jobdetails) {
		this.jobdetails = jobdetails;
	}
}
