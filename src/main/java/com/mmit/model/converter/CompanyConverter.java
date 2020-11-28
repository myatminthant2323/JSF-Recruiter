package com.mmit.model.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import com.mmit.model.entity.Company;
import com.mmit.model.service.CompanyService;

@Named
public class CompanyConverter implements Converter<Company>{
	
	@EJB
	private CompanyService service;

	@Override
	public Company getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null) 
			return service.findById(Integer.parseInt(value));
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Company value) {
		if(value != null)
			return String.valueOf(value.getId());
		return null;
	}

}
