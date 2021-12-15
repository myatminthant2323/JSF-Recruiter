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

import com.mmit.model.entity.FeesRecord;
import com.mmit.model.entity.InterviewRecord;
import com.mmit.model.entity.JobPipeline;
import com.mmit.model.service.FeesRecordService;
import com.mmit.model.service.InterviewRecordService;


@Named
@ViewScoped
public class FeesRecordBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private FeesRecord feesRecord; 
	private List<FeesRecord> feesRecordList;
	
	@EJB
	private FeesRecordService service;
	
	@Inject
	private LoginBean loginbean;
	
	@Inject
	private JobPipelineBean jobpipelineBean;
	
	
	@PostConstruct
	private void initialize() {
		feesRecord = new FeesRecord();
		feesRecordList = service.findAll();	 
		
	}
	
	

	
	
	public void insertFeesRecord(JobPipeline edit_jobpipeline) {
		List <FeesRecord> eidtFeesRecord = service.findExistOrNot(edit_jobpipeline);
		if(eidtFeesRecord.isEmpty() ) {
			feesRecord.setCandidateJobOrder(edit_jobpipeline);
			feesRecord.setStatus(jobpipelineBean.getEdit_jobpipeline().getCandidatejoborderstatus());
			feesRecord.setEntry_date(LocalDateTime.now());
			feesRecord.setEntryBy(loginbean.getLoginUser());
			service.insertIntoFeesRecord(feesRecord);
		}else {
			FeesRecord existingFeesRecord = eidtFeesRecord.get(0);
			existingFeesRecord.setRealsalary(feesRecord.getRealsalary());
			existingFeesRecord.setAllowance(feesRecord.getAllowance());
			existingFeesRecord.setAgentfees(feesRecord.getAgentfees());
			existingFeesRecord.setStatus(jobpipelineBean.getEdit_jobpipeline().getCandidatejoborderstatus());
			existingFeesRecord.setModify_date(LocalDateTime.now());
			existingFeesRecord.setModifyBy(loginbean.getLoginUser());
			service.updateRecord(existingFeesRecord);
		}
		
	}

	public FeesRecord getFeesRecord() {
		return feesRecord;
	}

	public void setFeesRecord(FeesRecord feesRecord) {
		this.feesRecord = feesRecord;
		System.out.println(feesRecord.getAllowance());
	}

	public List<FeesRecord> getFeesRecordList() {
		return feesRecordList;
	}

	public void setFeesRecordList(List<FeesRecord> feesRecordList) {
		this.feesRecordList = feesRecordList;
	}
	

}
