package com.cibertec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_usuarios")
@Data
public class Usuario {
	@Id
	@Column(name = "cod_usua")
	private int codigo;
	
	@Column(name = "nom_usua")
	private String nombre;
	
	@Column(name = "ape_usua")
	private String apellidos;
	
	@Column(name = "usr_usua")
	private String username;
	
	@Column(name = "cla_usua")
	private String contrasena;
	
	@Column(name = "fna_usua")
	private String fecNac;
	
	@Column(name = "idtipo")
	private int idtipo;
	
	@ManyToOne
	@JoinColumn(name = "idtipo", insertable = false, updatable = false)
	private Tipo tipo;
	
	@Column(name = "est_usua")
	private int estado;
}
