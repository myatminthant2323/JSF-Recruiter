package com.mmit.model.service;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.entity.dto.CountByMonthDTO;
import com.mmit.model.entity.JobPipeline;

@Stateless
public class JobPipelineService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;

	public List<JobPipeline> findAll() {
		TypedQuery<JobPipeline> query = em.createNamedQuery("JobPipeline.findAll", JobPipeline.class);
		return query.getResultList();
	}

	public JobPipeline findById(int id) {
		return em.find(JobPipeline.class, id);
	}

	
	
	

	public List<JobPipeline> findByCandidate(int candidateId) {
		TypedQuery<JobPipeline> query = em.createNamedQuery("JobPipeline.findByCandidate", JobPipeline.class);
		query.setParameter("candidateId", candidateId);
		return query.getResultList();
	}

	public List<JobPipeline> findByJobOrder(int joborderId) {
		TypedQuery<JobPipeline> query = em.createNamedQuery("JobPipeline.findByJobOrder", JobPipeline.class);
		query.setParameter("joborderId", joborderId);
		return query.getResultList();
	}

	public void save(JobPipeline jobPipeline) {
		em.persist(jobPipeline);
		
	}

	public void delete(int jpid) {
		JobPipeline jobpipeline = em.find(JobPipeline.class, jpid);
		em.remove(jobpipeline);
	}

	public void update(JobPipeline jobpipeline) {
		em.merge(jobpipeline);
		
	}

	public void insertIntoInterViewRecord(JobPipeline edit_jobpipeline) {
		
		
	}

	public List<JobPipeline> findInActiveJobOrder() {
		TypedQuery<JobPipeline> query = em.createNamedQuery("JobPipeline.findInActiveJobOrder", JobPipeline.class);
		return query.getResultList();
	}

	public List<JobPipeline> jobPipelineRecentHire() {
		TypedQuery<JobPipeline> query = em.createNamedQuery("JobPipeline.jobPipelineRecentHire", JobPipeline.class);
		return query.setMaxResults(10).getResultList();
	}

	public List<JobPipeline> jobPipelineInterviewByYear(int year) {
		TypedQuery<JobPipeline> query = em.createNamedQuery("JobPipeline.jobPipelineInterviewByYear",JobPipeline.class);
		query.setParameter("year", year);
		return query.getResultList();
	}
	
	public List<JobPipeline> jobPipelineHireByYear(int year) {
		TypedQuery<JobPipeline> query = em.createNamedQuery("JobPipeline.jobPipelineHireByYear", JobPipeline.class);
		query.setParameter("year", year);
		return query.getResultList();
	}
	
	public List<JobPipeline> jobPipelineInterviewByMonth(int month, int year) {
		TypedQuery<JobPipeline> query = em.createNamedQuery("JobPipeline.jobPipelineInterviewByMonth", JobPipeline.class);
		query.setParameter("month", month);
		query.setParameter("year", year);
		return query.getResultList();
	}

	public List<JobPipeline> jobPipelineHireByMonth(int month, int year) {
		TypedQuery<JobPipeline> query = em.createNamedQuery("JobPipeline.jobPipelineHireByMonth", JobPipeline.class);
		query.setParameter("month", month);
		query.setParameter("year", year);
		return query.getResultList();
	}

	



	

}
