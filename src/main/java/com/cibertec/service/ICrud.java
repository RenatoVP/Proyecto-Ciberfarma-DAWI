package com.cibertec.service;

import java.util.List;

public interface ICrud<T, primaryKey> {
	public List<T> findAll();
	
	public T find(primaryKey id);
	
	public T save(T bean);
	
	public void delete(primaryKey id);
}
