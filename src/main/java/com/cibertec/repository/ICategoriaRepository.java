package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {

}
