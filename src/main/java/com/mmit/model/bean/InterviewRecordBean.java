package com.mmit.model.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mmit.model.entity.InterviewRecord;
import com.mmit.model.entity.JobPipeline;
import com.mmit.model.service.InterviewRecordService;


@Named
@ViewScoped
public class InterviewRecordBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private InterviewRecord interviewRecord; 
	private List<InterviewRecord> interviewRecordList;
	
	@EJB
	private InterviewRecordService service;
	
	@Inject
	private LoginBean loginbean;
	
	@Inject
	private JobPipelineBean jobpipelineBean;
	
	private JSONArray interviewRecordJson = new JSONArray();
	
	
	@PostConstruct
	private void initialize() {
		/* interviewRecord = new InterviewRecord(); */
		/*
		 * if(jobpipelineBean.getEdit_jobpipeline() != null) interviewRecord =
		 * service.findByPipeline(jobpipelineBean.getEdit_jobpipeline().getId()); else
		 */
		interviewRecord = new InterviewRecord();
		interviewRecordList = service.findAll();
		interviewRecordList.forEach((each) -> {
			JSONObject recordJsonObj = new JSONObject();
	        try {
	        	
				recordJsonObj.put("id", ""+each.getId()); 
				recordJsonObj.put("company", ""+each.getCandidateJobOrder().getJoborder().getCompany().getName()); 
				recordJsonObj.put("candidate",""+each.getCandidateJobOrder().getCandidate().getName());
				recordJsonObj.put("status", ""+each.getStatus().getShort_description());
	        	recordJsonObj.put("title", ""+each.getCandidateJobOrder().getCandidate().getName());
	        	recordJsonObj.put("start", ""+each.getInterview_date()+"T"+each.getInterview_time()+"");
	        	recordJsonObj.put("type", ""+each.getInterviewType().getDescription());
	        	recordJsonObj.put("joborderId", ""+String.valueOf(each.getCandidateJobOrder().getJoborder().getId()));
	        	switch (each.getStatus().getId()) {
	        	  case 500:
	        		  recordJsonObj.put("color", ""+"#9062cc");
	        	      break;
	        	  case 600: 
	        		  recordJsonObj.put("color", ""+"#73d1aa");
	        	      break;
	        	  case 700: 
	        	      break;
	        	}
	        	
	        	recordJsonObj.put("interval", ""+each.getInterview_interval());
	        	
	        } catch (JSONException e) {
	        	System.err.print(e.getMessage());
	        }
	        interviewRecordJson.put(recordJsonObj);
            System.out.println(interviewRecordJson);
        });
		
		 
		
	}
	
	

	
	
	public void insertInterviewRecord(JobPipeline edit_jobpipeline) {
		List <InterviewRecord> eidtInterviewRecord = service.findExistOrNot(edit_jobpipeline);
		if(eidtInterviewRecord.isEmpty() ) {
			interviewRecord.setCandidateJobOrder(edit_jobpipeline);
			interviewRecord.setStatus(jobpipelineBean.getEdit_jobpipeline().getCandidatejoborderstatus());
			interviewRecord.setEntry_date(LocalDateTime.now());
			interviewRecord.setEntryBy(loginbean.getLoginUser());
			service.insertIntoInterViewRecord(interviewRecord);
		}else {
			InterviewRecord existingInterviewRecord = eidtInterviewRecord.get(0);
			existingInterviewRecord.setInterview_date(interviewRecord.getInterview_date());
			existingInterviewRecord.setInterview_time(interviewRecord.getInterview_time());
			existingInterviewRecord.setInterview_interval(interviewRecord.getInterview_interval());
			existingInterviewRecord.setInterviewType(interviewRecord.getInterviewType());
			existingInterviewRecord.setStatus(jobpipelineBean.getEdit_jobpipeline().getCandidatejoborderstatus());
			existingInterviewRecord.setModify_date(LocalDateTime.now());
			existingInterviewRecord.setModifyBy(loginbean.getLoginUser());
			service.updateRecord(existingInterviewRecord);
		}
		
	}

	public InterviewRecord getInterviewRecord() {
		return interviewRecord;
	}

	public void setInterviewRecord(InterviewRecord interviewRecord) {
		this.interviewRecord = interviewRecord;
	}

	public List<InterviewRecord> getInterviewRecordList() {
		return interviewRecordList;
	}

	public void setInterviewRecordList(List<InterviewRecord> interviewRecordList) {
		this.interviewRecordList = interviewRecordList;
	}





	public JSONArray getInterviewRecordJson() {
		return interviewRecordJson;
	}





	public void setInterviewRecordJson(JSONArray interviewRecordJson) {
		this.interviewRecordJson = interviewRecordJson;
	}

	

	
	
	

}
