package com.cibertec.entity;

import lombok.Data;

@Data
public class BoletaProducto {
	private int item;
	private String idproducto;
	private String producto;
	private String categoria;
	private double preciounitario;
	private int cantidad;
	private double subtotal;
}
