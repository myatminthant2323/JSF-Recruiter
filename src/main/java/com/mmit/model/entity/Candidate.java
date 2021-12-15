package com.mmit.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: Candidate
 *
 */
@Entity
@NamedQuery(name="Candidate.findAll",query="SELECT c FROM Candidate c")
@NamedQuery(name="Candidate.getUndeployedCandidates",query="SELECT c FROM Candidate c WHERE c.id NOT IN ( select candidate.id from JobPipeline p where p.joborder.id = :joborderid) AND c.is_active='yes'")



/*
 * "SELECT new com.mmit.entity.dto.ItemCategory(i,i.category.name) FROM Item i";
 */
public class Candidate implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Basic(optional = false)
	private String name;
	@Basic(optional = false)
	private String phone;
	@ManyToOne
	@JoinColumn(name = "township_id", referencedColumnName = "id")
	private Township township;
	@Basic(optional = false)
	private int expectedSalary;
	@Basic(optional = false)
	private String key_skills;
	@Basic(optional = false)
	@Column(unique = true, nullable = false)
	private String email;
	private LocalDate entry_date;
	@ManyToOne
	@JoinColumn(name = "entryBy", referencedColumnName = "id")
	private  Recruiter entryBy;
	private String cv_form;
	private String is_active;
	private String remark;
	@ManyToOne
	@JoinColumn(name = "availiability_id", referencedColumnName = "id")
	private AvailabilityType availiability;
	private LocalDate modify_date;
	@ManyToOne
	@JoinColumn(name = "modifyBy", referencedColumnName = "id")
	private Recruiter modifyBy;
	@OneToMany(mappedBy = "candidate", cascade = REMOVE)
	private List<JobPipeline> jobpipeline;
	
	
	
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

	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	public int getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(int expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getKey_skills() {
		return key_skills;
	}

	public void setKey_skills(String key_skills) {
		this.key_skills = key_skills;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCv_form() {
		return cv_form;
	}

	public void setCv_form(String cv_form) {
		this.cv_form = cv_form;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public AvailabilityType getAvailiability() {
		return availiability;
	}

	public void setAvailiability(AvailabilityType availiability) {
		this.availiability = availiability;
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
	
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	private static final long serialVersionUID = 1L;

	public Candidate() {
		super();
	}
   
}
