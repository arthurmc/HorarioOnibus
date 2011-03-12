package br.com.onibus.repositories;

import br.com.caelum.vraptor.ioc.Component;
import br.com.onibus.models.Horario;

import com.googlecode.objectify.Objectify;

@Component
public class HorarioRepositoryImpl extends Repository<Horario, Long> implements HorarioRepository {

	public HorarioRepositoryImpl(Objectify objecitify) {
		super(objecitify);
	}
}
