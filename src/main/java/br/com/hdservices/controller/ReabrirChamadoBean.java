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
@ManagedBean
@SessionScoped
public class ReabrirChamadoBean implements Serializable {

	private static final long serialVersionUID = 6494970003382119130L;

	@Inject
	private GerenciarChamadoService reabrirChamadoService;

	private Chamado chamado;
	private List<Chamado> chamadoFiltrado;

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

	public void pesquisar() {
		chamadoFiltrado = reabrirChamadoService
				.listarChamadosEncerrados(chamado.getNumeroChamado());
	}

	private void verificaSituacao(Chamado chamado) {
		this.chamado = chamado;
		Chamado chamadoOriginal = new Chamado();
		if (this.chamado != null && this.chamado.getNumeroChamado() != 0) {
			chamadoOriginal = reabrirChamadoService
					.buscarChamadoPorNumero(this.chamado.getNumeroChamado());
		}
		if (chamadoOriginal != null
				&& chamadoOriginal.getSituacao().equals("ENCERRADO")) {
			this.chamado.setSituacao("ABERTO");
		}
	}

	public String reabrirChamado(Chamado chamado) {
		verificaSituacao(chamado);
		reabrirChamadoService.salvar(this.chamado);
		FacesUtil.addInfoMessage("Chamado reaberto!");
		return "ReabrirChamado";
	}

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
