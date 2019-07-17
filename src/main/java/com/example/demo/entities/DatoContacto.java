package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dato_contacto")
public class DatoContacto 
{
	private enum tipo_dato_contacto {
		TELEFONO, CELULAR, EMAIL
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_contacto;
	@Column(name = "tipo_dato_contacto")
	private tipo_dato_contacto tipo_dato_contacto;
	@Column(name = "valor_dato_contacto")
	private String valor_dato_contacto;
	
	@ManyToOne
    @JoinColumn(name = "id_persona")
	@JsonIgnore
    private Persona persona;
	
	public DatoContacto() 
	{

	}
	
	
	public DatoContacto(long id_contacto, com.example.demo.entities.DatoContacto.tipo_dato_contacto tipo_dato_contacto,
			String valor_dato_contacto) 
	{
		super();
		this.id_contacto = id_contacto;
		this.tipo_dato_contacto = tipo_dato_contacto;
		this.valor_dato_contacto = valor_dato_contacto;
	}

	public long getId_contacto() {
		return id_contacto;
	}


	public void setId_contacto(long id_contacto) {
		this.id_contacto = id_contacto;
	}
	
	public Persona getPersona()
	{
		return persona;
	}
	
	public void setPersona(Persona p)
	{
		this.persona = p;
	}

	public tipo_dato_contacto getTipo_dato_contacto() {
		return tipo_dato_contacto;
	}


	public void setTipo_dato_contacto(tipo_dato_contacto tipo_dato_contacto) {
		this.tipo_dato_contacto = tipo_dato_contacto;
	}


	public String getValor_dato_contacto() {
		return valor_dato_contacto;
	}


	public void setValor_dato_contacto(String valor_dato_contacto) {
		this.valor_dato_contacto = valor_dato_contacto;
	}
	
	@Override
    public String toString() 
	{
        return "DatoContacto{" +
                "id=" + id_contacto +
                ", tipo_dato_contacto='" + tipo_dato_contacto + '\'' +
                ", dato='" + valor_dato_contacto + '\'' +
                '}';
	}
	
}
