package com.mmit.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: JobPipeline
 *
 */
@Entity
@NamedQuery(name="JobPipeline.findAll",query="SELECT jp FROM JobPipeline jp")
@NamedQuery(name="JobPipeline.findByCandidate",query="SELECT jp FROM JobPipeline jp WHERE jp.candidate.id = :candidateId")
@NamedQuery(name="JobPipeline.findByJobOrder",query="SELECT jp FROM JobPipeline jp WHERE jp.joborder.id = : joborderId")


@Table(name = "candidate_job_order")
public class JobPipeline implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "candidate_id", referencedColumnName = "id")
	private Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name = "joborder_id", referencedColumnName = "id")
	private JobOrder joborder;
	@ManyToOne
	@JoinColumn(name = "candidatejoborderstatus_id", referencedColumnName = "id")
	private CandidateJoborderStatus candidatejoborderstatus;
	
	private LocalDate entry_date;
	@ManyToOne
	@JoinColumn(name = "entryBy", referencedColumnName = "id")
	private  Recruiter entryBy;
	
	private LocalDate modify_date;
	@ManyToOne
	@JoinColumn(name = "modifyBy", referencedColumnName = "id")
	private Recruiter modifyBy;
	
	private static final long serialVersionUID = 1L;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Candidate getCandidate() {
		return candidate;
	}


	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}


	public JobOrder getJoborder() {
		return joborder;
	}


	public void setJoborder(JobOrder joborder) {
		this.joborder = joborder;
	}


	public CandidateJoborderStatus getCandidatejoborderstatus() {
		return candidatejoborderstatus;
	}


	public void setCandidatejoborderstatus(CandidateJoborderStatus candidatejoborderstatus) {
		this.candidatejoborderstatus = candidatejoborderstatus;
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


	public JobPipeline() {
		super();
	}
   
}
