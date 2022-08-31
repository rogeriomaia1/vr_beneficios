package com.vr;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr.controller.CartaoController;
import com.vr.dto.CartaoDTO;
import com.vr.repository.ICartaoRepository;
import com.vr.service.CartaoService;


class IntegracaoCartaoControllerTest extends VrBeneficiosApplicationTests{
	
	private static final Long ID = 1L;
	private static final String SENHA = "1234";
	private CartaoDTO cartaoDto;
	private MockMvc mockMvc;
	
	@Autowired
	private CartaoController cartaoController;

	@Mock
	private ICartaoRepository cartaoRepository;
	
	@Mock
	private CartaoService cartaoService;
	
	@BeforeEach
	void preConfiguracoes() throws Exception {
		 MockitoAnnotations.openMocks(this);
		 this.mockMvc = MockMvcBuilders.standaloneSetup(this.cartaoController).build();
		 this.inicializarObjetos();
	}

	@AfterEach
	public void tearDown() throws SQLException {
		limparBase();
	}
	
	private void limparBase() {
		cartaoController.resetBase();
	}

	@Test
	void testeCadastrarCartaoComSucesso() throws Exception {
		
		ObjectMapper mapper =  new ObjectMapper();
		String corpoRequisicaoJson = mapper.writeValueAsString(this.cartaoDto);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/cartoes")
												   .contentType(MediaType.APPLICATION_JSON)
												   .content(corpoRequisicaoJson)
							).andExpect(MockMvcResultMatchers.status().isOk())
							;
	}
	
	private void inicializarObjetos() {
		this.cartaoDto = new CartaoDTO(ID.toString(), SENHA);
	}
}
