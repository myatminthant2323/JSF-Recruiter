package com.mmit.model.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.mmit.model.entity.Township;
import com.mmit.model.service.TownshipService;

@Named
@RequestScoped
public class TownshipBean {
	
	private Township township; 
	private List<Township> townshipList;
	
	@EJB
	private TownshipService service;
	
	@PostConstruct
	private void initialize() {
		township = new Township();
		townshipList = service.findAll();
		
		
	}

	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	public List<Township> getTownshipList() {
		return townshipList;
	}

	public void setTownshipList(List<Township> townshipList) {
		this.townshipList = townshipList;
	}
	
	

}
