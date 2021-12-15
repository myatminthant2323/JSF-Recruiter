package com.mmit.model.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.mmit.model.entity.Candidate;
import com.mmit.model.entity.JobOrder;
import com.mmit.model.entity.JobPipeline;
import com.mmit.model.service.CandidateJobOrderStatusService;
import com.mmit.model.service.CandidateService;
import com.mmit.model.service.JobOrderService;
import com.mmit.model.service.JobPipelineService;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

@Named
@ViewScoped
public class CandidateBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String search_type;
	private String keyword;
	private Part cv_form;

	private Candidate candidate;
	private List<Candidate> candidateList;
	private Map<Integer, Boolean> checkedJobOrders = new HashMap<Integer, Boolean>();
	private List<JobOrder> candidate_jobPipelineList;

	@EJB
	private JobPipelineService jobpipelineService;

	@EJB
	private CandidateService service;

	@EJB
	private CandidateJobOrderStatusService candidateJobOrderStatusService;

	@EJB
	private JobOrderService jobOrderService;

	@Inject
	private LoginBean loginbean;

	@PostConstruct
	private void initialize() {
		String canid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("candidateid");
		if (canid != null) {
			candidate = service.findById(Integer.parseInt(canid));
			if (candidate.getIs_active().equals("yes")) {
				candidate.setIs_active("true");
			} else {
				candidate.setIs_active("false");
			}

			candidate_jobPipelineList = service.getUndeployedJobOrders(Integer.parseInt(canid));
			for (JobOrder each_joborder : candidate_jobPipelineList) {
				checkedJobOrders.put(each_joborder.getId(), false);

			}

		} else {
			candidate = new Candidate();
		}

		candidateList = service.findAll();

	}

	public String searchByKeyword() {
		if ("Keyskill".equals(search_type)) {
			if (!("*").equals(keyword)) {
				try {
					String[] mix;
					ArrayList<String> keywords = new ArrayList<String>();
					ArrayList<String> operators = new ArrayList<String>();
					mix = keyword.split(" ");

					for (int i = 0; i < mix.length; i++) {
						if (i % 2 == 0)
							keywords.add(mix[i]);
						else
							operators.add(mix[i]);
					}

					candidateList = service.advancedSearchByKeySkills(keywords, operators);
				} catch (Exception e) {
					FacesMessage message = new FacesMessage("Invalid Format,Try again!");
					FacesContext.getCurrentInstance().addMessage("search_form", message);
					candidateList = null;
					return null;
				}
			} else {
				candidateList = service.findAll();
			}
		} else {
			candidateList = service.findAll();
			Map<Integer, String> cv_forms = new HashMap<>();

			for (Candidate each_candidate : candidateList) {
				cv_forms.put(each_candidate.getId(), each_candidate.getCv_form());
			}

			ArrayList<Integer> foundId = new ArrayList<Integer>();
			ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			cv_forms.forEach((id, cv) -> {
				String file_extension = cv.substring(cv.lastIndexOf(".") + 1);
				System.out.println("Substring after separator = " + cv.substring(cv.lastIndexOf(".") + 1));
				if ("txt".equals(file_extension)) {

					try {
						int LineCount = 0;
						String line = "";
						System.out
								.println(context.getRealPath("") + File.separator + "CV_Uploads" + File.separator + cv);
						BufferedReader bReader = new BufferedReader(new FileReader(
								context.getRealPath("") + File.separator + "CV_Uploads" + File.separator + cv));
						while ((line = bReader.readLine()) != null) {
							LineCount++;
							int indexFound = line.toLowerCase().indexOf(keyword.toLowerCase());
							if (indexFound > -1) {
								System.out.println("Found at position " + indexFound + " on line " + LineCount);
								foundId.add(id);
								break;
							}
						}

						// Close the reader.
						bReader.close();

					} catch (IOException e) {
						// We encountered an error with the file, print it to the user.
						System.out.println("Error: " + e.toString());
					}
				} else if ("pdf".equals(file_extension)) {
					String contents = "";
					PDDocument doc = null;
					try {
						File file = new File(
								context.getRealPath("") + File.separator + "CV_Uploads" + File.separator + cv);
						doc = PDDocument.load(file);
						PDFTextStripper stripper = new PDFTextStripper();

						stripper.setLineSeparator("\n");
						stripper.setStartPage(1);
						contents = stripper.getText(doc);
						if (contents.toLowerCase().contains(keyword.toLowerCase()))
							foundId.add(id);
						System.out.println(contents);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if ("docx".equals(file_extension)  || "doc".equals(file_extension)) {
					/* Create a FileInputStream object to read the input MS Word Document */
					BufferedInputStream input_document = null;
					InputStream document = null;
					try {
						input_document = new BufferedInputStream(new FileInputStream(
								context.getRealPath("") + File.separator + "CV_Uploads" + File.separator + cv));
						document = new FileInputStream(new File(
								context.getRealPath("") + File.separator + "CV_Uploads" + File.separator + cv));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						if (FileMagic.valueOf(input_document) == FileMagic.OLE2) {
							WordExtractor my_word;
							try {
								my_word = new WordExtractor(document);
								input_document.close();
								document.close();
								String text = my_word.getText();
								   input_document.close();
						           document.close();
								   System.out.println(text);
								   my_word.close();
								   if(text.toLowerCase().contains(keyword.toLowerCase()))
				        		    	  foundId.add(id);
								/*
								 * Scanner document_scanner = new Scanner(my_word.getText()); Define Search
								 * Pattern - we find for the word "search" Pattern words =
								 * Pattern.compile("("+keyword+")"); String key; int count=0; while
								 * (document_scanner.hasNextLine()) { key = document_scanner.findInLine(words);
								 * if (key != null) { document_scanner.next(); count ++; key =
								 * document_scanner.findInLine(words); break; } document_scanner.nextLine(); }
								 * document_scanner.close();
								 */
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else if (FileMagic.valueOf(input_document) == FileMagic.OOXML) {
							XWPFDocument doc = new XWPFDocument(document);
							XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
							String text = extractor.getText();
							input_document.close();
							document.close();
							System.out.println(text);
							extractor.close();
							if (text.toLowerCase().contains(keyword.toLowerCase()))
								foundId.add(id);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if ("xlsx".equals(file_extension) || "xls".equals(file_extension)) {

				    try{

				        FileInputStream is=new FileInputStream(context.getRealPath("") + File.separator + "CV_Uploads" + File.separator + cv);
				        Workbook wb = WorkbookFactory.create(new File(context.getRealPath("") + File.separator + "CV_Uploads" + File.separator + cv));
				        Sheet sheet = wb.getSheetAt(0);
						/*
						 * HSSFWorkbook wb = new HSSFWorkbook(is); HSSFSheet sheet = wb.getSheetAt(0);
						 */


				                for(Row row: sheet){
				                    for(Cell cell : row){
				                    	CellType each = cell.getCellType();
				                    	String each_cell = "";
				                    	switch(each) {
				                    	    case NUMERIC:
				                    	    	each_cell = String.valueOf(cell.getNumericCellValue());
				                    	    	System.out.println(each_cell);
				                    	        break;
				                    	    case STRING:
				                    	    	each_cell = cell.getStringCellValue();
				                    	    	System.out.println(each_cell);
				                    	        break;
				                    	}
				                        if (each_cell.toLowerCase().contains(keyword.toLowerCase())){

				                                        foundId.add(id);
				                                        break;
				                                
				                        }

				                    }
				                }

				    }
				    catch(Exception e){
				    	e.printStackTrace();
				    }
				
					
				}

			});

			if (foundId.size() != 0)
				candidateList = service.advancedSearchByCVForm(foundId);
			else {
				ArrayList<Candidate> empty = new ArrayList<Candidate>();
				candidateList = empty;
			}

			/* candidateList = null; */
		}
		return null;
	}

	public String saveJobPipline() {
		for (Entry<Integer, Boolean> each : checkedJobOrders.entrySet()) {
			if (each.getValue() == true) {
				JobOrder joborder = jobOrderService.findById(each.getKey());
				JobPipeline jobPipeline = new JobPipeline();
				jobPipeline.setJoborder(joborder);
				jobPipeline.setCandidate(candidate);
				jobPipeline.setCandidatejoborderstatus(candidateJobOrderStatusService.findById(100));
				jobPipeline.setEntry_date(LocalDateTime.now());
				jobPipeline.setEntryBy(loginbean.getLoginUser());
				jobpipelineService.save(jobPipeline);
			}

		}
		return "/views/detail_candidate?faces-redirect=true&candidateid=" + candidate.getId();
		/*
		 * return "/views/detail_joborder?faces-redirect=true&joborderid=" +
		 * joborder.getId();
		 */

	}

	public void uploadFile() {
		System.out.println("This is upload file operation");
		// Upload File
		String candidateid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("candidateid");
		int candidateId = candidate.getId();
		try {
			ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			if (cv_form != null) {
				if (candidateId != 0) {
					Candidate toDelete = service.findById(candidateId);
					String cv_name = toDelete.getCv_form();
					service.delete(candidateId);
					System.out.println(context.getRealPath("") + File.separator + "CV_Uploads\\" + cv_name);
					File cv = new File(context.getRealPath("") + File.separator + "CV_Uploads\\" + cv_name);

					cv.delete();
				}
				String uploadFileName = cv_form.getSubmittedFileName();
				String oldName = uploadFileName.substring(0, uploadFileName.lastIndexOf("."));
				String newName = oldName + System.currentTimeMillis();
				uploadFileName = uploadFileName.replace(oldName, newName);

				/* String.valueOf(LocalDateTime.now() */

				String dirPath = context.getRealPath("") + File.separator + "CV_Uploads";
				File rootDir = new File(dirPath);
				if (!rootDir.exists())
					rootDir.mkdirs();

				cv_form.write(rootDir + File.separator + uploadFileName);
				candidate.setCv_form(uploadFileName);
				System.out.println(candidate.getId());
			}

		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}

	}

	public String removeCandidate(int cid) {

		try {
			Candidate toDelete = service.findById(cid);
			String cv_name = toDelete.getCv_form();
			service.delete(cid);
			ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			System.out.println(context.getRealPath("") + File.separator + "CV_Uploads\\" + cv_name);
			File cv = new File(context.getRealPath("") + File.separator + "CV_Uploads\\" + cv_name);

			cv.delete();

		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "/views/candidates?faces-redirect=true";
		}
		return "/views/candidates?faces-redirect=true";

	}

	public String saveCandidate() {
		try {
			if (candidate.getIs_active().equals("true")) {
				candidate.setIs_active("yes");
			} else {
				candidate.setIs_active("no");
			}

			if (candidate.getId() == 0) {
				candidate.setEntry_date(LocalDate.now());
				candidate.setEntryBy(loginbean.getLoginUser());
			} else {
				candidate.setModify_date(LocalDate.now());
				candidate.setModifyBy(loginbean.getLoginUser());
			}

			service.save(candidate);

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Email already exists, Enter different email ! ");
			FacesContext.getCurrentInstance().addMessage("candidate_email_unique", msg);

			return "";

		}

		return "/views/candidates?faces-redirect=true";
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public List<Candidate> getCandidateList() {
		return candidateList;
	}

	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}

	public Part getCv_form() {
		return cv_form;
	}

	public void setCv_form(Part cv_form) {
		this.cv_form = cv_form;
	}

	public Map<Integer, Boolean> getCheckedJobOrders() {
		return checkedJobOrders;
	}

	public void setCheckedJobOrders(Map<Integer, Boolean> checkedJobOrders) {
		this.checkedJobOrders = checkedJobOrders;
	}

	public List<JobOrder> getCandidate_jobPipelineList() {
		return candidate_jobPipelineList;
	}

	public void setCandidate_jobPipelineList(List<JobOrder> candidate_jobPipelineList) {
		this.candidate_jobPipelineList = candidate_jobPipelineList;
	}

	public String getSearch_type() {
		return search_type;
	}

	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
