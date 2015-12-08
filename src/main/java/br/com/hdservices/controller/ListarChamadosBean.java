package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.service.GerenciarChamadoService;

@Model
@ManagedBean(name = "lsSelecaoView")
@ViewScoped
public class ListarChamadosBean implements Serializable {

	private static final long serialVersionUID = 520030162362262378L;

	@Inject
	private GerenciarChamadoService listarFilaChamadoService;

	private Chamado chamado;
	private List<Chamado> chamados;
	private String[] valoresChamado;
	private Chamado chamadoSelecionado;

	public ListarChamadosBean() {

	}

	public Chamado getChamadoSelecionado() {
		return chamadoSelecionado;
	}

	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		this.chamadoSelecionado = chamadoSelecionado;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	public String[] getValoresChamado() {
		return valoresChamado;
	}

	public void setValoresChamado(String[] valoresChamado) {
		this.valoresChamado = valoresChamado;
	}

	private void limpar() {
		chamado = new Chamado();
	}

	@PostConstruct
	public void init() {
		try {
			this.chamados = listarFilaChamadoService
					.listarChamadosPorRelator(SessionContext.getInstance()
							.getUsuarioLogado());
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
