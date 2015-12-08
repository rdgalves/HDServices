package br.com.hdservices.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.hdservices.model.Pessoa;
import br.com.hdservices.service.GerenciarPessoaService;
import br.com.hdservices.util.jsf.FacesUtil;

@Model
@ManagedBean
@ViewScoped
public class AlterarSenhaBean implements Serializable {

	private static final long serialVersionUID = 8910873899951910769L;

	@Inject
	private GerenciarPessoaService gerenciarPessoaService;

	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	private void verificarSenhaCadastrada() {
		Pessoa pessoaOriginal = new Pessoa();
		if (pessoa != null && pessoa.getMatricula() != null) {
			pessoaOriginal = gerenciarPessoaService.buscarPorMatricula(pessoa
					.getMatricula());
		}
		if (pessoaOriginal != null && pessoaOriginal.getSenha() != null) {
			pessoa.setSenha(pessoaOriginal.getSenha());
		}
	}

	public String salvar() {
		verificarSenhaCadastrada();
		gerenciarPessoaService.salvar(pessoa);
		limpar();
		FacesUtil.addInfoMessage("Usuário cadastrado com sucesso!");
		return "CadastroPessoa";
	}

	private void limpar() {
		// pessoa = new Pessoa();
		setPessoa(new Pessoa());
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
