package com.mmit.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Recruiter
 *
 */
@Entity
@NamedQuery(name="Recruiter.login",query="SELECT r FROM Recruiter r WHERE r.email = :email AND r.password = :password")
@NamedQuery(name="Recruiter.findAll",query="SELECT r FROM Recruiter r")
public class Recruiter implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Basic(optional = false)
	private String name;
	@Basic(optional = false)
	private String phone;
	@Basic(optional = false)
	private String email ;
	private String password;
	
	
	@OneToMany(mappedBy = "entryBy")
	private List<Company> company_entry;
	
	@OneToMany(mappedBy = "modifyBy")
	private List<Company> company_modify;
	
	@OneToMany(mappedBy = "entryBy")
	private List<JobOrder> joborder_entry;
	
	@OneToMany(mappedBy = "modifyBy")
	private List<JobOrder> joborder_modify;
	
	@OneToMany(mappedBy = "entryBy")
	private List<Candidate> candidate_entry;
	
	@OneToMany(mappedBy = "modifyBy")
	private List<JobPipeline> jobpipeline_modify;
	
	@OneToMany(mappedBy = "entryBy")
	private List<JobPipeline> jobpipeline_entry;
	private static final long serialVersionUID = 1L;
	
	public Recruiter() {
		super();
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public List<Company> getCompany_entry() {
		return company_entry;
	}



	public void setCompany_entry(List<Company> company_entry) {
		this.company_entry = company_entry;
	}



	public List<Company> getCompany_modify() {
		return company_modify;
	}



	public void setCompany_modify(List<Company> company_modify) {
		this.company_modify = company_modify;
	}



	public List<JobOrder> getJoborder_entry() {
		return joborder_entry;
	}



	public void setJoborder_entry(List<JobOrder> joborder_entry) {
		this.joborder_entry = joborder_entry;
	}



	public List<JobOrder> getJoborder_modify() {
		return joborder_modify;
	}



	public void setJoborder_modify(List<JobOrder> joborder_modify) {
		this.joborder_modify = joborder_modify;
	}



	public List<Candidate> getCandidate_entry() {
		return candidate_entry;
	}



	public void setCandidate_entry(List<Candidate> candidate_entry) {
		this.candidate_entry = candidate_entry;
	}



	public List<JobPipeline> getJobpipeline_modify() {
		return jobpipeline_modify;
	}



	public void setJobpipeline_modify(List<JobPipeline> jobpipeline_modify) {
		this.jobpipeline_modify = jobpipeline_modify;
	}



	public List<JobPipeline> getJobpipeline_entry() {
		return jobpipeline_entry;
	}



	public void setJobpipeline_entry(List<JobPipeline> jobpipeline_entry) {
		this.jobpipeline_entry = jobpipeline_entry;
	}
	
	
   
}
