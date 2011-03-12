package br.com.onibus.repositories;

import com.googlecode.objectify.Objectify;

import br.com.caelum.vraptor.ioc.Component;
import br.com.onibus.models.Cidade;

@Component
public class CidadeRepositoryImpl extends Repository<Cidade, Long> implements CidadeRepository {

	public CidadeRepositoryImpl(Objectify objecitify) {
		super(objecitify);
	}
	
	@Override
	public void create(Cidade entity) {
		Cidade cidadeExistente = findByName(entity.getNome());
		if (cidadeExistente != null) {
			throw new IllegalArgumentException("Cidade j‡ existente");
		}
		objectify.put(entity);
	}

	@Override
	public Cidade findByName(String name) {
		return objectify.query(Cidade.class).filter("nome", name).get();
	}

	@Override
	public boolean exist(Cidade entity) {
		if (entity != null && entity.getId() != null)
			return objectify.find(Cidade.class, entity.getId()) != null ? true : false;
		
		if (entity != null && entity.getNome() != null)
			return findByName(entity.getNome()) != null ? true : false;
		
		return false;
	}
}
