package br.com.onibus.repositories;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import br.com.onibus.TestBase;
import br.com.onibus.models.Horario;

import com.googlecode.objectify.Objectify;

public class HorarioRepositoryImplTest extends TestBase {

	HorarioRepository repoHorario;
	Objectify ofy;

	@Test
	public void deveAdicionarHorario() {
		Horario saida = new Horario();
		saida.setHora(new Date());
		
		ofy = fact.begin();
		repoHorario = new HorarioRepositoryImpl(ofy);
		repoHorario.create(saida);
		
		Assert.assertNotNull(repoHorario.find(saida.getId()));
		Assert.assertEquals(saida.getHora(), repoHorario.find(saida.getId()).getHora());
	}
}