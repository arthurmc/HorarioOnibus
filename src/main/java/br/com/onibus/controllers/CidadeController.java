package br.com.onibus.controllers;

import java.util.List;

import br.com.onibus.models.Cidade;
import br.com.onibus.repositories.CidadeRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class CidadeController {

	private final Result result;
	private final CidadeRepository repository;
	private final Validator validator;
	
	public CidadeController(Result result, CidadeRepository repository, Validator validator) {
		this.result = result;
		this.repository = repository;
		this.validator = validator;
	}
	
	@Get
	@Path("/cidades")
	public List<Cidade> index() {
		return repository.findAll();
	}
	
	@Post
	@Path("/cidades")
	public void create(Cidade cidade) {
		validator.validate(cidade);
		validator.onErrorUsePageOf(this).newCidade();
		repository.create(cidade);
		result.redirectTo(this).index();
	}
	
	@Get
	@Path("/cidades/new")
	public Cidade newCidade() {
		return new Cidade();
	}
	
	@Put
	@Path("/cidades")
	public void update(Cidade cidade) {
		validator.validate(cidade);
		validator.onErrorUsePageOf(this).edit(cidade);
		repository.update(cidade);
		result.redirectTo(this).index();
	}
	
	@Get
	@Path("/cidades/{cidade.id}/edit")
	public Cidade edit(Cidade cidade) {
		return repository.find(cidade.getId());
	}

	@Get
	@Path("/cidades/{cidade.id}")
	public Cidade show(Cidade cidade) {
		return repository.find(cidade.getId());
	}

	@Delete
	@Path("/cidades/{cidade.id}")
	public void destroy(Cidade cidade) {
		repository.destroy(repository.find(cidade.getId()));
		result.redirectTo(this).index();  
	}
	
}
