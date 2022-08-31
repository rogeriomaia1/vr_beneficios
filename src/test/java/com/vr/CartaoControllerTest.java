package com.vr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vr.controller.CartaoController;
import com.vr.dto.CartaoDTO;
import com.vr.model.Cartao;
import com.vr.repository.ICartaoRepository;
import com.vr.service.CartaoService;



class CartaoControllerTest extends VrBeneficiosApplicationTests{
	
	private static final Long ID = 1L;
	private static final String SENHA = "1234";

	private Cartao cartao;
	private CartaoDTO cartaoDto;
	private ResponseEntity<CartaoDTO> respostaFalha;
	private ResponseEntity<CartaoDTO> respostaSucesso;
	
	@InjectMocks
	private CartaoController cartaoController;

	@Mock
	private ICartaoRepository cartaoRepository;
	
	@Mock
	private CartaoService cartaoService;
	
	@BeforeEach
	void preConfiguracoes() throws Exception {
		 MockitoAnnotations.openMocks(this);
		 this.inicializarObjetos();
	}
	
	@Test
	void testeCadastrarCartaoSemSucesso() {
		when(cartaoService.salvarCartao(this.cartaoDto)).thenReturn(this.respostaFalha);
		
		assertEquals(this.respostaFalha.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
		assertEquals(this.respostaSucesso.getBody().getNumeroCartao(), ID.toString());
	}

	@Test
	void testeObterCartaoValido() {
		
		  when(cartaoRepository.findById(ID)).thenReturn(Optional.of(this.cartao));
 
	      ResponseEntity<String> response = cartaoController.obterCartao(ID);
	      
	      Assertions.assertNotNull(response);
	      Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	      Assertions.assertEquals(ResponseEntity.class, response.getClass());
	      Assertions.assertNotNull(response.getBody());
	}

	@Test
	void testeObterCartaoInvalido() {
		
		when(cartaoRepository.findById(2L)).thenReturn(Optional.empty());
		
		ResponseEntity<String> response = cartaoController.obterCartao(ID);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	private void inicializarObjetos() {
		this.cartao = new Cartao(ID, SENHA, new BigDecimal("500"));
		this.cartaoDto = new CartaoDTO(ID.toString(), SENHA);
		this.respostaFalha = ResponseEntity.unprocessableEntity().body(cartaoDto);
		this.respostaSucesso = ResponseEntity.ok().body(cartaoDto);;
	}
}
