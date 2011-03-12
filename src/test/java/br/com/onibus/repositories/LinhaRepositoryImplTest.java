package br.com.onibus.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.junit.Test;

import br.com.onibus.TestBase;
import br.com.onibus.models.Cidade;
import br.com.onibus.models.Horario;
import br.com.onibus.models.Linha;

import com.googlecode.objectify.Objectify;

public class LinhaRepositoryImplTest extends TestBase {

    private LinhaRepositoryImpl linhaRepo;
    private CidadeRepositoryImpl cidadeRepo;
    private HorarioRepositoryImpl horarioRepo;
    private Objectify ofy;
    
    @Test
    public void deveAdicionarUmaLinhaComCidadeOrigemEDestinoVinculadas() {
    	Cidade origemValparaiso = new Cidade();
    	origemValparaiso.setNome("Valpara’so");
    	
    	Cidade destinoBrasilia = new Cidade();
    	destinoBrasilia.setNome("Bras’lia");
    	
    	ofy = fact.begin();

    	cidadeRepo = new CidadeRepositoryImpl(ofy);
    	cidadeRepo.create(origemValparaiso);
    	cidadeRepo.create(destinoBrasilia);
    	
    	Linha valparaisoBrasilia = new Linha();
    	valparaisoBrasilia.setOrigem(origemValparaiso.getNome());
    	valparaisoBrasilia.setDestino(destinoBrasilia.getNome());
    	
    	linhaRepo = new LinhaRepositoryImpl(ofy);
    	
    	linhaRepo.create(valparaisoBrasilia);
    	
    	Linha valBsb = linhaRepo.find(valparaisoBrasilia.getId());

    	Cidade val = cidadeRepo.findByName(valBsb.getOrigem());
    	Cidade bsb = cidadeRepo.findByName(valBsb.getDestino());
    	
    	Assert.assertEquals("Valpara’so", val.getNome());
    	Assert.assertEquals("Bras’lia", bsb.getNome());
	}

    @Test
    public void deveAdicionarUmaLinhaComVariosHorariosVinculados() {
    	Cidade origem = new Cidade();
    	origem.setNome("Itapemirim");
    	
    	Cidade destino = new Cidade();
    	destino.setNome("Fortaleza");
    	
    	ofy = fact.begin();
    	
    	cidadeRepo = new CidadeRepositoryImpl(ofy);
    	cidadeRepo.create(origem);
    	cidadeRepo.create(destino);
    	
    	Horario saida1 = new Horario();
    	Horario saida2 = new Horario();
    	
    	try {
    		saida1.setHora(new SimpleDateFormat("kk:mm").parse("11:00"));
			saida2.setHora(new SimpleDateFormat("kk:mm").parse("12:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	horarioRepo = new HorarioRepositoryImpl(ofy);
    	horarioRepo.create(saida1);
    	horarioRepo.create(saida2);
    	
    	Linha itapemirimFortaleza = new Linha();
    	itapemirimFortaleza.setOrigem(origem.getNome());
    	itapemirimFortaleza.setDestino(destino.getNome());
    	itapemirimFortaleza.addHorario(saida1);
    	itapemirimFortaleza.addHorario(saida2);
    	
    	linhaRepo = new LinhaRepositoryImpl(ofy);
    	linhaRepo.create(itapemirimFortaleza);
    	
    	Linha itapFortal = linhaRepo.find(itapemirimFortaleza.getId());
    	
    	Assert.assertEquals(itapemirimFortaleza.getHorarios().size(), itapFortal.getHorarios().size());
	}
    
}