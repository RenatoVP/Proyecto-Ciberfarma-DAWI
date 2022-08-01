package com.cibertec.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_productos")
@Data
public class Producto {
	@Id
	@Column(name = "id_prod")
	private String id;
	
	@Column(name = "des_prod")
	private String descripcion;
	
	@Column(name = "stk_prod")
	private Integer stock;
	
	@Column(name = "pre_prod")
	private Double precio;
	
	@Column(name = "img_prod")
	private String imagen;
	
	@OneToOne
	@JoinColumn(name = "idcategoria")
	private Categoria categoria;
	
	@Column(name = "est_prod")
	private Integer estado;
	
	@OneToOne
	@JoinColumn(name = "idprovedor")
	private Proveedor proveedor;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<DetalleCompra> detallecompras;
	
	public void quitar(int cantidad) {
		this.stock -= cantidad;
	}
	
}
