package com.mmit.model.service;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.Candidate;

@Stateless
public class CandidateService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;

	public List<Candidate> findAll() {
		TypedQuery<Candidate> query = em.createNamedQuery("Candidate.findAll", Candidate.class);
		return query.getResultList();
	}

	public Candidate findById(int id) {
		return em.find(Candidate.class, id);
	}

	public void save(Candidate candidate) {
		
		
		if (candidate.getId() == 0) {
			em.persist(candidate);
		}else {
			em.merge(candidate);
		}
	}
	
	public void delete(int cid) {
		
		Candidate can = em.find(Candidate.class, cid);
		em.remove(can);
	}

	public List<Candidate> getUndeployedCandidates(int joborderid) {
		TypedQuery<Candidate> query = em.createNamedQuery("Candidate.getUndeployedCandidates", Candidate.class);
		query.setParameter("joborderid", joborderid);
		return query.getResultList();
	}

	

}
