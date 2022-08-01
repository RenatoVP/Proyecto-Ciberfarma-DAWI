package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Usuario;
import com.cibertec.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements ICrud<Usuario, Integer>{
	
	@Autowired
	private IUsuarioRepository repository;

	@Override
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Page<Usuario> findAllPagination(Pageable page){
		return repository.findAll(page);
	}

	@Override
	public Usuario find(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public Usuario findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public Usuario save(Usuario bean) {
		return repository.save(bean);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
