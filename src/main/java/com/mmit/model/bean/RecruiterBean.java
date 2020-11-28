package com.mmit.model.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.mmit.model.entity.Recruiter;
import com.mmit.model.service.RecruiterService;

@Named
@RequestScoped
public class RecruiterBean {
	
	private Recruiter recruiter;
	
	private List<Recruiter> recruiterList;
	
	@EJB
	private RecruiterService service;
	
	@PostConstruct
	private void initialize() {
		String recid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("recruiterid");
		if(recid != null)
			recruiter = service.findById(Integer.parseInt(recid));
		else
			recruiter = new Recruiter();
		recruiterList = service.findAll();
	}
	
	public String saveRecruiter() {
		
		service.save(recruiter);
		return "/views/recruiters?faces-redirect=true";
	}
	
	public String removeRecruiter(int rid) {
		 service.delete(rid);
		 return "/views/recruiters?faces-redirect=true;";
	 }

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

	public List<Recruiter> getRecruiterList() {
		return recruiterList;
	}

	public void setRecruiterList(List<Recruiter> recruiterList) {
		this.recruiterList = recruiterList;
	}
	
	

}
