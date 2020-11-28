package com.mmit.model.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import com.mmit.model.entity.Township;
import com.mmit.model.service.TownshipService;

@Named
public class TownshipConverter implements  Converter<Township>{
	
	@EJB
	private TownshipService service;

	@Override
	public Township getAsObject(FacesContext context, UIComponent component, String value) {
		if( value != null) 
			return service.findById(Integer.parseInt(value));
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Township value) {
		if(value != null)
			return String.valueOf(value.getId());
		return null;
	}

}
