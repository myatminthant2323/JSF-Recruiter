package com.mmit.model.service;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.CandidateJoborderStatus;

@Stateless
public class CandidateJobOrderStatusService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;

	

	public CandidateJoborderStatus findById(int id) {
		return em.find(CandidateJoborderStatus.class, id);
	}



	public List<CandidateJoborderStatus> findAll() {
		TypedQuery<CandidateJoborderStatus> query = em.createNamedQuery("CandidateJoborderStatus.findAll", CandidateJoborderStatus.class);
		return query.getResultList();
	}
}
