package com.mmit.model.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.mmit.config.AppException;
import com.mmit.model.service.RecruiterService;

@Named
@ViewScoped
public class ChangePasswordBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String oldPassword;
	private String newPassword;
	private String  confirmPassword;
	@EJB
	private RecruiterService service;
	
	public String changePassword() {
		try {
			service.changPassword(oldPassword,newPassword,confirmPassword);
		}catch(AppException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/index?faces-redirect=true";
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
