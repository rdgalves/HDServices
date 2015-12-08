package br.com.hdservices.service;

import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.hdservices.model.Pessoa;
import br.com.hdservices.repository.Pessoas;

@Model
public class GerenciarPessoaService implements Serializable {

	private static final long serialVersionUID = 1482818454670569010L;

	@Inject
	private Pessoas pessoas;

	@Transactional
	public void salvar(Pessoa pessoa) {

		Pessoa pessoaExistente = pessoas.porMatricula(pessoa.getMatricula());

		if (pessoaExistente != null && !pessoaExistente.equals(pessoa)) {
			throw new NegocioException("Matricula já cadastrada!");
		}
		pessoas.salvar(pessoa);
	}

	public Pessoa buscarPorMatricula(String matricula) {
		return pessoas.porMatricula(matricula);
	}
}
