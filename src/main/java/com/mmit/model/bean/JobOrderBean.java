package com.mmit.model.bean;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.model.entity.JobOrder;
import com.mmit.model.service.JobOrderService;

@Named
@RequestScoped
public class JobOrderBean {
	
	private  JobOrder joborder;
	private List<JobOrder> joborderlist;
	
	
	@EJB
	private JobOrderService service;
	
	@Inject
	private LoginBean loginbean;
	
	@PostConstruct
	public void initialize() {
		String joborderid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("joborderid");
		if(joborderid != null) {
			 joborder = service.findById(Integer.parseInt(joborderid));
			 if(joborder.getIs_active().equals("yes")) {
					joborder.setIs_active("true");
				}else {
					joborder.setIs_active("false");
				}
		}else {
			joborder = new JobOrder();
		}
		
		 joborderlist = service.findAll();
		
	}
	
	public String removeJobOrder(int jid) {
		service.delete(jid);
		return "/views/joborders?faces-redirect=true";
		
	}
	
	public String saveJobOrder() {
		
		if(joborder.getIs_active().equals("true")) {
			joborder.setIs_active("yes");
		}else {
			joborder.setIs_active("no");
		}
		
		if (joborder.getId() == 0) {
			joborder.setEntry_date(LocalDate.now());
			joborder.setEntryBy(loginbean.getLoginUser());
		}else {
			JobOrder joborderobj = service.findById(joborder.getId());
			joborder.setEntryBy(joborderobj.getEntryBy());
			joborder.setEntry_date(joborderobj.getEntry_date());
			joborder.setModify_date(LocalDate.now());
			joborder.setModifyBy(loginbean.getLoginUser());
		}
		service.save(joborder);
		return "/views/joborders?faces-redirect=true";
	}

	public JobOrder getJoborder() {
		return joborder;
	}

	public void setJoborder(JobOrder joborder) {
		this.joborder = joborder;
	}

	public List<JobOrder> getJoborderlist() {
		return joborderlist;
	}

	public void setJoborderlist(List<JobOrder> joborderlist) {
		this.joborderlist = joborderlist;
	}

	
	
	
	
	
	

}
