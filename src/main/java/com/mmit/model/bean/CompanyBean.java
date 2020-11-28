package com.mmit.model.bean;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.mmit.model.entity.Company;
import com.mmit.model.entity.JobOrder;
import com.mmit.model.service.CompanyService;
import com.mmit.model.service.JobOrderService;
import java.io.Serializable;

@Named
@ViewScoped
public class CompanyBean implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private Company company;
	
	
	private List<Company> companyList;
	private List<JobOrder> company_joborderlist;
	
	@EJB
	private CompanyService service;
	
	@EJB
	private JobOrderService joborderservice;
	
	@Inject
	private LoginBean loginbean;
	
	@PostConstruct
	private void initialize() {
		String comid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("companyid");
		if(comid != null) {
			company_joborderlist = joborderservice.findbyCompany(Integer.parseInt(comid));
			company = service.findById(Integer.parseInt(comid));
			if(company.getIshot().equals("yes")) {
				company.setIshot("true");
			}else {
				company.setIshot("false");
			}
			
		}else {
			company = new Company();
		}
		
		companyList = service.findAll();
		
	}
	
	public String removeCompany(int cid) {
		service.delete(cid);
		return "/views/companys?faces-redirect=true";
		
	}
	
	public String saveCompany() {
		
		if(company.getIshot().equals("true")) {
			company.setIshot("yes");
		}else {
			company.setIshot("no");
		}
		
		if (company.getId() == 0) {
			company.setEntry_date(LocalDate.now());
			company.setEntryBy(loginbean.getLoginUser());
		}else {
//			Company companyobj = service.findById(company.getId());
//			company.setEntryBy(companyobj.getEntryBy());
//			company.setEntry_date(companyobj.getEntry_date());
//			System.out.println("Entry Date" + companyobj.getEntry_date());
			company.setModifyDate(LocalDate.now());
			company.setModifyBy(loginbean.getLoginUser());
		}
		service.save(company);
		return "/views/companys?faces-redirect=true";
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public List<JobOrder> getCompany_joborderlist() {
		return company_joborderlist;
	}

	public void setCompany_joborderlist(List<JobOrder> company_joborderlist) {
		this.company_joborderlist = company_joborderlist;
	}

	
}
