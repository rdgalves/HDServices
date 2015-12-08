package br.com.hdservices.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Acao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idAcao;
	private String descricao;
	private Date dataRegistro;
	private Date tempoEsforco;
	private Pessoa especialista;
	private Chamado chamado;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Long idAcao) {
		this.idAcao = idAcao;
	}

	@ManyToOne
	public Chamado getNumeroChamado() {
		return chamado;
	}

	public void setNumeroChamado(Chamado numeroChamado) {
		this.chamado = numeroChamado;
	}

	@ManyToOne
	public Pessoa getEspecialista() {
		return especialista;
	}

	public void setEspecialista(Pessoa pessoa) {
		this.especialista = pessoa;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataAtual) {
		this.dataRegistro = dataAtual;
	}

	public Date getTempoEsforco() {
		return tempoEsforco;
	}

	public void setTempoEsforco(Date tempoEsforco) {
		this.tempoEsforco = tempoEsforco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAcao == null) ? 0 : idAcao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acao other = (Acao) obj;
		if (idAcao == null) {
			if (other.idAcao != null)
				return false;
		} else if (!idAcao.equals(other.idAcao))
			return false;
		return true;
	}

}
