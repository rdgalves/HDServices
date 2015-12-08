package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.com.hdservices.model.Chamado;
import br.com.hdservices.service.GerenciarChamadoService;
import br.com.hdservices.util.jsf.FacesUtil;

@Model
@ManagedBean(name = "dtSelecaoReabrirChamadoView")
@SessionScoped
public class ReabrirChamadoBean implements Serializable {

	private static final long serialVersionUID = 6494970003382119130L;

	@Inject
	private GerenciarChamadoService reabrirChamadoService;

	private Chamado chamado;
	private List<Chamado> chamadoFiltrado;
	private String[] valoresChamado;
	private List<Chamado> chamadosSelecionados;
	private Chamado chamadoSelecionado;

	public String[] getValoresChamado() {
		return valoresChamado;
	}

	public void setValoresChamado(String[] valoresChamado) {
		this.valoresChamado = valoresChamado;
	}

	public List<Chamado> getChamadosSelecionados() {
		return chamadosSelecionados;
	}

	public void setChamadosSelecionados(List<Chamado> chamadosSelecionados) {
		this.chamadosSelecionados = chamadosSelecionados;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public List<Chamado> getChamadoFiltrado() {
		return chamadoFiltrado;
	}

	public void setChamadoFiltrado(List<Chamado> chamadoFiltrado) {
		this.chamadoFiltrado = chamadoFiltrado;
	}

	public Chamado getChamadoSelecionado() {
		return chamadoSelecionado;
	}

	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		this.chamadoSelecionado = chamadoSelecionado;
	}

	public void pesquisar() {
		chamadoFiltrado = reabrirChamadoService
				.listarChamadosEncerrados(chamado.getNumeroChamado());
	}

	// private void verificaSituacao(Chamado chamado) {
	// this.chamado = chamado;
	// Chamado chamadoOriginal = new Chamado();
	// if (this.chamado != null && this.chamado.getNumeroChamado() != 0) {
	// chamadoOriginal = reabrirChamadoService
	// .buscarChamadoPorNumero(this.chamado.getNumeroChamado());
	// }
	// if (chamadoOriginal != null
	// && chamadoOriginal.getSituacao().equals("ENCERRADO")) {
	// this.chamado.setSituacao("ABERTO");
	// }
	// }

	public String reabrirChamado() {
		// verificaSituacao(chamadoSelecionado);
		chamadoSelecionado.setSituacao("ABERTO");
		reabrirChamadoService.salvar(chamadoSelecionado);
		FacesUtil.addInfoMessage("Chamado reaberto!");
		limpar();
		return "ReabrirChamado";
	}

	// public String reabrirChamado() {
	// for (Chamado _chamado : getChamadosSelecionados()) {
	// _chamado.setSituacao("ABERTO");
	// reabrirChamadoService.salvar(_chamado);
	// }
	// return "/pages/Atendimento/AcompanharChamados";
	// }

	public void limpar() {
		chamado = new Chamado();
		chamadoFiltrado = new ArrayList<>();
	}

	@PostConstruct
	public void init() {
		try {
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
