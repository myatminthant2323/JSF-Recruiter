package com.mmit.model.bean;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mmit.model.entity.Candidate;
import com.mmit.model.entity.JobOrder;
import com.mmit.model.entity.JobPipeline;
import com.mmit.model.entity.Pipelinehistory;
import com.mmit.model.service.CandidateJobOrderStatusService;
import com.mmit.model.service.CandidateService;
import com.mmit.model.service.ChartService;
import com.mmit.model.service.JobOrderService;
import com.mmit.model.service.JobPipelineService;

import java.io.Serializable;

@Named
@ViewScoped
public class ChartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private List<Pipelinehistory> jobPipelineInterviewByYear;
	private List<Pipelinehistory> jobPipelineHireByYear;
	private List<Pipelinehistory> jobPipelineInterviewByMonth;
	private List<Pipelinehistory> jobPipelineHireByMonth;
	private List<Pipelinehistory> jobPipelineInterviewByWeek;
	private List<Pipelinehistory> jobPipelineHireByWeek;
	private JSONArray yearlyMorrisJson = new JSONArray();
	private JSONArray monthlyMorrisJson = new JSONArray();
	private JSONArray weeklyMorrisJson = new JSONArray();

	@EJB
	private ChartService service;

	@Inject
	private LoginBean loginbean;

	@Inject
	private InterviewRecordBean interviewRecordBean;

	@PostConstruct
	private void initialize() {
		
				
				/*
				 * jobPipelineInterviewByMonth = service.jobPipelineInterviewByMonth();
				 * jobPipelineHireByMonth = service.jobPipelineHireByMonth();
				 */
				try {
					int[] lastFourYear = new int[4];
					for(int i=0; i<4 ; i++) {
						lastFourYear[i] = (LocalDate.now().getYear())-(3-i);
					}
					for(int i=0; i<4 ; i++) {
						JSONObject yearlyJsonObj = new JSONObject();
					
					yearlyJsonObj.put("y", "" + lastFourYear[i]);
					jobPipelineInterviewByYear = service.jobPipelineInterviewByYear(lastFourYear[i]);
					jobPipelineHireByYear = service.jobPipelineHireByYear(lastFourYear[i]);
					yearlyJsonObj.put("interview", "" + jobPipelineInterviewByYear.size());
					yearlyJsonObj.put("hire", "" + jobPipelineHireByYear.size());
					yearlyMorrisJson.put(yearlyJsonObj);
					}
				} catch (JSONException e) {
					System.err.print(e.getMessage());
				}
				
				
				try {
					int[] lastFourMonth = new int[4];
					String[] lastFourMonthWithName = new String[4];
					int[] lastFourYear = new int[4];
					LocalDate now = LocalDate.now();
					for(int i=0; i<4 ; i++) {
						LocalDate previous = now.minusMonths(3-i);
						lastFourMonth[i] = previous.getMonth().getValue();
						lastFourYear[i] = (previous.getYear());
						lastFourMonthWithName[i] = previous.getMonth().getDisplayName(TextStyle.SHORT, Locale.US);
					}
					
					for(int i=0; i<4 ; i++) {
						JSONObject monthlyJsonObj = new JSONObject();
					
					monthlyJsonObj.put("m", "" + lastFourMonthWithName[i]);
					jobPipelineInterviewByMonth = service.jobPipelineInterviewByMonth(lastFourMonth[i], lastFourYear[i]);
					jobPipelineHireByMonth = service.jobPipelineHireByMonth(lastFourMonth[i], lastFourYear[i]);
					monthlyJsonObj.put("interview", "" + jobPipelineInterviewByMonth.size());
					monthlyJsonObj.put("hire", "" + jobPipelineHireByMonth.size());
					monthlyMorrisJson.put(monthlyJsonObj);
					}
				} catch (JSONException e) {
					System.err.print(e.getMessage());
				}
				
				
				try {
					
					for(int i=0; i<4 ; i++) {
						JSONObject weeklyJsonObj = new JSONObject();
						LocalDate from = LocalDate.now().plusDays(7-(LocalDate.now().getDayOfWeek().getValue())).minusDays(((3-(i-1))*7)-1);
						LocalDate to = LocalDate.now().plusDays(7-(LocalDate.now().getDayOfWeek().getValue())).minusDays((3-i)*7);
						String interval = from.getDayOfMonth()+"/"+from.getMonth().getValue()+"-"+to.getDayOfMonth()+"/"+to.getMonth().getValue();
						weeklyJsonObj.put("d", "" + interval);
						System.out.println(LocalDate.now().minusDays(7));
					jobPipelineInterviewByWeek = service.jobPipelineInterviewByWeek(from,to);
					jobPipelineHireByWeek = service.jobPipelineHireByWeek(from,to);
					weeklyJsonObj.put("interview", "" + jobPipelineInterviewByWeek.size());
					weeklyJsonObj.put("hire", "" + jobPipelineHireByWeek.size());
					weeklyMorrisJson.put(weeklyJsonObj);
					}
				} catch (JSONException e) {
					System.err.print(e.getMessage());
				}
			}
			
	
	
		

	public JSONArray getYearlyMorrisJson() {
		return yearlyMorrisJson;
	}

	public void setYearlyMorrisJson(JSONArray yearlyMorrisJson) {
		this.yearlyMorrisJson = yearlyMorrisJson;
	}

	public JSONArray getMonthlyMorrisJson() {
		return monthlyMorrisJson;
	}

	public void setMonthlyMorrisJson(JSONArray monthlyMorrisJson) {
		this.monthlyMorrisJson = monthlyMorrisJson;
	}

	public List<Pipelinehistory> getJobPipelineInterviewByYear() {
		return jobPipelineInterviewByYear;
	}

	public void setJobPipelineInterviewByYear(List<Pipelinehistory> jobPipelineInterviewByYear) {
		this.jobPipelineInterviewByYear = jobPipelineInterviewByYear;
	}

	public List<Pipelinehistory> getJobPipelineHireByYear() {
		return jobPipelineHireByYear;
	}

	public void setJobPipelineHireByYear(List<Pipelinehistory> jobPipelineHireByYear) {
		this.jobPipelineHireByYear = jobPipelineHireByYear;
	}

	public List<Pipelinehistory> getJobPipelineInterviewByMonth() {
		return jobPipelineInterviewByMonth;
	}

	public void setJobPipelineInterviewByMonth(List<Pipelinehistory> jobPipelineInterviewByMonth) {
		this.jobPipelineInterviewByMonth = jobPipelineInterviewByMonth;
	}


	public List<Pipelinehistory> getJobPipelineHireByMonth() {
		return jobPipelineHireByMonth;
	}

	public void setJobPipelineHireByMonth(List<Pipelinehistory> jobPipelineHireByMonth) {
		this.jobPipelineHireByMonth = jobPipelineHireByMonth;
	}

	public JSONArray getWeeklyMorrisJson() {
		return weeklyMorrisJson;
	}

	public void setWeeklyMorrisJson(JSONArray weeklyMorrisJson) {
		this.weeklyMorrisJson = weeklyMorrisJson;
	}
	

}
