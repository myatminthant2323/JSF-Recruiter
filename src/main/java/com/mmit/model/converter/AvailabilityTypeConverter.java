package com.mmit.model.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import com.mmit.model.entity.AvailabilityType;
import com.mmit.model.service.AvailabilityTypeService;
@Named
public class AvailabilityTypeConverter  implements  Converter<AvailabilityType>{
	@EJB
	private AvailabilityTypeService service;

	@Override
	public AvailabilityType getAsObject(FacesContext context, UIComponent component, String value) {
		if( value != null) 
			return service.findById(Integer.parseInt(value));
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, AvailabilityType value) {
		if(value != null)
			return String.valueOf(value.getId());
		return null;
	}
}
