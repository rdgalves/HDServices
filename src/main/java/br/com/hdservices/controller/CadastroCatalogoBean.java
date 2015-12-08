package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.hdservices.model.Catalogo;
import br.com.hdservices.model.TipoCatalogo;
import br.com.hdservices.service.GerenciarCatalogoService;
import br.com.hdservices.service.GerenciarTipoCatalogoService;
import br.com.hdservices.util.jsf.FacesUtil;

@Model
@ManagedBean
@ViewScoped
public class CadastroCatalogoBean implements Serializable {

	private static final long serialVersionUID = 2094191643303869376L;

	@Inject
	private GerenciarCatalogoService cadastroCatalogoService;

	@Inject
	private GerenciarTipoCatalogoService tipoCatalogoService;

	private TipoCatalogo tipo;
	private Catalogo catalogo;
	private List<TipoCatalogo> tiposCatalogo;
	

	public TipoCatalogo getTipo() {
		return tipo;
	}

	public void setTipo(TipoCatalogo tipo) {
		this.tipo = tipo;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public List<TipoCatalogo> getTiposCatalogo() {
		return tiposCatalogo;
	}

	public void setTiposCatalogo(List<TipoCatalogo> tiposCatalogo) {
		this.tiposCatalogo = tiposCatalogo;
	}

	private void limpar() {
		catalogo = new Catalogo();
		catalogo.setTipo(new TipoCatalogo());
	}

	public String salvar() {
		cadastroCatalogoService.salvar(catalogo);
		limpar();
		FacesUtil.addInfoMessage("Itém inserido no catalogo!");
		return "CadastroCatalogo";
	}

	@PostConstruct
	public void init() {
		try {
			this.tiposCatalogo = tipoCatalogoService.listar();
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
