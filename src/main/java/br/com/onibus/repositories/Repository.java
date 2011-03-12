package br.com.onibus.repositories;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.com.onibus.models.Cidade;
import br.com.onibus.models.Linha;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class Repository<T, I extends Serializable> {
	
	protected final Objectify objectify;
	
	static {
		ObjectifyService.register(Linha.class);
		ObjectifyService.register(Cidade.class);
	}
	
	public Repository(Objectify objectify) {
		this.objectify = objectify;
	}
	
	public void create(T entity) {
		objectify.put(entity);
	}
	
	public void update(T entity) {
		objectify.put(entity);
	}
	
	public void destroy(T entity) {
		objectify.delete(entity);
	}
	
	public T find(I id) {
		return objectify.get(getParameterizedClass(), Long.parseLong("" + id));
	}
	
	public List<T> findAll() {
		return objectify.query(getParameterizedClass()).list();
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getParameterizedClass() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
}
