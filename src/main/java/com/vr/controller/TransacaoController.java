package com.vr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr.dto.TransacaoDTO;
import com.vr.service.CartaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	@Autowired
	private CartaoService cartaoService;

	@PostMapping
	public ResponseEntity<String> realizaTransacao( @RequestBody @Valid TransacaoDTO transacaoDto) {
		
		return cartaoService.realizarTransacao(transacaoDto);
	}
	
}
