package com.devsuperioi.dscatalog.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperioi.dscatalog.repositories.ProductRepository;

/*
 * Realizando testes de unidade de um componente de serviço: 
 * Teste de unidades são importante para validar apenas um componente específico sem considerar o carregamento do contexto deste mesmo componente
 * 
 * Para este teste vamos usar o MOCKITO que irá injectar os contextos (componentes ou classes a qual ele depende. Esses componentes são mockados)
 * */
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	private long existingId;
	private long nonExistingId;
	
	@InjectMocks // Usado para o componente a qual estamos querendo testar (validar). Que nem o AutoWired, só que não considera os componentes a qual depende
	private ProductService service;
	
	@Mock // Usado para os contextos do nosso componente de teste
	private ProductRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 50L;
		
		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}
}