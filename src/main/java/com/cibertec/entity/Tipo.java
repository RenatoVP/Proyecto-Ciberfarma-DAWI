package com.cibertec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_tipos")
@Data
public class Tipo {
	@Id
	@Column(name = "idtipo")
	private Integer id;
	private String descripcion;
}
