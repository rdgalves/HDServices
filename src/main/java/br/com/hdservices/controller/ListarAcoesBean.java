package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.com.hdservices.model.Acao;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.service.GerenciarAcaoService;

@Model
@ManagedBean
@SessionScoped
public class ListarAcoesBean implements Serializable {

	private static final long serialVersionUID = 520030162362262378L;

	@Inject
	private GerenciarAcaoService acaoService;

	private Acao acao;
	private Chamado chamado;
	private List<Acao> acoes;

	public ListarAcoesBean() {

	}

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	private void limpar() {
		chamado = new Chamado();
//		acao = new Acao();
	}

//	public void listarAcao() {
//		acoes = acaoService.listarAcaoPorChamado(SessionContext.getInstance()
//				.getChamadoSelecionado());
//	}

	@PostConstruct
	public void init() {
		try {
			this.acoes = acaoService.listarAcaoPorChamado();
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
