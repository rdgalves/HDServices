package br.com.hdservices.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.hdservices.model.Pessoa;
import br.com.hdservices.service.GerenciarPessoaService;
import br.com.hdservices.util.jsf.FacesUtil;
import br.com.hdservices.util.security.SecurityUtils;

@Model
@ManagedBean
public class CadastroPessoaBean implements Serializable {

	private static final long serialVersionUID = -1542160936720433882L;

	@Inject
	private GerenciarPessoaService cadastroPessoaService;

	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;

		if (this.pessoa != null) {
			this.pessoa.getPerfil();
			this.pessoa.getStatus();
		}
	}

	private void limpar() {
		// pessoa = new Pessoa();
		setPessoa(new Pessoa());
	}

	public boolean isEditando() {
		if (getPessoa() != null && getPessoa().getMatricula() != null) {
			return true;
		}
		return false;
	}

	public String salvar() {
		verificarSenhaCadastrada();
		gerarPrimeiraSenha();
		cadastroPessoaService.salvar(pessoa);
		limpar();
		FacesUtil.addInfoMessage("Dados Salvos com sucesso!");
		return "CadastroPessoa";
	}

	private void verificarSenhaCadastrada() {
		Pessoa pessoaOriginal = new Pessoa();
		if (pessoa != null && pessoa.getMatricula() != null) {
			pessoaOriginal = cadastroPessoaService.buscarPorMatricula(pessoa
					.getMatricula());
		}
		if (pessoaOriginal != null && pessoaOriginal.getSenha() != null) {
			pessoa.setSenha(pessoaOriginal.getSenha());
		}
	}

	private void gerarPrimeiraSenha() {
		if (pessoa.getSenha() == null) {
			pessoa.setSenha(SecurityUtils.convertStringToMd5("123456"));
		}
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
