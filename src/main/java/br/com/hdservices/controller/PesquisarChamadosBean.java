package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Catalogo;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.model.TipoCatalogo;
import br.com.hdservices.repository.Chamados;
import br.com.hdservices.repository.filtro.ChamadoFiltro;
import br.com.hdservices.service.GerenciarTipoCatalogoService;

@Model
@ManagedBean(name = "psSelecaoView")
@ViewScoped
public class PesquisarChamadosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Chamados chamados;

	@Inject
	private GerenciarTipoCatalogoService tipoCatalogoService;

	private ChamadoFiltro filtro;
	private List<Chamado> chamadosFiltrados;
	private List<TipoCatalogo> tiposCatalogo;
	private TipoCatalogo tipo;
	private Catalogo catalogo;
	private Chamado chamadoSelecionado;

	public Chamado getChamadoSelecionado() {
		return chamadoSelecionado;
	}

	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		this.chamadoSelecionado = chamadoSelecionado;
		SessionContext.getInstance().setChamadoSelecionado(chamadoSelecionado);
	}

	public PesquisarChamadosBean() {
		filtro = new ChamadoFiltro();
		chamadosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		chamadosFiltrados = chamados.filtrados(filtro);
	}

	public ChamadoFiltro getFiltro() {
		return filtro;
	}

	public void setFiltro(ChamadoFiltro filtro) {
		this.filtro = filtro;
	}

	public List<Chamado> getChamadosFiltrados() {
		return chamadosFiltrados;
	}

	public List<TipoCatalogo> getTiposCatalogo() {
		return tiposCatalogo;
	}

	public void setTiposCatalogo(List<TipoCatalogo> tiposCatalogo) {
		this.tiposCatalogo = tiposCatalogo;
	}

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

	private void limpar() {
		tipo = new TipoCatalogo();
		catalogo = new Catalogo();
	}

	@PostConstruct
	public void init() {
		try {
			this.setTiposCatalogo(tipoCatalogoService.listar());
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}