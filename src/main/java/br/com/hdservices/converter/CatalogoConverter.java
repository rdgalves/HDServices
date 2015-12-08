package br.com.hdservices.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.hdservices.model.Catalogo;
import br.com.hdservices.repository.Catalogos;

@ManagedBean
@FacesConverter(forClass = Catalogo.class)
public class CatalogoConverter implements Converter {

	@Inject
	private Catalogos catalogos;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		try {
			Integer idCatalogo = Integer.parseInt(value);
			Catalogo catalogo = catalogos.porId(idCatalogo);
			return catalogo;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		try {
			Catalogo catalogo = (Catalogo) value;
			return String.valueOf(catalogo.getIdCatalogo());
		} catch (Exception e) {
			return value.toString();
		}
	}

}
