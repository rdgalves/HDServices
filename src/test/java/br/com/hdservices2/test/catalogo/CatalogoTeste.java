package br.com.hdservices2.test.catalogo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.hdservices.model.Catalogo;
import br.com.hdservices.model.Pessoa;
import br.com.hdservices.model.TipoCatalogo;
import br.com.hdservices2.test.base.BaseTest;

/**
 * Classe de testes unitários para o {@link Pessoa}
 * 
 * @author Rodrigo Alves (rdg.alvess@gmail.com)
 * @date 04/10/2015
 *
 */
public class CatalogoTeste extends BaseTest {

	private Catalogo catalogo;
	private TipoCatalogo tipoCatalogo;
//	private CategoriaCatalogo categoriaCatalogo;

	@Before
	public void before() {
		super.before();
		catalogo = new Catalogo();
		tipoCatalogo = new TipoCatalogo();
//		categoriaCatalogo = new CategoriaCatalogo();
	}

	@After
	public void after() {
		super.after();
		catalogo = null;
		tipoCatalogo = null;
//		categoriaCatalogo = null;
	}

	@Test
	public void salvarPessoaComSucesso() {

		em.getTransaction().begin();
		
//		categoriaCatalogo.setDescricao("Computador");
//		em.persist(categoriaCatalogo);
		
		tipoCatalogo.setDescricao("Computador do POSTALIS com problema");
		em.persist(tipoCatalogo);
		
//		catalogo.setSla();
		//catalogo.setTipo("Computador do Rodrigo");
		catalogo.setTipo(tipoCatalogo);
		catalogo.setSolicitacao("Qualquer coisa...");

		em.persist(catalogo);
		em.getTransaction().commit();
	}

}
