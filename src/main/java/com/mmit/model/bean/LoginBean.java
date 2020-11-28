package com.mmit.model.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.mmit.model.entity.Recruiter;
import com.mmit.model.service.LoginService;
@Named
@SessionScoped
public class LoginBean implements Serializable{
	private String email;
	private String password;
	
	private Recruiter loginUser;
	
	@EJB
	private LoginService service;
	
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	private void initialize() {
		loginUser = new Recruiter();
		System.out.println("Login User: "+loginUser.getName());
	}
	
	// actionlistener method
	public void checkUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			loginUser = service.check(email,password);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("The username or password you entered is incorrect ! ");
			context.addMessage("login", msg);
			context.validationFailed();
		}
		
	}
	
	// action method
	public String authenticateUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(context.isValidationFailed())
			return null;
		return "/views/companys?faces-redirect=true"; 
		
	}
	
public String logout() {
	// destroy session
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=true";
		
	}
	
	public Recruiter getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(Recruiter loginUser) {
		this.loginUser = loginUser;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
