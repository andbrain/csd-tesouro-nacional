package com.csd.tesouronacional.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Titulo {
	private long id;
	private String letra;
	private String numero;
	private Date vencimento;
	private double taxaCompra;
	private double precoCompra;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getFormattedVencimento(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return df.format(getVencimento());
	}
	
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public double getTaxaCompra() {
		return taxaCompra;
	}
	public void setTaxaCompra(double taxaCompra) {
		this.taxaCompra = taxaCompra;
	}
	public double getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}
}
