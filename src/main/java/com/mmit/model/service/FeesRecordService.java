package com.mmit.model.service;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.FeesRecord;
import com.mmit.model.entity.InterviewRecord;
import com.mmit.model.entity.JobPipeline;

@Stateless
public class FeesRecordService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;

	

	public FeesRecord findById(int id) {
		return em.find(FeesRecord.class, id);
	}



	public List<FeesRecord> findAll() {
		TypedQuery<FeesRecord> query = em.createNamedQuery("FeesRecord.findAll", FeesRecord.class);
		return query.getResultList();
	}



	public void insertIntoFeesRecord(FeesRecord feesRecord) {
		if (feesRecord.getId() == 0) {
			em.persist(feesRecord);
		}else {
			em.merge(feesRecord);
		}
		
	}



	public List<FeesRecord> findExistOrNot(JobPipeline edit_jobpipeline) {
		TypedQuery<FeesRecord> query = em.createNamedQuery("FeesRecord.findExistOrNot", FeesRecord.class);
		query.setParameter("eidtcandidateJobOrderId", edit_jobpipeline.getId());
		return query.getResultList();
	}



	public void updateRecord(FeesRecord existingFeesRecord) {
		em.merge(existingFeesRecord);
		
	}



	public FeesRecord findByPipeline(int jobpipelineid) {
		TypedQuery<FeesRecord> query = em.createNamedQuery("FeesRecord.findByJobPipeline", FeesRecord.class);
		query.setParameter("jobpipelineid", jobpipelineid);
		return query.getSingleResult();
	}

}
