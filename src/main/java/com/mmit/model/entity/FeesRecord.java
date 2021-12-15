package com.mmit.model.entity;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.*;
import static javax.persistence.TemporalType.TIME;

/**
 * Entity implementation class for Entity: InterviewRecord
 *
 */
@Entity
@NamedQuery(name="FeesRecord.findAll",query="SELECT f FROM FeesRecord f")
@NamedQuery(name="FeesRecord.findExistOrNot",query="SELECT f FROM FeesRecord f WHERE f.candidateJobOrder.id = :eidtcandidateJobOrderId AND f.status.id IN (800,900)")
@NamedQuery(name="FeesRecord.findByJobPipeline",query="SELECT f FROM FeesRecord f WHERE f.candidateJobOrder.id = :jobpipelineid")

@Table(name = "offer_place_candidaterecord")
public class FeesRecord implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "candidateJobOrder_id", referencedColumnName = "id")
	private JobPipeline candidateJobOrder;
	
	@OneToOne
	@JoinColumn(name = "status_id", referencedColumnName = "id")
	private CandidateJoborderStatus status;
	
	private long realsalary;
	
	private long allowance;
	
	private long agentfees;
	
	
	private LocalDateTime entry_date;
	@ManyToOne
	@JoinColumn(name = "entryBy", referencedColumnName = "id")
	private  Recruiter entryBy;
	
	private LocalDateTime modify_date;
	@ManyToOne
	@JoinColumn(name = "modifyBy", referencedColumnName = "id")
	private Recruiter modifyBy;
	
	private static final long serialVersionUID = 1L;

	public FeesRecord() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JobPipeline getCandidateJobOrder() {
		return candidateJobOrder;
	}

	public void setCandidateJobOrder(JobPipeline candidateJobOrder) {
		this.candidateJobOrder = candidateJobOrder;
	}

	

	public long getRealsalary() {
		return realsalary;
	}

	public void setRealsalary(long realsalary) {
		this.realsalary = realsalary;
	}

	public long getAllowance() {
		return allowance;
	}

	public void setAllowance(long allowance) {
		this.allowance = allowance;
	}

	public long getAgentfees() {
		return agentfees;
	}

	public void setAgentfees(long agentfees) {
		this.agentfees = agentfees;
	}


	
	public LocalDateTime getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(LocalDateTime entry_date) {
		this.entry_date = entry_date;
	}

	public void setModify_date(LocalDateTime modify_date) {
		this.modify_date = modify_date;
	}

	public Recruiter getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(Recruiter entryBy) {
		this.entryBy = entryBy;
	}

	

	public LocalDateTime getModify_date() {
		return modify_date;
	}

	public Recruiter getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Recruiter modifyBy) {
		this.modifyBy = modifyBy;
	}

	public CandidateJoborderStatus getStatus() {
		return status;
	}

	public void setStatus(CandidateJoborderStatus status) {
		this.status = status;
	}

	

	

	

	
	
	
	
	
   
}
