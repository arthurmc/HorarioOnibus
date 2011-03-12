package br.com.onibus.repositories;

import br.com.caelum.vraptor.ioc.Component;
import br.com.onibus.models.Linha;

import com.googlecode.objectify.Objectify;

@Component
public class LinhaRepositoryImpl extends Repository<Linha, Long> implements LinhaRepository {

	public LinhaRepositoryImpl(Objectify objectify) {
		super(objectify);
	}
	
	@Override
	public void create(Linha entity) {
    	objectify.put(entity);
	}

	@Override
	public boolean exist(Linha entity) {
		if (objectify.query(Linha.class).filter("origem", entity.getOrigem()).filter("destino", entity.getDestino()).get() != null)
			return true;
		
		return false;
	}
}
