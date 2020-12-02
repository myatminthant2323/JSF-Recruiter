package com.mmit.model.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import com.mmit.model.entity.CandidateJoborderStatus;
import com.mmit.model.service.CandidateJobOrderStatusService;

@Named
public class CandidateJobOrderStatusConverter implements  Converter<CandidateJoborderStatus>{
	
	@EJB
	private CandidateJobOrderStatusService service;

	@Override
	public CandidateJoborderStatus getAsObject(FacesContext context, UIComponent component, String value) {
		if( value != null) 
			return service.findById(Integer.parseInt(value));
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, CandidateJoborderStatus value) {
		if(value != null)
			return String.valueOf(value.getId());
		return null;
	}

	

}
