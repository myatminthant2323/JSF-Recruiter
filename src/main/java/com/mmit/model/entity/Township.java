package com.mmit.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: Township
 *
 */
@Entity
@NamedQuery(name="Township.findAll",query="SELECT ts FROM Township ts")

public class Township implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Basic(optional = false)
	private String name;
	
	@OneToMany(mappedBy = "township", cascade = REMOVE)
	private List<Company> company;
	
	@OneToMany(mappedBy = "job_location")
	private List<JobOrder> joborder;
	
	@OneToMany(mappedBy = "township")
	private List<Candidate> candidate;
	private static final long serialVersionUID = 1L;

	public Township() {
		super();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Township other = (Township) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

   
}
