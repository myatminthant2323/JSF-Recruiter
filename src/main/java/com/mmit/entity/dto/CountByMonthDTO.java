package com.mmit.entity.dto;


public class CountByMonthDTO {
	private int month;
	private long count_interview;
	
	public CountByMonthDTO(int month, long count_interview) {
		super();
		this.month = month;
		this.count_interview = count_interview;
	}

	

	public int getMonth() {
		return month;
	}



	public void setMonth(int month) {
		this.month = month;
	}



	public long getCount_interview() {
		return count_interview;
	}

	public void setCount_interview(long count_interview) {
		this.count_interview = count_interview;
	}

	
	
	
	
	
	
}
