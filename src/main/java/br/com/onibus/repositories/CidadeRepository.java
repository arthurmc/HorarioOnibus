package br.com.onibus.repositories;

import java.util.List;

import br.com.onibus.models.Cidade;

public interface CidadeRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Cidade entity);
	
	void update(Cidade entity);
	
	void destroy(Cidade entity);
	
	Cidade find(Long id);
	
	Cidade findByName(String name);
	
	boolean exist(Cidade entity);
	
	List<Cidade> findAll();

}
