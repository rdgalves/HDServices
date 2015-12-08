package br.com.hdservices.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;

import br.com.hdservices.controller.LoginBean;
import br.com.hdservices.model.Pessoa;
import br.com.hdservices.repository.Pessoas;
import br.com.hdservices.repository.filtro.PessoaFiltro;

@Model
public class AutenticaPessoaService implements Serializable {

	private static final long serialVersionUID = 1482818454670569010L;

	private static Logger logger = Logger.getLogger(LoginBean.class);

	private PessoaFiltro filtro;

	@Inject
	private Pessoas pessoas;

	// Verifica se usuário existe ou se pode logar
	public Pessoa isUsuarioReadyToLogin(String matricula, String senha) {
		filtro = new PessoaFiltro();
		try {
			matricula = matricula.toLowerCase().trim();
			logger.info("Verificando login do usuário " + matricula);
			// List retorno =
			// pessoas.findByNamedQuery(Pessoa.FIND_BY_MATRICULA_SENHA, new
			// NamedParams("matricula",
			// matricula.trim(), "senha", convertStringToMd5(senha)));

			filtro.setMatricula(matricula);
			filtro.setSenha(convertStringToMd5(senha));

			Pessoa retorno = pessoas.porLoginSenha(filtro);

			if (retorno != null) {
				Pessoa userFound = (Pessoa) retorno;
				return userFound;
			}

			return null;
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	private String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {
			// Instanciamos o nosso HASH MD5, poderíamos usar outro como
			// SHA, por exemplo, mas optamos por MD5.
			mDigest = MessageDigest.getInstance("MD5");

			// Convert a String valor para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior comparação se senhas
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
