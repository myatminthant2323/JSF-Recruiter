package com.mmit.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.Township;

@Stateless
public class TownshipService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;

	public List<Township> findAll() {
		TypedQuery<Township> query = em.createNamedQuery("Township.findAll", Township.class);
		return query.getResultList();
	}

	public Township findById(int id) {
		return em.find(Township.class, id);
	}
}
