package br.com.hdservices.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.hdservices.model.Pessoa;
import br.com.hdservices.repository.Pessoas;

@ManagedBean
@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {

	@Inject
	private Pessoas pessoas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		try {
			String matricula = value;
			Pessoa pessoa = pessoas.porMatricula(matricula);
			return pessoa;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		try {
			Pessoa pessoa = (Pessoa) value;
			return String.valueOf(pessoa.getMatricula());
		} catch (Exception e) {
			return value.toString();
		}
	}

}
