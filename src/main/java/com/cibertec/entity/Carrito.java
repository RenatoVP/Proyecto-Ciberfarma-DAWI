package com.cibertec.entity;

public class Carrito extends Producto {
	private Integer cantidad;

	public Carrito() {
		super();
	}
	
	public void agregaCantidad(Integer cantidad) {
		this.cantidad += cantidad;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getTotal() {
		return this.getPrecio() * this.cantidad;
	}
	
	
}
