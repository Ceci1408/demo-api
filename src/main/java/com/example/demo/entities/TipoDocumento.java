package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tipo_documento")
public class TipoDocumento 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_tipo_documento;
	@Column(name = "tipo_documento", unique=true)
	private String tipo_documento;
	@OneToMany(fetch=FetchType.LAZY,
	        mappedBy = "per_id_tipo_doc",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )	
	//@OneToMany(mappedBy="per_id_tipo_doc")
	private List<Persona> personas = new ArrayList<>();
	
	public TipoDocumento()
	{
		
	}


	public int getId_tipo_documento() {
		return id_tipo_documento;
	}


	public void setId_tipo_documento(int id_tipo_documento) {
		this.id_tipo_documento = id_tipo_documento;
	}


	public String getTipo_documento() {
		return tipo_documento;
	}


	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	
	public void addPersona(Persona p)
	{
		this.personas.add(p);
		p.setPer_id_tipo_doc(this);
	}
	
	public void removePersona(Persona p)
	{
		this.personas.remove(p);
		p.setPer_id_tipo_doc(null);
	}
	
}
