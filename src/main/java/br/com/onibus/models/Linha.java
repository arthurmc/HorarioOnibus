package br.com.onibus.models;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;

public class Linha extends Entity {
	
	private String destino;
	private String origem;
	private List<Key<Horario>> horarios;
	
	public void setHorarios(List<Key<Horario>> horarios) {
		this.horarios = horarios;
	}
	
	public List<Key<Horario>> getHorarios() {
		if (horarios == null) {
			horarios = new ArrayList<Key<Horario>>();
		}
		return horarios;
	}
	
	public void addHorario(Horario horario) {
		if (horario.getId() == null) {
			throw new IllegalStateException("Hor‡rio informado n‹o possui id.");
		}
		getHorarios().add(new Key<Horario>(Horario.class, horario.getId()));
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public String getOrigem() {
		return origem;
	}	
}