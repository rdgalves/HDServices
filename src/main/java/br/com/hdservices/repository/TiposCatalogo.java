package br.com.hdservices.repository;

import java.util.List;

import javax.ejb.Stateless;

import br.com.hdservices.model.TipoCatalogo;

@Stateless
public class TiposCatalogo extends BaseRepository {

	public TipoCatalogo buscar(Integer id) {
		return em.find(TipoCatalogo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TipoCatalogo> listar() {
		return em.createQuery("select t from TipoCatalogo t").getResultList();
	}

	public TipoCatalogo salvar(TipoCatalogo tipo) {
		em.getTransaction().begin();
		try {
			em.persist(tipo);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		return tipo;
	}
}
