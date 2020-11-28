package com.mmit.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name="JobOrder.findbyCompany",query="SELECT j FROM JobOrder j WHERE j.company.id = :companyid")
@NamedQuery(name="JobOrder.findAll",query="SELECT j FROM JobOrder j")
public class JobOrder implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Basic(optional = false)
	private String  job_position;
	@Basic(optional = false)
	private String job_description;
	@Basic(optional = false)
	private int basic_salary;
	@Basic(optional = false)
	private int total_employee;
	
	private LocalDate entry_date;
	
	@ManyToOne
	@JoinColumn(name = "entryBy", referencedColumnName = "id")
	private Recruiter entryBy;
	
	private LocalDate modify_date;
	
	@ManyToOne
	@JoinColumn(name = "modifyBy", referencedColumnName = "id")
	private Recruiter modifyBy ;
	
	private String is_active;
	
	@ManyToOne
	@JoinColumn(name = "company", referencedColumnName = "id")
	private Company company;
	
	@ManyToOne
	@JoinColumn(name = "job_location", referencedColumnName = "id")
	private Township job_location;
	
	@OneToMany(mappedBy = "joborder")
	private List<JobPipeline> jobpipeline;
	
	private static final long serialVersionUID = 1L;

	
	
	public List<JobPipeline> getJobpipeline() {
		return jobpipeline;
	}



	public void setJobpipeline(List<JobPipeline> jobpipeline) {
		this.jobpipeline = jobpipeline;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getJob_position() {
		return job_position;
	}



	public void setJob_position(String job_position) {
		this.job_position = job_position;
	}



	public String getJob_description() {
		return job_description;
	}



	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}



	public int getBasic_salary() {
		return basic_salary;
	}



	public void setBasic_salary(int basic_salary) {
		this.basic_salary = basic_salary;
	}



	public int getTotal_employee() {
		return total_employee;
	}



	public void setTotal_employee(int total_employee) {
		this.total_employee = total_employee;
	}



	public LocalDate getEntry_date() {
		return entry_date;
	}



	public void setEntry_date(LocalDate entry_date) {
		this.entry_date = entry_date;
	}



	public Recruiter getEntryBy() {
		return entryBy;
	}



	public void setEntryBy(Recruiter entryBy) {
		this.entryBy = entryBy;
	}



	public LocalDate getModify_date() {
		return modify_date;
	}



	public void setModify_date(LocalDate modify_date) {
		this.modify_date = modify_date;
	}



	public Recruiter getModifyBy() {
		return modifyBy;
	}



	public void setModifyBy(Recruiter modifyBy) {
		this.modifyBy = modifyBy;
	}



	public String getIs_active() {
		return is_active;
	}



	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}




	public Company getCompany() {
		return company;
	}



	public void setCompany(Company company) {
		this.company = company;
	}



	public Township getJob_location() {
		return job_location;
	}



	public void setJob_location(Township job_location) {
		this.job_location = job_location;
	}



	public JobOrder() {
		super();
	}
   
}
