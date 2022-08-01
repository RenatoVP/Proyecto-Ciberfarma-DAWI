package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Compra;
import com.cibertec.repository.CompraRepository;

@Service
public class CompraServiceImpl implements ICrud<Compra, Integer> {
	
	@Autowired
	private CompraRepository repository;

	@Override
	public List<Compra> findAll() {
		return repository.findAll();
	}

	@Override
	public Compra find(Integer id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Compra save(Compra bean) {
		return repository.save(bean);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
