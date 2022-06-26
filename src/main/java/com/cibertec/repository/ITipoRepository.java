package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Tipo;

public interface ITipoRepository extends JpaRepository<Tipo, Integer> {
	
}
