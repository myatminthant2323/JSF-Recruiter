package com.mmit.model.bean;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import com.mmit.model.entity.Candidate;
import com.mmit.model.service.CandidateService;

import java.io.File;
import java.io.Serializable;

@Named
@ViewScoped
public class CandidateBean implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private Candidate candidate;
	
	
	private List<Candidate> candidateList;
	
	private Part cv_form;
	@EJB
	private CandidateService service;
	
	
	@Inject
	private LoginBean loginbean;
	
	@PostConstruct
	private void initialize() {
		String canid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("candidateid");
		if(canid != null) {
			candidate = service.findById(Integer.parseInt(canid));
			if(candidate.getIs_active().equals("yes")) {
				candidate.setIs_active("true");
			}else {
				candidate.setIs_active("false");
			}
			
		}else {
			candidate = new Candidate();
		}
		
		candidateList = service.findAll();
		
	}
	
	public void uploadFile() {
		System.out.println("This is upload file operation");
		// Upload File
		try {
				String uploadFileName = cv_form.getSubmittedFileName();
				
				ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				
				String dirPath = context.getRealPath("") + File.separator + "CV_Uploads";
				File rootDir = new File(dirPath);
				if(!rootDir.exists())
					rootDir.mkdirs();
				
				cv_form.write(rootDir + File.separator + uploadFileName);
				candidate.setCv_form(uploadFileName);
		} catch (Exception e) {
					FacesMessage message = new FacesMessage(e.getMessage());
					FacesContext.getCurrentInstance().addMessage(null, message);
					return;
				}
	}
	
	public String removeCandidate(int cid) {
		service.delete(cid);
		return "/views/candidates?faces-redirect=true";
		
	}
	
	public String saveCandidate() {
		
		if(candidate.getIs_active().equals("true")) {
			candidate.setIs_active("yes");
		}else {
			candidate.setIs_active("no");
		}
		
		if (candidate.getId() == 0) {
			candidate.setEntry_date(LocalDate.now());
			candidate.setEntryBy(loginbean.getLoginUser());
		}else {
			candidate.setModify_date(LocalDate.now());
			candidate.setModifyBy(loginbean.getLoginUser());
		}
		service.save(candidate);
		return "/views/candidates?faces-redirect=true";
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public List<Candidate> getCandidateList() {
		return candidateList;
	}

	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}

	public Part getCv_form() {
		return cv_form;
	}

	public void setCv_form(Part cv_form) {
		this.cv_form = cv_form;
	}
	
	

	

	
}
