package br.com.hdservices.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.hdservices.model.Acao;
import br.com.hdservices.repository.Acoes;

@Model
public class GerenciarAcaoService implements Serializable {

	private static final long serialVersionUID = 1482818454670569010L;

	@Inject
	private Acoes acoes;

	public void salvar(Acao acao) {
		acoes.salvar(acao);
	}
	
	public List<Acao> listarAcaoPorChamado() {
		return acoes.listarAcaoPorChamado();
	}

}
