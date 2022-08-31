package com.vr.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.vr.model.Cartao;

public class CartaoDTO {

	@NotBlank(message = "{numero.cartao.nao.vazio}")
	private String numeroCartao;
	
	@NotBlank(message = "{senha.nao.vazio}")
	private String senha;

	public CartaoDTO() {
	}
	
	public CartaoDTO(@NotBlank(message = "{numero.cartao.not.blank}") String numeroCartao,
			@NotBlank(message = "{senha.not.blank}") String senha) {
		super();
		this.numeroCartao = numeroCartao;
		this.senha = senha;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public static @Valid CartaoDTO converterEntidadeParaDto(Cartao entidade) {
		
		CartaoDTO dto = new CartaoDTO(entidade.getNumeroCartao().toString(), entidade.getSenha());
		return dto;
	}

	public Cartao converterDtoParaEntidade() {
		Cartao entidade = new Cartao(Long.valueOf(this.numeroCartao), this.senha);
		return entidade;
	}
}
