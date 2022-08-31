package com.vr.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vr.dto.CartaoDTO;
import com.vr.dto.TransacaoDTO;
import com.vr.model.Cartao;
import com.vr.repository.ICartaoRepository;

@Service
public class CartaoService {
	
	@Autowired
	private ICartaoRepository cartaoRepository;
	
	public @Valid ResponseEntity<CartaoDTO> salvarCartao(@Valid CartaoDTO cartaoDto) {
		
		if (cartaoRepository.findById(Long.valueOf(cartaoDto.getNumeroCartao())).isPresent()) {
			return ResponseEntity.unprocessableEntity().body(cartaoDto);
		} else {
			Cartao cartao = cartaoDto.converterDtoParaEntidade();
			cartao.setSaldo(new BigDecimal("500"));
			CartaoDTO cartaoPersistido = CartaoDTO.converterEntidadeParaDto(cartaoRepository.save(cartao));
			return ResponseEntity.ok().body(cartaoPersistido);
		}
	}

	public void removerTodos() {
		cartaoRepository.deleteAll();
	}

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="5000")})
	public ResponseEntity<String> realizarTransacao(@Valid TransacaoDTO transacaoDto) {
		
		if(!cartaoRepository.findById(Long.valueOf(transacaoDto.getNumeroCartao())).isPresent()){
			return ResponseEntity.unprocessableEntity().body("CARTAO_INEXISTENTE");
		}
		
		Cartao cartao = new Cartao(Long.valueOf(transacaoDto.getNumeroCartao()), transacaoDto.getSenhaCartao());
		Example<Cartao> example = Example.of(cartao);
		Optional<Cartao> cartaoConsultado = cartaoRepository.findOne(example);
		
		if (!cartaoConsultado.isPresent()) {
			return ResponseEntity.unprocessableEntity().body("SENHA_INVALIDA");
		}	
		
		BigDecimal valorTransacao = new BigDecimal(transacaoDto.getValor());
		
		if (cartaoConsultado.get().getSaldo().compareTo(valorTransacao) < 0) {
			return ResponseEntity.unprocessableEntity().body("SALDO_INSUFICIENTE");
		}
		
		Cartao entidade = cartaoConsultado.get();
		entidade.setSaldo(entidade.getSaldo().subtract(valorTransacao));
		cartaoRepository.save(entidade);
		
		return  ResponseEntity.ok().body("OK"); 
	}
}
