package com.mmit.model.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.mmit.model.entity.CandidateJoborderStatus;
import com.mmit.model.service.CandidateJobOrderStatusService;

@Named
@RequestScoped
public class CandidateJobOrderStatusBean {
	
	private CandidateJoborderStatus joborderStatus; 
	private List<CandidateJoborderStatus> joborderStatusList;
	
	@EJB
	private CandidateJobOrderStatusService service;
	
	@PostConstruct
	private void initialize() {
		joborderStatus = new CandidateJoborderStatus();
		joborderStatusList = service.findAll();
		
		
	}

	public CandidateJoborderStatus getJoborderStatus() {
		return joborderStatus;
	}

	public void setJoborderStatus(CandidateJoborderStatus joborderStatus) {
		this.joborderStatus = joborderStatus;
	}

	public List<CandidateJoborderStatus> getJoborderStatusList() {
		return joborderStatusList;
	}

	public void setJoborderStatusList(List<CandidateJoborderStatus> joborderStatusList) {
		this.joborderStatusList = joborderStatusList;
	}

	
	
	

}
