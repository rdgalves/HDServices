package br.com.hdservices.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Catalogo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idCatalogo;
	private TipoCatalogo tipo;
	private Pessoa autor;
	private Date dataCriacao;
	private int sla;
	private String solicitacao;
	private List<Chamado> chamados;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIdCatalogo() {
		return idCatalogo;
	}

	public void setIdCatalogo(int idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	@OneToMany
	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	public String getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}

	public int getSla() {
		return sla;
	}

	public void setSla(int sla) {
		this.sla = sla;
	}

	@ManyToOne
	public Pessoa getAutor() {
		return autor;
	}

	public void setAutor(Pessoa autor) {
		this.autor = autor;
	}

	public void setId(int idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	@ManyToOne
	public TipoCatalogo getTipo() {
		return tipo;
	}

	public void setTipo(TipoCatalogo tipo) {
		this.tipo = tipo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCatalogo;
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
		Catalogo other = (Catalogo) obj;
		if (idCatalogo != other.idCatalogo)
			return false;
		return true;
	}

}
