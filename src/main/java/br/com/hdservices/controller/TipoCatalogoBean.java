package br.com.hdservices.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.hdservices.model.TipoCatalogo;
import br.com.hdservices.service.GerenciarTipoCatalogoService;
import br.com.hdservices.util.jsf.FacesUtil;

@Model
@ManagedBean
@ViewScoped
public class TipoCatalogoBean implements Serializable {

	private static final long serialVersionUID = -7051839823957311262L;

	@Inject
	private GerenciarTipoCatalogoService tipoCatalogoService;

	// @Inject
	// private TiposCatalogo tipos;

	private TipoCatalogo tipo;

	// private CatalogoFilter filtro;
	// private List<Catalogo> catalogosFiltrados;

	public String salvar() {
		tipoCatalogoService.salvar(tipo);
		FacesUtil.addInfoMessage("Tipo inserido no catalogo!");
		return "CadastroCatalogo";

	}

	public TipoCatalogo getTipo() {
		return tipo;
	}

	public void setTipo(TipoCatalogo tipo) {
		this.tipo = tipo;
	}



	// public List<Catalogo> getCatalogosFiltrados() {
	// return catalogosFiltrados;
	// }
	//
	// public CatalogoFilter getFilter() {
	// return filtro;
	// }
	//
	// public void pesquisar() {
	// catalogosFiltrados = tipos.filtrados(filtro);
	// }

	@PostConstruct
	public void init() {
		try {
			setTipo(new TipoCatalogo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
