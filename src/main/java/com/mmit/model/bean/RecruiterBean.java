package com.mmit.model.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
	
	@Inject
	private LoginBean loginbean;
	
	@PostConstruct
	private void initialize() {
		String recid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("recruiterid");
		if(recid != null) {
			if("Staff".equals(String.valueOf(loginbean.getLoginUser().getLevel())) && !recid.equals(String.valueOf(loginbean.getLoginUser().getId())))
				recruiter = null;
			else
				recruiter = service.findById(Integer.parseInt(recid));
		System.out.println(recruiter);
		}else {
			if("Staff".equals(String.valueOf(loginbean.getLoginUser().getLevel())))
				recruiter = null;
			else
				recruiter = new Recruiter();
		}
		recruiterList = service.findAll();
	}
	
	public String saveRecruiter() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(recruiter.getLevel() == null) {
				Recruiter oldRecruiter = service.findById(recruiter.getId());
				oldRecruiter.setName(recruiter.getName());
				oldRecruiter.setPhone(recruiter.getPhone());
				oldRecruiter.setPassword(recruiter.getPassword());
				service.save(oldRecruiter);
				loginbean.setLoginUser(oldRecruiter);
			}else {
			service.save(recruiter);
			loginbean.setLoginUser(recruiter);
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Email already exists, Enter different email ! ");
			context.addMessage("email_unique_constraint", msg);
			String recid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("recruiterid");
			if(recid != null) {
				return "/views/recruiter-add?faces-redirect=true&recruiterid="+recruiter.getId();
			}else {
				return "/views/recruiter-add";
			}
			
		}
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
