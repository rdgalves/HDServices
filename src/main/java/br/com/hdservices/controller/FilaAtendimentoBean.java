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
@ManagedBean(name = "dtSelecaoView")
@ViewScoped
public class FilaAtendimentoBean implements Serializable {

	private static final long serialVersionUID = 520030162362262378L;

	@Inject
	private GerenciarChamadoService filaAtendimentoService;

	@Inject
	private GerenciarChamadoService abrirChamadoService;

	private Chamado chamado;
	private List<Chamado> chamados;
	private String[] valoresChamado;
	private Chamado chamadoSelecionado;
	private List<Chamado> chamadosSelecionados;

	public FilaAtendimentoBean() {

	}

	public String adicionaAtendente() {
		for (Chamado _chamado : getChamadosSelecionados()) {
			_chamado.setEspecialista(SessionContext.getInstance()
					.getUsuarioLogado());
			abrirChamadoService.salvar(_chamado);
		}
		return "/pages/Atendimento/AcompanharChamados";
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

	public List<Chamado> getChamadosSelecionados() {
		return chamadosSelecionados;
	}

	public void setChamadosSelecionados(List<Chamado> chamadosSelecionados) {
		this.chamadosSelecionados = chamadosSelecionados;
	}

	private void limpar() {
		chamado = new Chamado();
	}

	@PostConstruct
	public void init() {
		try {
			this.chamados = filaAtendimentoService.listarChamadosAbertos();
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
