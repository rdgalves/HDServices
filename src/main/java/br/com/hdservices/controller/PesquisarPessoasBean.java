package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.hdservices.model.Pessoa;
import br.com.hdservices.repository.Pessoas;
import br.com.hdservices.repository.filtro.PessoaFiltro;


@Model
@ManagedBean
public class PesquisarPessoasBean implements Serializable{

	private static final long serialVersionUID = 7849460511018525780L;
	
	@Inject
	private Pessoas pessoas;
	
	private PessoaFiltro filtro;
	private List<Pessoa> pessoasFiltradas;
	
	public PesquisarPessoasBean(){
		filtro = new PessoaFiltro();
	}
		
	public void pesquisar(){
		pessoasFiltradas = pessoas.filtrados(filtro);
	}
	
	public List<Pessoa> getPessoasFiltradas() {
		return pessoasFiltradas;
		
	}
	
	public PessoaFiltro getFilter(){
		return filtro;
	}
}
