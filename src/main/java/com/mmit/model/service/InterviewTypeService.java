package com.mmit.model.service;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.mmit.model.entity.InterviewType;

@Stateless
public class InterviewTypeService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;

	

	public InterviewType findById(int id) {
		return em.find(InterviewType.class, id);
	}



	public List<InterviewType> findAll() {
		TypedQuery<InterviewType> query = em.createNamedQuery("InterviewType.findAll", InterviewType.class);
		return query.getResultList();
	}
}
