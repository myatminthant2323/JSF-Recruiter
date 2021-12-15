package com.mmit.model.service;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.mmit.model.entity.Pipelinehistory;

@Stateless
public class ChartService {
	
	@PersistenceContext(name = "jsf-ui-recruiter")
	private EntityManager em;

	
	

	public List<Pipelinehistory> jobPipelineInterviewByYear(int year) {
		TypedQuery<Pipelinehistory> query = em.createNamedQuery("Pipelinehistory.jobPipelineInterviewByYear",Pipelinehistory.class);
		query.setParameter("year", year);
		return query.getResultList();
	}
	
	public List<Pipelinehistory> jobPipelineHireByYear(int year) {
		TypedQuery<Pipelinehistory> query = em.createNamedQuery("Pipelinehistory.jobPipelineHireByYear", Pipelinehistory.class);
		query.setParameter("year", year);
		return query.getResultList();
	}
	
	public List<Pipelinehistory> jobPipelineInterviewByMonth(int month, int year) {
		TypedQuery<Pipelinehistory> query = em.createNamedQuery("Pipelinehistory.jobPipelineInterviewByMonth", Pipelinehistory.class);
		query.setParameter("month", month);
		query.setParameter("year", year);
		return query.getResultList();
	}

	public List<Pipelinehistory> jobPipelineHireByMonth(int month, int year) {
		TypedQuery<Pipelinehistory> query = em.createNamedQuery("Pipelinehistory.jobPipelineHireByMonth", Pipelinehistory.class);
		query.setParameter("month", month);
		query.setParameter("year", year);
		return query.getResultList();
	}

	public List<Pipelinehistory> jobPipelineInterviewByWeek(LocalDate from, LocalDate to) {
		TypedQuery<Pipelinehistory> query = em.createNamedQuery("Pipelinehistory.jobPipelineInterviewByWeek", Pipelinehistory.class);
		query.setParameter("from", java.sql.Date.valueOf(from));
		query.setParameter("to", java.sql.Date.valueOf(to));
		return query.getResultList();
	}

	public List<Pipelinehistory> jobPipelineHireByWeek(LocalDate from, LocalDate to) {
		TypedQuery<Pipelinehistory> query = em.createNamedQuery("Pipelinehistory.jobPipelineHireByWeek", Pipelinehistory.class);
		query.setParameter("from", java.sql.Date.valueOf(from));
		query.setParameter("to", java.sql.Date.valueOf(to));
		return query.getResultList();
	}
	



	

}
