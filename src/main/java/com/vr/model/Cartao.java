package com.vr.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cartao")
public class Cartao {
	
	@Id
	private Long numeroCartao;
	
	@Column(nullable = false)
	private String senha;
	
	@Column
	private BigDecimal saldo;
	
	public Cartao() {}
	
	public Cartao( Long numeroCartao,  String senha) {
		
		this.numeroCartao = numeroCartao;
		this.senha = senha;
	}

	public Cartao(Long numeroCartao, String senha, BigDecimal novoSaldo) {
		this.numeroCartao = numeroCartao;
		this.senha = senha;
		this.saldo = novoSaldo;
	}

	public Long getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getSaldoFormatado() {
		return this.getSaldo().setScale(2).toString();
	}

}
