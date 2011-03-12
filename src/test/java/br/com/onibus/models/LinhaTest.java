package br.com.onibus.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.junit.Test;

public class LinhaTest {

    @Test
    public void deveAdicionarHorarioALinha() {
    	Linha cearaMaranhao = new Linha();
    	int numeroDeHorarios = cearaMaranhao.getHorarios().size();
    	
    	Horario saida = new Horario();
    	saida.setId(Long.valueOf(0));

    	try {
			saida.setHora(new SimpleDateFormat("kk:mm").parse("08:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		cearaMaranhao.addHorario(saida);
		
		Assert.assertEquals(numeroDeHorarios + 1, cearaMaranhao.getHorarios().size());
  	}
    
    @Test(expected=IllegalStateException.class)
    public void deveImpedirAdicaoDeHorarioCasoEleNaoTenhaId() {
    	Linha cearaMaranhao = new Linha();
    	int numeroDeHorarios = cearaMaranhao.getHorarios().size();
    	
    	Horario saida = new Horario();
    	
    	try {
    		saida.setHora(new SimpleDateFormat("kk:mm").parse("08:00"));
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	
    	cearaMaranhao.addHorario(saida);
    	
    	Assert.assertEquals(numeroDeHorarios + 1, cearaMaranhao.getHorarios().size());
    }
}

