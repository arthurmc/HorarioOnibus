package br.com.onibus.controllers;

import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.onibus.domain.LinhaService;
import br.com.onibus.models.Cidade;

public class LinhaControllerTest {

	private static MockResult result;
	private static Validator validator;
	private static LinhaController controller;
	private static LinhaService script;
	
	@BeforeClass
	public static void inicia() {
		result = new MockResult() {
			public Result on(java.lang.Class<? extends Exception> exception) {
				return this;
			};
		};
		
		validator = new MockValidator();
		script = Mockito.mock(LinhaService.class);
		controller = new LinhaController(result, validator, script);
	}
	
	@Test
	public void newLinhaDeveTerUmaListaDeCidades() {
		controller.newLinha();
		List<Cidade> cidadeList = result.included("cidadeList");
		Assert.assertNotNull(cidadeList);
	}

//	@Test
//	public void deveEditarLinha() {
//		Linha linha = new Linha();
//		
//		Mockito.when(repoLinha.find(linha.getId())).thenReturn(true);
//		Mockito.when(repoCidade.exist(destino)).thenReturn(true);
//		controller.edit();
//		Map<String, Object> resultMap = result.included();
//		Assert.assertNotNull(resultMap.get("cidadeList"));
//		
//	}
	
	@AfterClass
	public static void termina() {
		result = null;
		validator = null;
		controller = null;
	}
}