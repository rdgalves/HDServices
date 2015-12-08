package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Catalogo;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.model.Pessoa;
import br.com.hdservices.model.TipoCatalogo;
import br.com.hdservices.service.GerenciarCatalogoService;
import br.com.hdservices.service.GerenciarChamadoService;
import br.com.hdservices.service.GerenciarTipoCatalogoService;
import br.com.hdservices.util.jsf.FacesUtil;

@Model
@ManagedBean
@ViewScoped
public class AbrirChamadoBean implements Serializable {

	private static final long serialVersionUID = 6494970003382119130L;

	@Inject
	private GerenciarChamadoService abrirChamadoService;

	@Inject
	private GerenciarTipoCatalogoService tipoCatalogoService;

	@Inject
	private GerenciarCatalogoService catalogoService;

	private TipoCatalogo tipo;

	private Chamado chamado = new Chamado();
	private List<TipoCatalogo> tiposCatalogo;
	private List<Catalogo> listaCatalogos;
	private Pessoa usuarioSessao = SessionContext.getInstance()
			.getUsuarioLogado();

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			tiposCatalogo = tipoCatalogoService.listar();
		}
	}

	public void carregarCatalogos() {
		listaCatalogos = catalogoService.catalogosDe(tipo);
		SessionContext.getInstance().setCatalogos(listaCatalogos);
	}

	public TipoCatalogo getTipo() {
		return tipo;
	}

	public void setTipo(TipoCatalogo tipo) {
		this.tipo = tipo;
	}

	public List<TipoCatalogo> getTiposCatalogo() {
		return tiposCatalogo;
	}

	public void setTiposCatalogo(List<TipoCatalogo> tiposCatalogo) {
		this.tiposCatalogo = tiposCatalogo;
	}

	public List<Catalogo> getCatalogos() {
		return listaCatalogos;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public Pessoa getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(Pessoa usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}

	private void limpar() {
		if (listaCatalogos == null || listaCatalogos.size() == 0) {
			listaCatalogos = new ArrayList<Catalogo>();
			listaCatalogos = SessionContext.getInstance().getCatalogos();
			SessionContext.getInstance().setCatalogos(null);
		}
	}

	public String salvar() {
		Date dataAbertura = new Date();

		chamado.setSituacao("ABERTO");
		chamado.setRelator(SessionContext.getInstance().getUsuarioLogado());
		chamado.setDataAbertura(dataAbertura);

		abrirChamadoService.salvar(chamado);
		limpar();
		FacesUtil.addInfoMessage("Chamado Registrado com Sucesso!");
		SessionContext.getInstance().setCatalogos(null);
		return "AbrirChamado";

	}

	public List<Catalogo> getListaCatalogos() {
		return listaCatalogos;
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
