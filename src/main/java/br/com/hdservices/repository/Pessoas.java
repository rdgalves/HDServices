package br.com.hdservices.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.hdservices.model.Pessoa;
import br.com.hdservices.repository.filtro.PessoaFiltro;

@Stateless
public class Pessoas extends BaseRepository {

	/**
	 * Salvar uma {@link Pessoa}
	 * 
	 * @param pessoa
	 *            {@link Pessoa}
	 * @return {@link Pessoa}
	 */

	public void salvar(Pessoa pessoa) {
		em.getTransaction().begin();
		try {
			em.merge(pessoa);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	public Pessoa porMatricula(String matricula) {
		// Pessoa pessoa = em.find(Pessoa.class, matricula);
		try {
			Pessoa pessoa = em.find(Pessoa.class, matricula);
			// Pessoa pessoa = (Pessoa) em
			// .createQuery(
			// "select p from Pessoa p where matricula = :matricula")
			// .setParameter("matricula", matricula).getSingleResult();
			return pessoa;
		} catch (NoResultException nre) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> filtrados(PessoaFiltro filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pessoa.class);

		if (StringUtils.isNotBlank(filtro.getMatricula())) {
			criteria.add(Restrictions.eq("matricula", filtro.getMatricula()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();

	}

	public void validarLoginPessoa(String matricula, String senha) throws Exception {
		try {
			Pessoa pessoa = (Pessoa) em
					.createQuery(" select p from Pessoa p where p.matricula = :matricula and p.senha = :senha")
					.setParameter("matricula", matricula).setParameter("senha", senha).getSingleResult();

			if (pessoa == null) {
				throw new Exception("Usuário não cadastrado");
			}
		} catch (NoResultException e) {
			throw new Exception(e.getMessage());
		}
	}

	public Pessoa porLoginSenha(PessoaFiltro filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pessoa.class);

		if (StringUtils.isNotBlank(filtro.getMatricula())) {
			criteria.add(Restrictions.eq("matricula", filtro.getMatricula()));
		}

		if (StringUtils.isNotBlank(filtro.getSenha())) {
			criteria.add(Restrictions.ilike("senha", filtro.getSenha()));
		}

		return (Pessoa) criteria.uniqueResult();

	}

}
