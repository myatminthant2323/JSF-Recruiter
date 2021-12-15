package com.mmit.model.service;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.hql.internal.ast.QuerySyntaxException;

import com.mmit.model.entity.Candidate;
import com.mmit.model.entity.JobOrder;

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

	public void save(Candidate candidate) throws Exception {
		
		
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

	public List<JobOrder> getUndeployedJobOrders(int id) {
		TypedQuery<JobOrder> query = em.createNamedQuery("JobOrder.getUndeployedJobOrders", JobOrder.class);
		query.setParameter("candidateid", id);
		return query.getResultList();
	}

	public List<Candidate> advancedSearchByKeySkills(ArrayList<String> keywords, ArrayList<String> operators) throws Exception {
		
		String qry= "SELECT c FROM Candidate c Where";
		
			for(int i=0; i<keywords.size();i++)
			{
				if(i == keywords.size()-1) 
					qry+= " c.key_skills LIKE '%" + keywords.get(i) + "%'";
				else 
					qry+= " c.key_skills LIKE '%" + keywords.get(i) + "%' "+operators.get(i);
			
			
		}

		TypedQuery<Candidate> query = em.createQuery(qry, Candidate.class);
		return query.getResultList();
	}

	public List<Candidate> advancedSearchByCVForm(ArrayList<Integer> foundId) {
		String qry= "SELECT c FROM Candidate c Where";
		
		for(int i=0; i<foundId.size();i++)
		{
			if(i == foundId.size()-1) 
				qry+= " c.id =" + foundId.get(i);
			else 
				qry+= " c.id  = " + foundId.get(i)+" OR";
		
		
	}

	TypedQuery<Candidate> query = em.createQuery(qry, Candidate.class);
	return query.getResultList();
	}

	

}
