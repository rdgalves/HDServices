package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.hdservices.model.Catalogo;
import br.com.hdservices.repository.Catalogos;
import br.com.hdservices.repository.filtro.CatalogoFiltro;
import br.com.hdservices.util.jsf.FacesUtil;

@Model
@ManagedBean
public class PesquisarCatalogosBean implements Serializable {

	private static final long serialVersionUID = -3472637190799410274L;

	@Inject
	private Catalogos catalogos;

	private Catalogo catalogoSelecionado;
	private CatalogoFiltro filtro;
	private List<Catalogo> catalogosFiltrados;

	public PesquisarCatalogosBean() {
		filtro = new CatalogoFiltro();
	}

	public List<Catalogo> getCatalogosFiltrados() {
		return catalogosFiltrados;
	}

	public CatalogoFiltro getFilter() {
		return filtro;
	}

	public void pesquisar() {
		catalogosFiltrados = catalogos.filtrados(filtro);
	}
	
	public void excluir() {
		catalogos.remover(catalogoSelecionado);
		pesquisar();
		
		FacesUtil.addInfoMessage("Serviço " + catalogoSelecionado.getSolicitacao() + "Removido Com Sucesso");
	}

	public Catalogo getCatalogoSelecionado() {
		return catalogoSelecionado;
	}

	public void setCatalogoSelecionado(Catalogo catalogoSelecionado) {
		this.catalogoSelecionado = catalogoSelecionado;
	}

}
