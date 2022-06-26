package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, String> {
	
	public List<Producto> findAllByIdcategoria(int idcategoria);
	
	@Query("select p from Producto p where p.descripcion like %?1%")
	public List<Producto> findAllByProductName(String descripcion);
	
	@Query("select p from Producto p where p.idcategoria = ?1 and p.precio between ?2 and ?3")
	public List<Producto> findAllByCategoryPriceBetween(int idcategoria, double price1, double price2);
	
	public List<Producto> findAllByPrecioBetween(double price1, double price2);
}
