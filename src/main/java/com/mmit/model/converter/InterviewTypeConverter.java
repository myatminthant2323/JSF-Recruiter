package com.mmit.model.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import com.mmit.model.entity.InterviewType;
import com.mmit.model.service.InterviewTypeService;

@Named
public class InterviewTypeConverter implements  Converter<InterviewType>{
	
	@EJB
	private InterviewTypeService service;

	@Override
	public InterviewType getAsObject(FacesContext context, UIComponent component, String value) {
		if( value != null) 
			return service.findById(Integer.parseInt(value));
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, InterviewType value) {
		if(value != null)
			return String.valueOf(value.getId());
		return null;
	}

	

}
