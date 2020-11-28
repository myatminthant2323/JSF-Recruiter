package com.mmit.model.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.mmit.model.entity.AvailabilityType;
import com.mmit.model.service.AvailabilityTypeService;

@Named
@RequestScoped
public class AvailabilityTypeBean {
	
	private AvailabilityType availabilitytype; 
	private List<AvailabilityType> availabilitytypeList;
	
	@EJB
	private AvailabilityTypeService service;
	
	@PostConstruct
	private void initialize() {
		availabilitytype = new AvailabilityType();
		availabilitytypeList = service.findAll();
	}

	public AvailabilityType getAvailabilitytype() {
		return availabilitytype;
	}

	public void setAvailabilitytype(AvailabilityType availabilitytype) {
		this.availabilitytype = availabilitytype;
	}

	public List<AvailabilityType> getAvailabilitytypeList() {
		return availabilitytypeList;
	}

	public void setAvailabilitytypeList(List<AvailabilityType> availabilitytypeList) {
		this.availabilitytypeList = availabilitytypeList;
	}

	
	
	

	
	
	

}
