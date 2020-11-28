package com.mmit.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.model.entity.CandidateJoborderStatus;

@Stateless
public class CandidateJobOrderStatusService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;

	

	public CandidateJoborderStatus findById(int id) {
		return em.find(CandidateJoborderStatus.class, id);
	}
}
