package br.com.hdservices.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.hdservices.model.Chamado;
import br.com.hdservices.model.Pessoa;
import br.com.hdservices.repository.Chamados;
import br.com.hdservices.repository.filtro.ChamadoFiltro;

@Model
public class GerenciarChamadoService implements Serializable {

	private static final long serialVersionUID = 1482818454670569010L;

	@Inject
	private Chamados chamados;

	public void salvar(Chamado chamado) {
		
		Chamado chamadoExistente = chamados.porNumeroChamado(chamado.getNumeroChamado());
		
		if (chamadoExistente != null && !chamadoExistente.equals(chamado)) {
			throw new NegocioException("");
		}

		chamados.salvar(chamado);
	}

	public Chamado buscarChamadoPorNumero(Integer chamado){
		return chamados.porNumeroChamado(chamado);		
	}
	
	
	public List<Chamado> listarChamadosEncerrados(Integer chamado) {
		return chamados.listarChamadosPorSituacao(chamado);
	}
	
	public List<Chamado> listarChamadosAbertos() {
		return chamados.listarChamadosAbertos();
	}

	public List<Chamado> listarChamadosPorEspecialista(Pessoa pessoa) {
		return chamados.listarChamadosPorEspecialista(pessoa);
	}
	
	public List<Chamado> listarChamadosPorRelator(Pessoa pessoa) {
		return chamados.listarChamadosPorRelator(pessoa);
	}
	
	public List<Chamado> pesquisaChamadosFiltrados(ChamadoFiltro filtro){
		return chamados.filtrados(filtro);
	}

}
