package br.com.hdservices;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.hdservices.model.Catalogo;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.model.Pessoa;

public class SessionContext {
	private static SessionContext instance;

	public static SessionContext getInstance() {
		if (instance == null) {
			instance = new SessionContext();
		}
		return instance;
	}

	private SessionContext() {
	}

	private ExternalContext currentExternalContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException(
					"O FacesContext não pode ser chamado fora de uma requisição HTTP");
		} else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}

	public Pessoa getUsuarioLogado() {
		return (Pessoa) getAttribute("usuarioLogado");
	}

	public void setUsuarioLogado(Pessoa usuario) {
		setAttribute("usuarioLogado", usuario);
	}

	public Chamado getChamadoSelecionado() {
		return (Chamado) getAttribute("chamadoSelecionado");
	}

	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		setAttribute("chamadoSelecionado", chamadoSelecionado);
	}

	@SuppressWarnings("unchecked")
	public List<Catalogo> getCatalogos() {
		return (List<Catalogo>) getAttribute("catalogosFiltrados");
	}

	public void setCatalogos(List<Catalogo> catalogosFiltrados) {
		setAttribute("catalogosFiltrados", catalogosFiltrados);
	}

	public void encerrarSessao() {
		setAttribute("usuarioLogado", null);
		currentExternalContext().invalidateSession();
	}

	public Object getAttribute(String nome) {
		return currentExternalContext().getSessionMap().get(nome);
	}

	public void setAttribute(String nome, Object valor) {
		currentExternalContext().getSessionMap().put(nome, valor);
	}
}