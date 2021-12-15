package com.mmit.entity.dto;


public class CountByYearDTO {
	private int year;
	private long count_interview;
	
	public CountByYearDTO(int year, long count_interview) {
		super();
		this.year = year;
		this.count_interview = count_interview;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public long getCount_interview() {
		return count_interview;
	}

	public void setCount_interview(long count_interview) {
		this.count_interview = count_interview;
	}

	
	
	
	
	
	
}
