package com.cibertec.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_compra")
@Data
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_compra")
	private Integer numcompra;
	
	@Column(name = "fch_compra")
	private Date fecha;
	
	@OneToOne
	@JoinColumn(name = "cod_usua")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
	private List<DetalleCompra> detallecompras;
}
