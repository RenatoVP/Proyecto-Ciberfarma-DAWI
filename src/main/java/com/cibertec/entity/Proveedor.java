package com.cibertec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_proveedor")
@Data
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idprovedor")
	private Integer id;
	
	@Column(name = "nombre_rs")
	private String nombre;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "email")
	private String email;
}
