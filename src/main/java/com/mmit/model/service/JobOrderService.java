package com.mmit.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.JobOrder;

@Stateless
public class JobOrderService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;

	public JobOrder findById(int id) {
		
		return em.find(JobOrder.class, id);
	}

	public List<JobOrder> findAll() {
		TypedQuery<JobOrder> query = em.createNamedQuery("JobOrder.findAll", JobOrder.class);
		return query.getResultList();
	}

	public void save(JobOrder joborder) {
		if (joborder.getId() == 0) {
			em.persist(joborder);
		}else {
			em.merge(joborder);
		}
	}
	
public void delete(int jid) {
		
	JobOrder joborder = em.find(JobOrder.class, jid);
		em.remove(joborder);
	}

public List<JobOrder> findbyCompany(int companyid) {
	TypedQuery<JobOrder> query = em.createNamedQuery("JobOrder.findbyCompany", JobOrder.class);
	query.setParameter("companyid", companyid);
	return query.getResultList();
}


		

}
