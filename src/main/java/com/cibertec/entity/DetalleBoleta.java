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
@Table(name = "tb_det_boleta")
@Data
public class DetalleBoleta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "num_bol")
	private int nroboleta;
	
	@ManyToOne
	@JoinColumn(name = "num_bol", insertable = false, updatable = false)
	private Boleta boleta;
	
	@Column(name = "idprod")
	private String idprod;
	
	@ManyToOne
	@JoinColumn(name = "idprod", insertable = false, updatable = false)
	private Producto producto;
	
	private int cantidad;
	private double preciovta;
}
