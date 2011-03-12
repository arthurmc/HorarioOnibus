package br.com.onibus.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.onibus.models.Cidade;
import br.com.onibus.models.Horario;
import br.com.onibus.models.Linha;
import br.com.onibus.repositories.CidadeRepository;
import br.com.onibus.repositories.HorarioRepository;
import br.com.onibus.repositories.LinhaRepository;

import com.googlecode.objectify.Key;

@Component
public class LinhaServiceImpl implements LinhaService {
	
	private LinhaRepository linhaRepo;
	private CidadeRepository cidadeRepo;
	private HorarioRepository horarioRepo;

	public LinhaServiceImpl(LinhaRepository linhaRepo, CidadeRepository cidadeRepo, HorarioRepository horarioRepo) {
		this.linhaRepo = linhaRepo;
		this.cidadeRepo = cidadeRepo;
		this.horarioRepo = horarioRepo;
	}
	
	public void criaNovaLinha(Cidade origem, Cidade destino) {
		if (!cidadeRepo.exist(origem) || !cidadeRepo.exist(destino))
			throw new IllegalArgumentException("Cidade de origem ou destino n‹o existe");
		
		if (origem.equals(destino))
			throw new IllegalArgumentException("Cidade de origem n‹o pode ser igual a cidade de destino");
		
		String nomeDaCidadeOrigem = cidadeRepo.find(origem.getId()).getNome();
		String nomeDaCidadeDestino = cidadeRepo.find(destino.getId()).getNome();
		
		Linha linha = new Linha();
		linha.setOrigem(nomeDaCidadeOrigem);
		linha.setDestino(nomeDaCidadeDestino);
		
		if (linha.getOrigem() == null || linha.getDestino()  == null)
			throw new IllegalStateException("Origem ou destino da linha Ž inv‡lido.");
		
		if (linhaRepo.exist(linha))
			throw new IllegalArgumentException("Linha j‡ existente.");
		
		linhaRepo.create(linha);
	}

	@Override
	public List<Linha> findAllLinhas() {
		return linhaRepo.findAll();
	}

	@Override
	public List<Cidade> findAllCidades() {
		return cidadeRepo.findAll();
	}

	@Override
	public void update(Linha linha) {
		linhaRepo.update(linha);
	}

	@Override
	public Linha find(Long id) {
		return linhaRepo.find(id);
	}

	@Override
	public Cidade findByCidadeName(String nomeCidade) {
		return cidadeRepo.findByName(nomeCidade);
	}

	@Override
	public void destroy(Linha paraDestruir) {
		linhaRepo.destroy(paraDestruir);
	}

	@Override
	public List<Horario> recuperaHorariosPelaLinha(Linha linha) {
		List<Key<Horario>> chavesDosHorarios = linha.getHorarios();
		List<Horario> horariosDaLinha = new ArrayList<Horario>();
		
		for (Key<Horario> key : chavesDosHorarios) {
			Horario horario = horarioRepo.find(key.getId());
			horariosDaLinha.add(horario);
		}
		
		return horariosDaLinha;
	}
}