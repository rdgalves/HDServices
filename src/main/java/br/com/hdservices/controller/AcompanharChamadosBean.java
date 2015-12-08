package br.com.hdservices.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Acao;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.service.GerenciarAcaoService;
import br.com.hdservices.service.GerenciarChamadoService;
import br.com.hdservices.util.jsf.FacesUtil;

@Model
@ManagedBean(name = "SelecaoView")
@SessionScoped
public class AcompanharChamadosBean implements Serializable {

	private static final long serialVersionUID = 520030162362262378L;

	@Inject
	private GerenciarChamadoService chamadoService;

	@Inject
	private GerenciarAcaoService acaoService;

	private Acao acao;
	private Chamado chamado;
	private List<Chamado> chamados;
	private Chamado chamadoSelecionado;

	public AcompanharChamadosBean() {

	}

	public void redireciona() {
		try {

			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect("/HDServices/pages/Atendimento/ListarAcoes.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Chamado getChamadoSelecionado() {
		return chamadoSelecionado;
	}

	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		this.chamadoSelecionado = chamadoSelecionado;
		SessionContext.getInstance().setChamadoSelecionado(chamadoSelecionado);
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

	private void limpar() {
		chamado = new Chamado();
		acao = new Acao();
	}

	public void salvarAcao() {

		Date dataAtual = new Date();
		acao.setDataRegistro(dataAtual);
		acao.setEspecialista(SessionContext.getInstance().getUsuarioLogado());
		acao.setChamado(SessionContext.getInstance().getChamadoSelecionado());

		acaoService.salvar(acao);

		FacesUtil.addInfoMessage("Ação Registrada com sucesso");
		SessionContext.getInstance().setChamadoSelecionado(null);
		;
	}

	public void encerrarChamado() {
		Chamado chamadoSelecionado = SessionContext.getInstance().getChamadoSelecionado();
		if (chamadoSelecionado.getSituacao().equals("ABERTO")) {
			chamadoSelecionado.setSituacao("ENCERRADO");
			chamado = chamadoSelecionado;
		}
		chamadoService.salvar(chamado);
		this.chamados = chamadoService
				.listarChamadosPorEspecialista(SessionContext.getInstance()
						.getUsuarioLogado());
	}

	@PostConstruct
	public void init() {
		try {
			this.chamados = chamadoService
					.listarChamadosPorEspecialista(SessionContext.getInstance()
							.getUsuarioLogado());

			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
