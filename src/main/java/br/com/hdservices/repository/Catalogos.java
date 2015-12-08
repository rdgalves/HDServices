package br.com.hdservices.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.hdservices.model.Catalogo;
import br.com.hdservices.model.TipoCatalogo;
import br.com.hdservices.repository.filtro.CatalogoFiltro;
import br.com.hdservices.service.NegocioException;

@Stateless
public class Catalogos extends BaseRepository {

	/**
	 * Salvar uma {@link Catalogo}
	 * 
	 * @param pessoa
	 *            {@link Catalogo}
	 * @return {@link Catalogo}
	 */
	public Catalogo salvar(Catalogo catalogo) {
		em.getTransaction().begin();
		try {
			em.merge(catalogo);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		return catalogo;
	}

	public void remover(Catalogo catalogo) {
		em.getTransaction().begin();
		try {
			em.remove(catalogo);
			em.merge(catalogo);
			em.flush();
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			throw new NegocioException("Catalogo não pode ser removido");
		}
	}

	public List<Catalogo> catalogosDe(TipoCatalogo tipo) {
		return em
				.createQuery("from Catalogo where tipo = :tipo", Catalogo.class)
				.setParameter("tipo", tipo).getResultList();
	}

	public Catalogo porId(Integer id) {

		try {
			Catalogo catalogo = em.find(Catalogo.class, id);
			return catalogo;
		} catch (NoResultException nre) {
			return null;
		}

	}

	public Catalogo buscarPorTipo(String tipo) {
		Catalogo catalogo = em.find(Catalogo.class, tipo);
		return catalogo;
	}

	@SuppressWarnings("unchecked")
	public List<Catalogo> listarPorSolicitacao(String solicitacao) {
		try {
			List<Catalogo> catalogos = em
					.createQuery(
							"select c from Catalogo c where solicitacao = :solicitacao")
					.setParameter("solicitacao", solicitacao).getResultList();
			return catalogos;

		} catch (NoResultException nre) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Catalogo> filtrados(CatalogoFiltro filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Catalogo.class);

		if (StringUtils.isNotBlank(filtro.getCatalogo())) {
			criteria.add(Restrictions.ilike("solicitacao",
					filtro.getCatalogo(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("tipo")).list();

	}

}
