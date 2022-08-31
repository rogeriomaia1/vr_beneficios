package com.vr.dto;

import javax.validation.constraints.NotBlank;

public class TransacaoDTO {

	@NotBlank(message = "{numero.cartao.nao.vazio}")
	private String numeroCartao;
	
	@NotBlank(message = "{senha.nao.vazio}")
	private String senhaCartao;
	
	@NotBlank(message = "{valor.nao.vazio}")
	private String valor;
	
	public TransacaoDTO() {
	}
	
	public TransacaoDTO(@NotBlank(message = "{numero.cartao.not.blank}") String numeroCartao,
			@NotBlank(message = "{senha.not.blank}") String senhaCartao, @NotBlank(message = "{valor.not.blank}") String valor) {
		super();
		this.numeroCartao = numeroCartao;
		this.senhaCartao = senhaCartao;
		this.valor = valor;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	public String getSenhaCartao() {
		return senhaCartao;
	}

	public void setSenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
