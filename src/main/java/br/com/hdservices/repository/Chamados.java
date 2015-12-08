package br.com.hdservices.repository;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.hdservices.model.Catalogo;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.model.Pessoa;
import br.com.hdservices.repository.filtro.ChamadoFiltro;

@Stateless
public class Chamados extends BaseRepository {

	/**
	 * Salvar uma {@link Catalogo}
	 * 
	 * @param pessoa
	 *            {@link Catalogo}
	 * @return {@link Catalogo}
	 */

	public void salvar(Chamado chamado) {
		em.getTransaction().begin();
		try {
			em.merge(chamado);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	public Chamado porNumeroChamado(Integer numeroChamado) {
		try {
			Chamado chamado = em.find(Chamado.class, numeroChamado);
			return chamado;
		} catch (NoResultException nre) {
			return null;
		}
	}

	public List<Chamado> listaPorNumero(Integer numeroChamado) {
		return (List<Chamado>) em
				.createQuery(
						"from Chamado where numeroChamado = :numeroChamado",
						Chamado.class)
				.setParameter("numeroChamado", numeroChamado).getResultList();
	}

	public List<Chamado> listarChamadosAbertos() {
		return (List<Chamado>) em
				.createQuery(
						"from Chamado where situacao = 'ABERTO' and especialista = null",
						Chamado.class).getResultList();
	}

	public List<Chamado> listarChamadosPorEspecialista(Pessoa pessoa) {
		return (List<Chamado>) em
				.createQuery(
						"from Chamado where especialista = :especialista and situacao = 'ABERTO'",
						Chamado.class).setParameter("especialista", pessoa)
				.getResultList();
	}

	public List<Chamado> listarChamadosPorRelator(Pessoa pessoa) {
		return (List<Chamado>) em
				.createQuery(
						"from Chamado where relator = :relator and situacao = 'ABERTO'",
						Chamado.class).setParameter("relator", pessoa)
				.getResultList();
	}

	public List<Chamado> listarChamadosPorSituacao(Integer chamado) {
		return (List<Chamado>) em
				.createQuery(
						"from Chamado where numeroChamado = :chamado and situacao = 'ENCERRADO'",
						Chamado.class).setParameter("chamado", chamado)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Chamado> filtrados(ChamadoFiltro filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Chamado.class)
				.createAlias("relator", "r").createAlias("especialista", "e")
				.createAlias("catalogo", "c");

		if (filtro.getNumeroDe() != 0) {
			criteria.add(Restrictions.ge("numeroChamado", filtro.getNumeroDe()));
		}

		if (filtro.getNumeroAte() != 0) {
			criteria.add(Restrictions.le("numeroChamado", filtro.getNumeroAte()));
		}

		if (StringUtils.isNotBlank(filtro.getNomeRelator())) {
			criteria.add(Restrictions.ilike("r.nome", filtro.getNomeRelator(),
					MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getNomeAtendente())) {
			criteria.add(Restrictions.ilike("e.nome",
					filtro.getNomeAtendente(), MatchMode.ANYWHERE));
		}

		if (filtro.getDataCriacaoDe() != null) {
			criteria.add(Restrictions.ge("dataAbertura",
					filtro.getDataCriacaoDe()));
		}

		if (filtro.getDataCriacaoAte() != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(filtro.getDataCriacaoAte());
			c.add(Calendar.DATE, 1);

			criteria.add(Restrictions.le("dataAbertura", c.getTime()));
		}

		if (filtro.getTipo() != null
				&& filtro.getTipo().getIdTipoCatalogo() != null) {
			criteria.add(Restrictions.eq("c.tipo.idTipoCatalogo", filtro
					.getTipo().getIdTipoCatalogo()));
		}

		return criteria.addOrder(Order.asc("numeroChamado")).list();

	}
}
