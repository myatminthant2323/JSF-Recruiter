package com.mmit.model.bean;

import java.io.Serializable;
import java.util.Map;
import javax.servlet.http.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.mmit.model.entity.Recruiter;
import com.mmit.model.service.LoginService;
@Named
@SessionScoped
public class LoginBean implements Serializable{
	private String email;
	private String password;
	private boolean rememberMe;
	private Recruiter loginUser;
	private Cookie cookies[];
	
	@EJB
	private LoginService service;
	
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	private void initialize() {
		loginUser = new Recruiter();
		System.out.println("Login User: "+loginUser.getName());
		FacesContext facesContext = FacesContext.getCurrentInstance();
		cookies = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
		if(cookies != null && cookies.length > 0){
			for (Cookie cookie : cookies) {
			  if(cookie.getName().equals("email")){
				  email = cookie.getValue();
			  }else if(cookie.getName().equals("password")){
				  password = cookie.getValue();
				  if(!password.equals("")) {
						 rememberMe = true; 
				  }
			  }
		}
		}
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
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		if(rememberMe) {
		 Cookie ckEmail = new Cookie("email", email); 
		 ckEmail.setMaxAge(3600);
		 response.addCookie(ckEmail); 
		 Cookie ckPassword = new Cookie("password",password); 
		 ckPassword.setMaxAge(3600);
		 response.addCookie(ckPassword);
		 ckPassword.setMaxAge(3600);
		}else {
			 Cookie ckEmail = new Cookie("email", ""); 
			 ckEmail.setMaxAge(0);
			 response.addCookie(ckEmail); 
			 Cookie ckPassword = new Cookie("password",""); 
			 ckPassword.setMaxAge(0);
			 response.addCookie(ckPassword);
			 ckPassword.setMaxAge(0);
			/*
			 * if(cookies != null && cookies.length > 0){ for (Cookie cookie : cookies) {
			 * if(cookie.getName().equals("email")){ cookie.setValue("");
			 * cookie.setPath("/"); cookie.setMaxAge(0); response.addCookie(cookie); }else
			 * if(cookie.getName().equals("password")){ cookie.setValue("");
			 * cookie.setPath("/"); cookie.setMaxAge(0); response.addCookie(cookie); } } }
			 */
		}
		return "/views/dashboard?faces-redirect=true"; 
		
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

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	

}
