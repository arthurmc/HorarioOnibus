package br.com.onibus.domain;

import java.util.List;

import br.com.onibus.models.Cidade;
import br.com.onibus.models.Horario;
import br.com.onibus.models.Linha;

public interface LinhaService {
	
	public void criaNovaLinha(Cidade origem, Cidade destino);
	
	public List<Linha> findAllLinhas();
	
	public List<Cidade> findAllCidades();
	
	public void update(Linha linha);

	public Linha find(Long id);

	public Cidade findByCidadeName(String origem);

	public void destroy(Linha find);

	public List<Horario> recuperaHorariosPelaLinha(Linha linha);

}