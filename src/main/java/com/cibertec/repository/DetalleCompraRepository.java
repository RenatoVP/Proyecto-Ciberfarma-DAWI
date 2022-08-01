package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.DetalleCompra;
import com.cibertec.entity.DetalleCompraKey;

@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, DetalleCompraKey>{

}
