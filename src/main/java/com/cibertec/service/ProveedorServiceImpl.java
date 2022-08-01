package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Proveedor;
import com.cibertec.repository.IProveedorRepository;

@Service
public class ProveedorServiceImpl implements ICrud<Proveedor, Integer>{
	
	@Autowired
	private IProveedorRepository repository;

	@Override
	public List<Proveedor> findAll() {
		return repository.findAll();
	}
	
	public Page<Proveedor> findAllPagination(Pageable page){
		return repository.findAll(page);
	}

	@Override
	public Proveedor find(Integer id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Proveedor save(Proveedor bean) {
		return repository.save(bean);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
