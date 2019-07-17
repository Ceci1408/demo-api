package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
		name = "persona_relacion",
		uniqueConstraints={
					@UniqueConstraint(columnNames={"id_persona_1", "id_persona_2", "id_tipo_relacion"})
					}
	)
public class PersonaRelacion 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_relacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona_1")
	private Persona id_persona_1;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona_2")
	private Persona id_persona_2;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_relacion")
	private TipoRelacion tipo_relacion;
	
	public PersonaRelacion(long id_relacion, Persona id_persona_1, Persona id_persona_2, TipoRelacion tipo_relacion) 
	{
		this.id_relacion = id_relacion;
		this.id_persona_1 = id_persona_1;
		this.id_persona_2 = id_persona_2;
		this.tipo_relacion = tipo_relacion;
	}
	
	public PersonaRelacion() {
		
	}

	public long getId_relacion() {
		return id_relacion;
	}

	public void setId_relacion(long id_relacion) {
		this.id_relacion = id_relacion;
	}

	public Persona getId_persona_1() {
		return id_persona_1;
	}

	public void setId_persona_1(Persona id_persona_1) {
		this.id_persona_1 = id_persona_1;
	}

	public Persona getId_persona_2() {
		return id_persona_2;
	}

	public void setId_persona_2(Persona id_persona_2) {
		this.id_persona_2 = id_persona_2;
	}

	public TipoRelacion getTipo_relacion() {
		return tipo_relacion;
	}

	public void setTipo_relacion(TipoRelacion tipo_relacion) {
		this.tipo_relacion = tipo_relacion;
	}
	
	
	
	
}
