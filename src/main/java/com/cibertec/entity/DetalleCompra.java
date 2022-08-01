package com.cibertec.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "tb_det_compra")
@Data
public class DetalleCompra implements Serializable{
	
	@EmbeddedId
	private DetalleCompraKey id = new DetalleCompraKey();
	
	@ManyToOne
	@MapsId("numcompra")
	@JoinColumn(name = "num_compra")
	private Compra compra;
	
	@ManyToOne
	@MapsId("idproducto")
	@JoinColumn(name = "id_prod")
	private Producto producto;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column(name = "preciovta")
	private Double precioventa;
}
