package br.com.hdservices.service;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.hdservices.model.TipoCatalogo;
import br.com.hdservices.repository.TiposCatalogo;

@Model
public class GerenciarTipoCatalogoService {

	@Inject
	private TiposCatalogo tiposCatalogo;

	public TipoCatalogo buscar(Integer id) {
		return tiposCatalogo.buscar(id);
	}

	public List<TipoCatalogo> listar() {
		return tiposCatalogo.listar();
	}

	public void salvar(TipoCatalogo tipo) {
		tiposCatalogo.salvar(tipo);
	}
}
