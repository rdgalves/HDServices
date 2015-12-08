package br.com.hdservices2.test.base;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

/**
 * Classe abstrata com as implementações comuns para todas as demais classes de
 * testes
 * 
 * @author Rodrigo Alves (rdg.alvess@gmail.com)
 * @date 04/10/2015
 *
 */
public abstract class BaseTest {
	
	protected EntityManager em = null;

	@Before
	protected void before() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HDServicesPU");
		em = emf.createEntityManager();
	}

	@After
	protected void after() {
		em = null;
	}
}
