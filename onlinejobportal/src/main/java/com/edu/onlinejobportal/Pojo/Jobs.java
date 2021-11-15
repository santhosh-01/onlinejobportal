package com.edu.onlinejobportal.Pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Jobs {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	
	@Column
	private String jobName;
	
	@Column
	private String description;
	
	@Column
	private String city;
	
	@Column
	private String state;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Company company;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy = "jobs")
	private Set<Application> application;

	public Set<Application> getApplication() {
		return application;
	}

	public void setApplication(Set<Application> application) {
		this.application = application;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
