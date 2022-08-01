package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Categoria;
import com.cibertec.repository.ICategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICrud<Categoria, Integer>{
	
	@Autowired
	private ICategoriaRepository repository;
	
	@Override
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	public Page<Categoria> findAllPagination(Pageable page){
		return repository.findAll(page);
	}

	@Override
	public Categoria find(Integer id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Categoria save(Categoria bean) {
		return repository.save(bean);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
