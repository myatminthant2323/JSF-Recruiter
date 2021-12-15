package com.mmit.model.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.mmit.model.entity.InterviewType;
import com.mmit.model.service.InterviewTypeService;

@Named
@RequestScoped
public class InterviewTypeBean {
	
	private InterviewType interviewType; 
	private List<InterviewType> interviewTypeList;
	
	@EJB
	private InterviewTypeService service;
	
	@PostConstruct
	private void initialize() {
		interviewType = new InterviewType();
		interviewTypeList = service.findAll();
		
		
	}

	public InterviewType getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(InterviewType interviewType) {
		this.interviewType = interviewType;
	}

	public List<InterviewType> getInterviewTypeList() {
		return interviewTypeList;
	}

	public void setInterviewTypeList(List<InterviewType> interviewTypeList) {
		this.interviewTypeList = interviewTypeList;
	}

	

	
	
	

}
