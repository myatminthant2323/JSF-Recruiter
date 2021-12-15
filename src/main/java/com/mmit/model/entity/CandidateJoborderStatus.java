package com.mmit.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((short_description == null) ? 0 : short_description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateJoborderStatus other = (CandidateJoborderStatus) obj;
		if (id != other.id)
			return false;
		if (short_description == null) {
			if (other.short_description != null)
				return false;
		} else if (!short_description.equals(other.short_description))
			return false;
		return true;
	}

	

	
	
	
   
}
