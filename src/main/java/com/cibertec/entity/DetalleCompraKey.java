package com.cibertec.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class DetalleCompraKey implements Serializable {
	@Column(name = "num_compra")
	private Integer numcompra;
	
	@Column(name = "id_prod")
	private String idproducto;
}
