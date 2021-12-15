package com.mmit.model.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.mmit.model.entity.Pipelinehistory;
import com.mmit.model.service.ActivityServices;

@Named
@RequestScoped
public class ActivitiesBean {
	
	private Pipelinehistory pipelineHistory; 
	private List<Pipelinehistory> pipelinehistoryList;
	
	@EJB
	private ActivityServices service;
	
	@PostConstruct
	private void initialize() {
		pipelineHistory = new Pipelinehistory();
		pipelinehistoryList = service.findAll();
		System.out.println(pipelinehistoryList);
	}

	public Pipelinehistory getPipelineHistory() {
		return pipelineHistory;
	}

	public void setPipelineHistory(Pipelinehistory pipelineHistory) {
		this.pipelineHistory = pipelineHistory;
	}

	public List<Pipelinehistory> getPipelinehistoryList() {
		return pipelinehistoryList;
	}

	public void setPipelinehistoryList(List<Pipelinehistory> pipelinehistoryList) {
		this.pipelinehistoryList = pipelinehistoryList;
	}

	
	
	
	

	
	
	

}
