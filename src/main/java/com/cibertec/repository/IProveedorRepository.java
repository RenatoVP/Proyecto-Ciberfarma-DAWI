package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.Proveedor;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor, Integer> {

}
