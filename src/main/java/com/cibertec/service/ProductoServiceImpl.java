package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Producto;
import com.cibertec.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements ICrud<Producto, String>{
	
	@Autowired
	private IProductoRepository repository;

	@Override
	public List<Producto> findAll() {
		return repository.findAll();
	}
	
	public Page<Producto> findAllPagination(Pageable page){
		return repository.findAll(page);
	}

	@Override
	public Producto find(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Producto> findByExample(Example<Producto> example){
		return repository.findAll(example);
	}

	@Override
	public Producto save(Producto bean) {
		return repository.save(bean);
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

}
