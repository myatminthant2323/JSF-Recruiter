package com.mmit.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;


/**
 * Entity implementation class for Entity: Pipelinehistory
 *
 */
@Entity

public class Pipelinehistory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "piplelineId", referencedColumnName = "id")
	private JobPipeline piplelineId ;
	@ManyToOne
	@JoinColumn(name = "actionUserId", referencedColumnName = "id")
	private Recruiter actionUserId;
	@ManyToOne
	@JoinColumn(name = "fromstatus", referencedColumnName = "id")
	private CandidateJoborderStatus fromstatus;
	@ManyToOne
	@JoinColumn(name = "tostatus", referencedColumnName = "id")
	private CandidateJoborderStatus tostatus;
	@CreationTimestamp
	private LocalDateTime actionDate;
	
	private static final long serialVersionUID = 1L;

	public Pipelinehistory() {
		super();
	}
   
}
