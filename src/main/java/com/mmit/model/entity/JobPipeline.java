package com.mmit.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: JobPipeline
 *
 */
@Entity
@NamedQuery(name="JobPipeline.findAll",query="SELECT jp FROM JobPipeline jp")
@NamedQuery(name="JobPipeline.findByCandidate",query="SELECT jp FROM JobPipeline jp WHERE jp.candidate.id = :candidateId")
@NamedQuery(name="JobPipeline.findByJobOrder",query="SELECT jp FROM JobPipeline jp WHERE jp.joborder.id = : joborderId")
@NamedQuery(name="JobPipeline.findInActiveJobOrder",query="SELECT jp FROM JobPipeline jp WHERE jp.joborder.id IN (SELECT jo.id FROM JobOrder jo WHERE jo.is_active = 'yes') AND jp.candidatejoborderstatus.id IN (200,500,600,700,800)")
@NamedQuery(name="JobPipeline.jobPipelineRecentHire",query="SELECT jp FROM JobPipeline jp WHERE jp.candidatejoborderstatus.id = 900 AND jp.candidate.id IN (SELECT c.id FROM Candidate c WHERE c.is_active = 'yes') AND jp.joborder.id IN (SELECT jo.id FROM JobOrder jo WHERE jo.is_active = 'yes')  ORDER BY jp.modify_date DESC")
/*
 * @NamedQuery(name="JobPipeline.jobPipelineInterviewByMonth",
 * query="SELECT new com.mmit.entity.dto.CountByMonthDTO(MONTH(entry_date),COUNT(id)) FROM JobPipeline jp WHERE jp.candidatejoborderstatus.id IN (500,600,700) GROUP BY MONTH(jp.entry_date)"
 * )
 * 
 * @NamedQuery(name="JobPipeline.jobPipelineHireByMonth",
 * query="SELECT new com.mmit.entity.dto.CountByMonthDTO(MONTH(entry_date),COUNT(id)) FROM JobPipeline jp WHERE jp.candidatejoborderstatus.id = 900 GROUP BY MONTH(jp.entry_date)"
 * )
 */

/*@NamedQuery(name="JobPipeline.JobPipelineInterviewByYear",query="SELECT new com.mmit.entity.dto.CountInterviewANDHireByYearDTO(I.year, I.count_interview, O.count_interview) FROM (SELECT new com.mmit.entity.dto.CountInterviewByYearDTO(YEAR(entry_date),COUNT(id)) FROM JobPipeline jp WHERE jp.candidatejoborderstatus.id IN (500,600,700) GROUP BY YEAR(jp.entry_date)) AS I LEFT JOIN (SELECT new com.mmit.entity.dto.CountInterviewByYearDTO(YEAR(entry_date),COUNT(id)) FROM JobPipeline jp WHERE jp.candidatejoborderstatus.id = 900 GROUP BY YEAR(jp.entry_date)) AS O ON I.year = O.year GROUP BY I.year UNION SELECT new com.mmit.entity.dto.CountInterviewANDHireByYearDTO(O.year, I.count_interview, O.count_interview) FROM (SELECT new com.mmit.entity.dto.CountInterviewByYearDTO(YEAR(entry_date),COUNT(id)) FROM JobPipeline jp WHERE jp.candidatejoborderstatus.id IN (500,600,700) GROUP BY YEAR(jp.entry_date)) AS  I RIGHT JOIN (SELECT new com.mmit.entity.dto.CountInterviewByYearDTO(YEAR(entry_date),COUNT(id)) FROM JobPipeline jp WHERE jp.candidatejoborderstatus.id = 900 GROUP BY YEAR(jp.entry_date)) AS  O ON I.year = O.year GROUP BY I.year")*/





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
	
	private LocalDateTime entry_date;
	@ManyToOne
	@JoinColumn(name = "entryBy", referencedColumnName = "id")
	private  Recruiter entryBy;
	
	private LocalDateTime modify_date;
	@ManyToOne
	@JoinColumn(name = "modifyBy", referencedColumnName = "id")
	private Recruiter modifyBy;
	
	@OneToMany(mappedBy = "piplelineId", cascade = REMOVE)
	private List<Pipelinehistory> pipelinehistory;
	
	@OneToMany(mappedBy = "candidateJobOrder", cascade = REMOVE)
	private List<InterviewRecord> candidateJobOrderInterviewRecord;
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



	public Recruiter getEntryBy() {
		return entryBy;
	}


	public void setEntryBy(Recruiter entryBy) {
		this.entryBy = entryBy;
	}



	public LocalDateTime getEntry_date() {
		return entry_date;
	}


	public void setEntry_date(LocalDateTime entry_date) {
		this.entry_date = entry_date;
	}


	public LocalDateTime getModify_date() {
		return modify_date;
	}


	public void setModify_date(LocalDateTime modify_date) {
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
