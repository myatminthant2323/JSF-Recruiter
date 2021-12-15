package com.mmit.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.REMOVE;


@Entity
@NamedQuery(name="InterviewType.findAll",query="SELECT it FROM InterviewType it")
public class InterviewType implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Basic(optional = false)
	private String description;
	
	@OneToMany(mappedBy = "interviewType", cascade = REMOVE)
	private List <InterviewRecord> interviewRecord;
	
	
	private static final long serialVersionUID = 1L;

	public InterviewType() {
		super();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterviewType other = (InterviewType) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	


	
	
   
}
