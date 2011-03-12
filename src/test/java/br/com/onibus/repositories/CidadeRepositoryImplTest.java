package br.com.onibus.repositories;

import junit.framework.Assert;

import org.junit.Test;

import br.com.onibus.TestBase;
import br.com.onibus.models.Cidade;

import com.googlecode.objectify.Objectify;

public class CidadeRepositoryImplTest extends TestBase {

	CidadeRepository repoCidade;
	Objectify ofy;

	@Test
	public void deveBuscarCidadePeloSeuNome() {
		ofy = fact.begin();
		repoCidade = new CidadeRepositoryImpl(ofy);
		
		Cidade ocidental = new Cidade();
		ocidental.setNome("Ocidental");
		
		repoCidade.create(ocidental);
		
		Assert.assertEquals("Ocidental", repoCidade.findByName("Ocidental").getNome());
	}
	
	@Test(expected=IllegalArgumentException.class)
    public void naoDeveAdicionarCidadesComNomesIguais() {
		Cidade rio = new Cidade();
		rio.setNome("Rio de Janeiro");
		
		Cidade rj = new Cidade();
		rj.setNome("Rio de Janeiro");
		
		ofy = fact.begin();
		repoCidade = new CidadeRepositoryImpl(ofy);
		repoCidade.create(rio);
		repoCidade.create(rj);
	}
	
	@Test
	public void deveAssegurarQueACidadeExistePeloNome() {
		Cidade saoPaulo = new Cidade();
		saoPaulo.setNome("S‹o Paulo");
		
		ofy = fact.begin();
		repoCidade = new CidadeRepositoryImpl(ofy);
		repoCidade.create(saoPaulo);

		Cidade sp = new Cidade();
		sp.setNome("S‹o Paulo");
		
		Assert.assertTrue(repoCidade.exist(sp));
	}

	@Test
	public void deveAssegurarQueACidadeExistePeloId() {
		Cidade joaoPessoa = new Cidade();
		joaoPessoa.setNome("Jo‹o Pessoa");
		
		ofy = fact.begin();
		repoCidade = new CidadeRepositoryImpl(ofy);
		repoCidade.create(joaoPessoa);
		
		Cidade jp = new Cidade();
		jp.setId(joaoPessoa.getId());
		
		Assert.assertTrue(repoCidade.exist(jp));
	}
}