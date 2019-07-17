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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "pais")
public class Pais 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_pais;
	@Column(name = "pais", unique=true)
	private String pais;
	@OneToMany(fetch=FetchType.LAZY,
	        mappedBy = "per_id_pais",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Persona> personas = new ArrayList<>();
	
	public Pais() {
		super();
	}
	
	

	public Pais(long id, String pais) {
		super();
		this.id_pais = id;
		this.pais = pais;
	}

	public long getId() {
		return id_pais;
	}
	public void setId(long id) {
		this.id_pais = id;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public void addPersona(Persona p)
	{
		this.personas.add(p);
		p.setPer_id_pais(this);
	}
	
	public void removePersona(Persona p)
	{
		this.personas.remove(p);
		p.setPer_id_pais(null);
	}
	 
}
