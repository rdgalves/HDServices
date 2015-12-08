package br.com.hdservices.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.hdservices.util.jpa.Constantes;

public abstract class BaseRepository {

	public BaseRepository() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
		if (em == null) {
			em = emf.createEntityManager();
		}
	}

	protected EntityManager em = null;

}
