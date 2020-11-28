package com.mmit.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.AvailabilityType;

@Stateless
public class AvailabilityTypeService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;

	public List<AvailabilityType> findAll() {
		TypedQuery<AvailabilityType> query = em.createNamedQuery("AvailabilityType.findAll", AvailabilityType.class);
		return query.getResultList();
	}

	public AvailabilityType findById(int id) {
		return em.find(AvailabilityType.class, id);
	}
}
