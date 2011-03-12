package br.com.onibus.repositories;

import java.util.List;

import br.com.onibus.models.Horario;

public interface HorarioRepository {
	 
	void create(Horario entity);
	
	void update(Horario entity);
	
	void destroy(Horario entity);
	
	Horario find(Long id);
	
	List<Horario> findAll();

}
