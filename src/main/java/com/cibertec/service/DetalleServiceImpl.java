package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.DetalleCompra;
import com.cibertec.entity.DetalleCompraKey;
import com.cibertec.repository.DetalleCompraRepository;

@Service
public class DetalleServiceImpl implements ICrud<DetalleCompra, DetalleCompraKey> {
	
	@Autowired
	private DetalleCompraRepository repository;

	@Override
	public List<DetalleCompra> findAll() {
		return repository.findAll();
	}

	@Override
	public DetalleCompra find(DetalleCompraKey id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public DetalleCompra save(DetalleCompra bean) {
		return repository.save(bean);
	}

	@Override
	public void delete(DetalleCompraKey id) {
		repository.deleteById(id);
	}

}
