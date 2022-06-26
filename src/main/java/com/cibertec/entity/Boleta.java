package com.cibertec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_cab_boleta")
@Data
public class Boleta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_bol")
	private int codigo;
	
	@Column(name = "fch_bol")
	private String fec_bol;
	
	@Column(name = "cod_usua")
	private int idusuario;
	
	@ManyToOne
	@JoinColumn(name = "cod_usua", insertable = false, updatable = false)
	private Usuario usuario;
}
