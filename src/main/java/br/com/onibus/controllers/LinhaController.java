package br.com.onibus.controllers;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.onibus.domain.LinhaService;
import br.com.onibus.models.Cidade;
import br.com.onibus.models.Horario;
import br.com.onibus.models.Linha;

@Resource
public class LinhaController {

	private final Result result;
	private final Validator validator;
	private final LinhaService script;
	
	public LinhaController(Result result, Validator validator, LinhaService script) {
		this.result = result;
		this.validator = validator;
		this.script = script;
	}
	
	@Get
	@Path("/linhas")
	public List<Linha> index() {
		return script.findAllLinhas();
	}
	
	@Post
	@Path("/linhas")
	public void create(Cidade origem, Cidade destino) {
		validator.validate(origem);
		validator.validate(destino);
		validator.onErrorUsePageOf(this).newLinha();
		result.on(IllegalArgumentException.class).forwardTo(this).newLinha();

		script.criaNovaLinha(origem, destino);
		
		result.redirectTo(this).index();
	}

	
	@Get
	@Path("/linhas/new")
	public Linha newLinha() {
		result.include("cidadeList", script.findAllCidades());
		return new Linha();
	}
	
	@Put
	@Path("/linhas")
	public void update(Linha linha) {
		validator.validate(linha);
		validator.onErrorUsePageOf(this).edit(linha);
		
		script.update(linha);
		
		result.redirectTo(this).index();
	}
	
	@Get
	@Path("/linhas/{linha.id}/edit")
	public Linha edit(Linha linha) {
		Linha paraEditar = script.find(linha.getId());
		Cidade origem = script.findByCidadeName(paraEditar.getOrigem());
		Cidade destino = script.findByCidadeName(paraEditar.getDestino());
		
		result.include("origem", origem);
		result.include("destino", destino);
		result.include("cidadeList", script.findAllLinhas());
		
		return script.find(linha.getId());
	}

	@Get
	@Path("/linhas/{linha.id}")
	public Linha show(Linha linha) {
		Linha pedida = script.find(linha.getId());
		
		List<Horario> horariosDaLinha = script.recuperaHorariosPelaLinha(pedida);
		result.include("horariosDaLinha", horariosDaLinha);
		
		return pedida;
	}

	@Delete
	@Path("/linhas/{linha.id}")
	public void destroy(Linha linha) {
		script.destroy(script.find(linha.getId()));
		result.redirectTo(this).index();  
	}
}