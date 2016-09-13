package com.proitc.dao;

import java.util.List;

public interface GenericDao<T, K> {
	public abstract T findById(K id);

	public abstract List<T> findAll();

	public abstract void save(T t);

	public abstract void update(T t);

	public abstract void delete(T t);
}
