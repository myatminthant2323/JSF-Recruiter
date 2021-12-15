INSERT INTO Township  (`name`) VALUES ('Yangon');
INSERT INTO Township  (`name`) VALUES ('Mandalay');
INSERT INTO Township  (`name`) VALUES ('Pyin Oo Lwin');

INSERT INTO InterviewType  (`description`) VALUES ('Personal');
INSERT INTO InterviewType  (`description`) VALUES ('Call');
INSERT INTO InterviewType  (`description`) VALUES ('Online');

INSERT INTO AvailabilityType  (`description`) VALUES ('immediately');
INSERT INTO AvailabilityType  (`description`) VALUES ('1 week');
INSERT INTO AvailabilityType  (`description`) VALUES ('2 week');
INSERT INTO AvailabilityType  (`description`) VALUES ('1 or 2 month');

INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('100', 'Added');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('200', 'Interview Request');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('300', 'Interview Accept');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('400', 'Interview Reject');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('500', 'First Interview');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('600', 'Second Interview');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('700', 'Third Interview');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('800', 'Offered');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('900', 'Placed');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('1000', 'Candidate Declined');



INSERT INTO Recruiter (`email`, `name`, `password`, `phone`,`level`) VALUES ('myat@gmail.com', 'Myat', '123', '4545454545','Admin');
INSERT INTO Recruiter (`email`, `name`, `password`, `phone`,`level`) VALUES ('min@gmail.com', 'Min', '123', '635454555','Staff');

INSERT INTO Company (`address`, `email`, `entry_date`, `ishot`, `name`, `ownerName`, `phone1`, `phone2`, `remark`, `website`, `entryBy`, `township_id`) VALUES ('3626  Villa Drive', 'test@gmail.com', '2020-11-23', 'yes', 'TEST', 'Paing', '567564545', '5446687', 'Hey', 'test@gmail.com', '1', '1');
INSERT INTO Company (`address`, `email`, `entry_date`, `ishot`, `name`, `ownerName`, `phone1`, `phone2`, `remark`, `website`, `entryBy`, `township_id`) VALUES ('Villa Drive', 'abc@gmail.com', '2020-11-24', 'yes', 'ABC', 'Thant', '567564545', '5446687', 'Hey', 'abc@gmail.com', '1', '2');

INSERT INTO Candidate (`email`, `entry_date`, `expectedSalary`, `is_active`, `key_skills`, `name`, `phone`, `availiability_id`, `entryBy`, `township_id`,`cv_form`) VALUES ('aung@gmail.com', '2020-11-24', '500000', 'yes', 'JAVA, PHP', 'Aung Aung', '6545646548', '4', '1', '2','JSF2-Ajax.pdf');
INSERT INTO Candidate (`email`, `entry_date`, `expectedSalary`, `is_active`, `key_skills`,  `name`, `phone`, `availiability_id`, `entryBy`, `township_id`,`cv_form`) VALUES ('mg@gmail.com', '2020-11-24', '800000', 'yes', 'Java', 'Mg Mg', '786768768', '2', '1', '1','JAVA.pdf');
INSERT INTO Candidate (`email`, `entry_date`, `expectedSalary`, `is_active`, `key_skills`, `name`, `phone`, `availiability_id`, `entryBy`, `township_id`,`cv_form`) VALUES ('mon@gmail.com', '2020-11-24', '800000', 'yes', 'PHP', 'Mon Mon', '769576878687', '3', '1', '3','PHP.pdf');

INSERT INTO Joborder (`basic_salary`, `entry_date`, `is_active`, `job_description`, `job_position`, `total_employee`, `company`, `entryBy`, `job_location`) VALUES ('800000', '2020-11-23', 'yes', 'Description', 'Backend Developer', '2', '1', '1', '1');
INSERT INTO Joborder (`basic_salary`, `entry_date`, `is_active`, `job_description`, `job_position`, `total_employee`, `company`, `entryBy`, `job_location`) VALUES ('500000', '2020-11-24', 'yes', 'Description Of Senior Web Developer', 'Senior Web Developer', '2', '2', '1', '2');
INSERT INTO Joborder (`basic_salary`, `entry_date`, `is_active`, `job_description`, `job_position`, `total_employee`, `company`, `entryBy`, `job_location`) VALUES ('800000', '2020-11-23', 'yes', 'At TikTok, we are grateful and inspired by the service and sacrifice of our men and women in uniform. We see it as vital to honor veterans not just within our own community at TikTok, but around the world. In observing.', 'Tik Tok Professional', '3', '1', '1', '1');



INSERT INTO Candidate_job_order (`entry_date`, `candidate_id`, `candidatejoborderstatus_id`, `entryBy`, `joborder_id`) VALUES ('2020-11-24', '1', '100', '1', '1');

INSERT INTO Candidate_job_order (`entry_date`, `candidate_id`, `candidatejoborderstatus_id`, `entryBy`, `joborder_id`) VALUES ('2020-11-24', '2', '100', '2', '1');
INSERT INTO Candidate_job_order (`entry_date`, `candidate_id`, `candidatejoborderstatus_id`, `entryBy`, `joborder_id`) VALUES ('2020-11-25', '2', '100', '1', '3');
INSERT INTO Candidate_job_order (`entry_date`, `candidate_id`, `candidatejoborderstatus_id`, `entryBy`, `joborder_id`) VALUES ('2020-11-24', '1', '100', '2', '2');
INSERT INTO Candidate_job_order (`entry_date`, `candidate_id`, `candidatejoborderstatus_id`, `entryBy`, `joborder_id`) VALUES ('2020-11-26', '3', '100', '1', '1');

DROP TRIGGER IF EXISTS change_pipeline_status_history; 
CREATE TRIGGER change_pipeline_status_history AFTER UPDATE ON candidate_job_order FOR EACH ROW BEGIN IF NEW.candidatejoborderstatus_id != OLD.candidatejoborderstatus_id THEN INSERT INTO pipelinehistory(piplelineId,fromstatus,tostatus,actionUserId,actionDate) VALUES(NEW.id,OLD.candidatejoborderstatus_id,NEW.candidatejoborderstatus_id,NEW.modifyBy,NEW.modify_date); END IF; END