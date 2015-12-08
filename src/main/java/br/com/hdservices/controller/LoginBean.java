package br.com.hdservices.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Pessoa;
import br.com.hdservices.service.AutenticaPessoaService;
import br.com.hdservices.util.jsf.FacesUtil;

/**
 * Controla o LOGIN e LOGOUT do Usuário
 * */
@Model
@ManagedBean(value = "UsuarioLogadoBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 324299637922711210L;

	private static Logger logger = Logger.getLogger(LoginBean.class);

	@Inject
	@ManagedProperty(value = "#{autenticaPessoaService}")
	private AutenticaPessoaService autenticaPessoaService;

	private String matricula;
	private String senha;

	/**
	 * Retorna usuario logado
	 * */
	public Pessoa getUser() {
		return (Pessoa) SessionContext.getInstance().getUsuarioLogado();
	}

	public String doLogin() {
		try {
			logger.info("Tentando logar com a matricula " + matricula);
			Pessoa pessoa = autenticaPessoaService.isUsuarioReadyToLogin(
					matricula, senha);

			if (pessoa == null) {
				FacesUtil
						.addErrorMessage("Login ou Senha errado, tente novamente!");
				FacesContext.getCurrentInstance().validationFailed();
				return "";
			}

			logger.info("Login efetuado com sucesso");
			SessionContext.getInstance().setAttribute("usuarioLogado", pessoa);
			return "/index.xhtml?faces-redirect=true";
		} catch (PersistenceException e) {
			FacesUtil.addErrorMessage(e.getMessage());
			FacesContext.getCurrentInstance().validationFailed();
			e.printStackTrace();
			return "";
		}

	}

	public void doLogout() throws IOException {
		logger.info("Fazendo logout com usuário "
				+ SessionContext.getInstance().getUsuarioLogado()
						.getMatricula());
		SessionContext.getInstance().encerrarSessao();
		FacesUtil.addInfoMessage("Logout realizado com sucesso !");
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/HDServices/Login.xhtml");
		// return "/Login.xhtml?faces-redirect=true";
	}

	public void solicitarNovaSenha() {
		try {
			// getUserBO().gerarNovaSenha(login, email);
			// addInfoMessage("Nova Senha enviada para o email " + email);
		} catch (Exception e) {
			// FacesUtil.addErrorMessage(e.getMessage());
			// FacesContext.getCurrentInstance().validationFailed();
		}
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
