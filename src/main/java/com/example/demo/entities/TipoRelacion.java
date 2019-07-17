package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_relacion")
public class TipoRelacion 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_tipo_relacion;
	@Column(name ="tipo_relacion", unique=true)
	private String tipo_relacion;


	public TipoRelacion(long id_tipo_relacion, String tipo_relacion) 
	{
		this.id_tipo_relacion = id_tipo_relacion;
		this.tipo_relacion = tipo_relacion;
	}
	
	public TipoRelacion() 
	{
		
	}

	public long getId_relacion() {
		return id_tipo_relacion;
	}

	public void setId_relacion(long id_relacion) {
		this.id_tipo_relacion = id_relacion;
	}

	public String getRelacion() {
		return tipo_relacion;
	}

	public void setRelacion(String relacion) {
		this.tipo_relacion = relacion;
	}
	
	
	
	
}


