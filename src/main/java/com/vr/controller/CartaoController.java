package com.vr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr.dto.CartaoDTO;
import com.vr.repository.ICartaoRepository;
import com.vr.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private ICartaoRepository cartaoRepository;

	@PostMapping
	public ResponseEntity<CartaoDTO> cadastrarCartao( @RequestBody @Valid CartaoDTO cartaoDto) {
		
		return cartaoService.salvarCartao(cartaoDto);
	}
	
	@GetMapping(path="/{numeroCartao}")
	public ResponseEntity<String> obterCartao( @PathVariable Long numeroCartao ) {
		 
		return  cartaoRepository.findById(numeroCartao)
		        .map(cartaoConsultado -> ResponseEntity.ok().body( cartaoConsultado.getSaldoFormatado() ))
		        .orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/reset")
	public ResponseEntity<CartaoDTO> resetBase() {
		
		cartaoService.removerTodos();
		return ResponseEntity.noContent().build();
	}
}
