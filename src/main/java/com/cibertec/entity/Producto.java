package com.cibertec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_productos")
@Data
public class Producto {
	@Id
	@Column(name = "idprod")
	private String id;
	private String descripcion;
	private int stock;
	private double precio;
	private int idcategoria;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria", insertable = false, updatable = false)
	private Categoria categoria;
	private int estado;
	
}
