package br.com.hdservices.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.hdservices.model.TipoCatalogo;
import br.com.hdservices.service.GerenciarTipoCatalogoService;

@ManagedBean
@FacesConverter(forClass = TipoCatalogo.class)
public class TipoCatalogoConverter implements Converter {

	@Inject
	private GerenciarTipoCatalogoService tipoCatalogoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			Integer id = Integer.parseInt(value);
			TipoCatalogo tipoCatalogo = tipoCatalogoService.buscar(id);
			return tipoCatalogo;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		try {
			TipoCatalogo tipoCatalogo = (TipoCatalogo) value;
			return String.valueOf(tipoCatalogo.getIdTipoCatalogo());
		} catch (Exception e) {
			return value.toString();
		}
	}

}
