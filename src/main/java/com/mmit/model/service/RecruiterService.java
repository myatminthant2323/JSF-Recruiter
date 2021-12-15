package com.mmit.model.service;

import java.security.Principal;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.mmit.model.bean.LoginBean;
import com.mmit.model.entity.Recruiter;
import com.mmit.config.AppException;

@Stateless
public class RecruiterService {
	
	@Inject
	private LoginBean loginbean;
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;
	
	public List<Recruiter> findAll() {
		TypedQuery<Recruiter> query = em.createNamedQuery("Recruiter.findAll", Recruiter.class);
		return query.getResultList();
	}

	public Recruiter findById(int id) {
		return em.find(Recruiter.class, id);
	}

	public void save(Recruiter recruiter) {
		if (recruiter.getId() == 0) 
			em.persist(recruiter);
		else
			em.merge(recruiter);
	}

	public void delete(int rid) {
		Recruiter r = em.find(Recruiter.class, rid);
		if(r.getEmail().equals(loginbean.getEmail())) {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();			
		}
		em.remove(r);
		
	}

	public Long getCountUser() {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(r) FROM Recruiter  r",Long.class);
		return query.getSingleResult();
	}

	public void changPassword(String oldPassword, String newPassword, String confirmPassword) {
		TypedQuery<Recruiter> query = em.createQuery("SELECT r FROM Recruiter r WHERE r.email = :email",Recruiter.class);
		query.setParameter("email",loginbean.getEmail());
		Recruiter rec = query.getSingleResult();
		
		if(!rec.getPassword().equals(oldPassword)) {
			throw new AppException("Old Password does not match !");
		}
		if(!newPassword.equals(confirmPassword)) {
			throw new AppException("New Password and Confirm Password do not match !");
		}
		rec.setPassword(newPassword);
		
	}
}
	
	
		
	

