package br.com.hdservices.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Pessoa;
import br.com.hdservices.service.GerenciarPessoaService;
import br.com.hdservices.util.jsf.FacesUtil;
import br.com.hdservices.util.security.SecurityUtils;

@Model
@ManagedBean
@ViewScoped
public class AlterarSenhaBean implements Serializable {

	private static final long serialVersionUID = 8910873899951910769L;

	@Inject
	private GerenciarPessoaService gerenciarPessoaService;

	private Pessoa pessoa;
	private String novaSenha;
	private String novaSenha2;
	private String senhaAtual;

	public String getNovaSenha2() {
		return novaSenha2;
	}

	public void setNovaSenha2(String novaSenha2) {
		this.novaSenha2 = novaSenha2;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	private Boolean isDadosSenhaValidos() {
		Boolean retorno = true;
		Pessoa pessoaOriginal = SessionContext.getInstance().getUsuarioLogado();

		if (!SecurityUtils.convertStringToMd5(senhaAtual).equals(
				pessoaOriginal.getSenha())) {
			FacesUtil.addErrorMessage("Senha atual Invalida!");
			retorno = false;
		}

		if (SecurityUtils.convertStringToMd5(novaSenha).equals(
				pessoaOriginal.getSenha())) {
			FacesUtil.addErrorMessage("A senha não pode ser a mesma");
			retorno = false;
		}

		pessoaOriginal.setSenha(SecurityUtils.convertStringToMd5(novaSenha));

		this.pessoa = pessoaOriginal;
		return retorno;
	}

	public String salvar() {
		if (!isDadosSenhaValidos()) {
			return "AlterarSenha";
		}
		gerenciarPessoaService.salvar(pessoa);
		SessionContext.getInstance().setUsuarioLogado(pessoa);
		limpar();
		FacesUtil.addInfoMessage("Senha Alterada com sucesso!");
		return "AlterarSenha";
	}

	private void limpar() {
		pessoa = new Pessoa();
		setPessoa(new Pessoa());
		novaSenha = null;
		senhaAtual = null;
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
