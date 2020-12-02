package com.mmit.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AvailabilityType
 *
 */
@Entity
@NamedQuery(name="CandidateJoborderStatus.findAll",query="SELECT cjs FROM CandidateJoborderStatus cjs")
public class CandidateJoborderStatus implements Serializable {

	@Id
	private int id;
	private String short_description;
	
	@OneToMany(mappedBy = "candidatejoborderstatus")
	private List<JobPipeline> jobpipeline;
	
	@OneToMany(mappedBy = "fromstatus")
	private List<Pipelinehistory> fromstatus_pipelinehistory;
	
	@OneToMany(mappedBy = "tostatus")
	private List<Pipelinehistory> tostatus_pipelinehistory;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public List<JobPipeline> getJobpipeline() {
		return jobpipeline;
	}

	public void setJobpipeline(List<JobPipeline> jobpipeline) {
		this.jobpipeline = jobpipeline;
	}



	private static final long serialVersionUID = 1L;

	public CandidateJoborderStatus() {
		super();
	}

	
	
	
   
}
