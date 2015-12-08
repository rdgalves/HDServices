package br.com.hdservices.repository.filtro;

import java.util.Date;

import br.com.hdservices.model.TipoCatalogo;

public class ChamadoFiltro {

	private int numeroChamado;
	private int numeroDe;
	private int numeroAte;
	private Date dataCriacaoDe;
	private Date dataCriacaoAte;
	private TipoCatalogo tipo;
	private String nomeRelator;
	private String nomeAtendente;

	public int getNumeroChamado() {
		return numeroChamado;
	}

	public void setNumeroChamado(int numeroChamado) {
		this.numeroChamado = numeroChamado;
	}

	public int getNumeroDe() {
		return numeroDe;
	}

	public void setNumeroDe(int numeroDe) {
		this.numeroDe = numeroDe;
	}

	public int getNumeroAte() {
		return numeroAte;
	}

	public void setNumeroAte(int numeroAte) {
		this.numeroAte = numeroAte;
	}

	public Date getDataCriacaoDe() {
		return dataCriacaoDe;
	}

	public void setDataCriacaoDe(Date dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}

	public Date getDataCriacaoAte() {
		return dataCriacaoAte;
	}

	public void setDataCriacaoAte(Date dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}

	public String getNomeRelator() {
		return nomeRelator;
	}

	public void setNomeRelator(String nomeRelator) {
		this.nomeRelator = nomeRelator;
	}

	public String getNomeAtendente() {
		return nomeAtendente;
	}

	public void setNomeAtendente(String nomeAtendente) {
		this.nomeAtendente = nomeAtendente;
	}

	public TipoCatalogo getTipo() {
		return tipo;
	}

	public void setTipo(TipoCatalogo tipo) {
		this.tipo = tipo;
	}

}
