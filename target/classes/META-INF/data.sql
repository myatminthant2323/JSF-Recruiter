INSERT INTO Township  (`name`) VALUES ('Yangon');
INSERT INTO Township  (`name`) VALUES ('Mandalay');
INSERT INTO Township  (`name`) VALUES ('Pyin Oo Lwin');

INSERT INTO AvailabilityType  (`description`) VALUES ('immediately');
INSERT INTO AvailabilityType  (`description`) VALUES ('1 week');
INSERT INTO AvailabilityType  (`description`) VALUES ('2 week');
INSERT INTO AvailabilityType  (`description`) VALUES ('1 or 2 month');

INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('100', 'Added');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('200', 'Contacted');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('300', 'Qualifying');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('400', 'Submitted');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('500', 'First Interview');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('600', 'Offered');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('700', 'Client Declined');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('800', 'Placed');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('900', 'Second Interview');
INSERT INTO CandidateJoborderStatus  (`id`,`short_description`) VALUES ('1000', 'Third Interview');



INSERT INTO Recruiter (`email`, `name`, `password`, `phone`) VALUES ('myat@gmail.com', 'Myat', '123', '4545454545');
INSERT INTO Recruiter (`email`, `name`, `password`, `phone`) VALUES ('min@gmail.com', 'Min', '123', '635454555');

INSERT INTO Company (`address`, `email`, `entry_date`, `ishot`, `name`, `ownerName`, `phone1`, `phone2`, `remark`, `website`, `entryBy`, `township_id`) VALUES ('3626  Villa Drive', 'test@gmail.com', '2020-11-23', 'yes', 'TEST', 'Paing', '567564545', '5446687', 'Hey', 'test@gmail.com', '1', '1');
INSERT INTO Company (`address`, `email`, `entry_date`, `ishot`, `name`, `ownerName`, `phone1`, `phone2`, `remark`, `website`, `entryBy`, `township_id`) VALUES ('Villa Drive', 'abc@gmail.com', '2020-11-24', 'yes', 'ABC', 'Thant', '567564545', '5446687', 'Hey', 'abc@gmail.com', '1', '2');

INSERT INTO Candidate (`email`, `entry_date`, `expectedSalary`, `is_active`, `key_skills`, `name`, `phone`, `availiability_id`, `entryBy`, `township_id`,`cv_form`) VALUES ('aung@gmail.com', '2020-11-24', '500000', 'yes', 'JAVA, PHP', 'Aung Aung', '6545646548', '4', '1', '2','JSF2-Ajax.pdf');
INSERT INTO Candidate (`email`, `entry_date`, `expectedSalary`, `is_active`, `key_skills`,  `name`, `phone`, `availiability_id`, `entryBy`, `township_id`,`cv_form`) VALUES ('mg@gmail.com', '2020-11-24', '800000', 'yes', 'Tik Toking', 'Mg Mg', '786768768', '2', '1', '1','how to use tik tok professionally.pdf');
INSERT INTO Candidate (`email`, `entry_date`, `expectedSalary`, `is_active`, `key_skills`, `name`, `phone`, `availiability_id`, `entryBy`, `township_id`,`cv_form`) VALUES ('mon@gmail.com', '2020-11-24', '800000', 'yes', 'PHP', 'Mon Mon', '769576878687', '3', '1', '3','PHP.pdf');

INSERT INTO Joborder (`basic_salary`, `entry_date`, `is_active`, `job_description`, `job_position`, `total_employee`, `company`, `entryBy`, `job_location`) VALUES ('800000', '2020-11-23', 'yes', 'Description', 'Backend Developer', '2', '1', '1', '1');
INSERT INTO Joborder (`basic_salary`, `entry_date`, `is_active`, `job_description`, `job_position`, `total_employee`, `company`, `entryBy`, `job_location`) VALUES ('500000', '2020-11-24', 'yes', 'Description Of Senior Web Developer', 'Senior Web Developer', '2', '2', '1', '2');
INSERT INTO Joborder (`basic_salary`, `entry_date`, `is_active`, `job_description`, `job_position`, `total_employee`, `company`, `entryBy`, `job_location`) VALUES ('800000', '2020-11-23', 'yes', 'At TikTok, we are grateful and inspired by the service and sacrifice of our men and women in uniform. We see it as vital to honor veterans not just within our own community at TikTok, but around the world. In observing.', 'Tik Tok Professional', '3', '1', '1', '1');



INSERT INTO Candidate_job_order (`entry_date`, `candidate_id`, `candidatejoborderstatus_id`, `entryBy`, `joborder_id`) VALUES ('2020-11-24', '1', '100', '1', '1');

INSERT INTO Candidate_job_order (`entry_date`, `candidate_id`, `candidatejoborderstatus_id`, `entryBy`, `joborder_id`) VALUES ('2020-11-24', '2', '100', '2', '1');
INSERT INTO Candidate_job_order (`entry_date`, `candidate_id`, `candidatejoborderstatus_id`, `entryBy`, `joborder_id`) VALUES ('2020-11-25', '2', '100', '1', '3');
INSERT INTO Candidate_job_order (`entry_date`, `candidate_id`, `candidatejoborderstatus_id`, `entryBy`, `joborder_id`) VALUES ('2020-11-24', '1', '100', '2', '2');
INSERT INTO Candidate_job_order (`entry_date`, `candidate_id`, `candidatejoborderstatus_id`, `entryBy`, `joborder_id`) VALUES ('2020-11-26', '3', '100', '1', '1');