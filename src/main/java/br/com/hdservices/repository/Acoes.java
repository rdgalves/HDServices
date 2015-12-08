package br.com.hdservices.repository;

import java.util.List;

import javax.ejb.Stateless;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Acao;
import br.com.hdservices.model.Catalogo;

@Stateless
public class Acoes extends BaseRepository {

	/**
	 * Salvar uma {@link Catalogo}
	 * 
	 * @param pessoa
	 *            {@link Catalogo}
	 * @return {@link Catalogo}
	 */

	public void salvar(Acao acao) {
		em.getTransaction().begin();
		try {
			em.merge(acao);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	public List<Acao> listarAcaoPorChamado() {
		return (List<Acao>) em
				.createQuery("from Acao where numeroChamado = :numeroChamado",
						Acao.class)
				.setParameter("numeroChamado", SessionContext.getInstance().getChamadoSelecionado())
				.getResultList();
	}
}
