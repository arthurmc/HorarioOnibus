package br.com.onibus.repositories;

import java.util.List;

import br.com.onibus.models.Linha;

public interface LinhaRepository {

	void create(Linha entity);
	
	void update(Linha entity);
	
	void destroy(Linha entity);
	
	Linha find(Long id);
	
	List<Linha> findAll();
	
	boolean exist(Linha entity);

}
