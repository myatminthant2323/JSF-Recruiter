package com.mmit.model.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;

import com.mmit.model.entity.Recruiter;


@Stateless
public class LoginService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;
	
	public Recruiter findByName(String name) throws NoResultException{
		String sql= "SELECT r FROM Recruiter r WHERE r.name = :name";
		TypedQuery<Recruiter> query = em.createQuery(sql, Recruiter.class);
		query.setParameter("name", name);
		Recruiter recruiter = query.getSingleResult();
		return recruiter;
	}

	public Recruiter check(String email, String password) throws NotFoundException{
		TypedQuery<Recruiter> query = em.createNamedQuery("Recruiter.login",Recruiter.class );
		query.setParameter("email" , email);
		query.setParameter("password" , password);
		
		Recruiter result_rec = query.getSingleResult();
		System.out.println(result_rec.getName());
		return query.getSingleResult();
		
		
	}
}
