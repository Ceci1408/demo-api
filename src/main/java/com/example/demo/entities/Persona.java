package com.example.demo.entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.hateoas.ResourceSupport;

@Entity
@Table(
		name = "persona",
		uniqueConstraints={
					@UniqueConstraint(columnNames={"per_id_pais", "per_id_tipo_doc", "per_nro_documento", "genero"}) 
					}
	)
public class Persona extends ResourceSupport
{
	// Al ser solamente dos opciones, no considero crear una Clase/Tabla paramétrica.
	public enum Sexo { 
		MASCULINO, FEMENINO
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_persona;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_id_pais")
	private Pais per_id_pais;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_id_tipo_doc")
	//@JsonIgnore
	private TipoDocumento per_id_tipo_doc;
	@Column(name = "per_nro_documento")
	private String per_nro_documento;
	@Column(name = "genero", nullable=false)
	private Sexo genero;
	// Para la edad, prefiero la fecha de nacimiento 
	@Column(name = "per_fec_nacimiento")
	private Date fecha_nacimiento;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="id_persona_1")
    private List<PersonaRelacion> relacion_1  = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="id_persona_2")
    private List<PersonaRelacion> relacion_2 = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="id_persona")
	private List<DatoContacto> datos_contacto = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
			  name = "personas_nacionalidades",
			  joinColumns = @JoinColumn(name = "id_persona"), 
			  inverseJoinColumns = @JoinColumn(name = "id_pais"))
	private List<Pais> nacionalidades = new ArrayList<>();
	
	
	@JsonCreator
    public Persona(@JsonProperty("id_persona") Long id_persona) 
	{
        this.id_persona = id_persona;
    }
	
	public Persona() 
	{
		
	}

	public long getId_persona() {
		return id_persona;
	}

	public void setId_persona(long id_persona) {
		this.id_persona = id_persona;
	}

	public Pais getPer_id_pais() {
		return per_id_pais;
	}

	public void setPer_id_pais(Pais per_id_pais) {
		this.per_id_pais = per_id_pais;
	}

	public TipoDocumento getPer_id_tipo_doc() {
		return per_id_tipo_doc;
	}

	public void setPer_id_tipo_doc(TipoDocumento per_id_tipo_doc) {
		this.per_id_tipo_doc = per_id_tipo_doc;
	}

	public String getPer_nro_documento() {
		return per_nro_documento;
	}

	public void setPer_nro_documento(String per_nro_documento) {
		this.per_nro_documento = per_nro_documento;
	}

	public Sexo getGenero() {
		return genero;
	}

	public void setGenero(Sexo genero) {
		this.genero = genero;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	public void addRelacion_1(PersonaRelacion pr)
	{
		this.relacion_1.add(pr);
		pr.setId_persona_1(this);
	}
	
	public void removeRelacion_1(PersonaRelacion pr)
	{
		this.relacion_1.remove(pr);
		pr.setId_persona_1(null);
	}
	
	public void addRelacion_2(PersonaRelacion pr)
	{
		this.relacion_2.add(pr);
		pr.setId_persona_2(this);
	}
	
	public void removeRelacion_2(PersonaRelacion pr)
	{
		this.relacion_2.remove(pr);
		pr.setId_persona_2(null);
	}
	
	public void addDatoContacto(DatoContacto dc)
	{
		this.datos_contacto.add(dc);
		dc.setPersona(this);
	}
	public void removeDatoContacto(DatoContacto dc)
	{
		this.datos_contacto.remove(dc);
		dc.setPersona(null);
	}
	
	public void addNacionalidad(Pais nacionalidad)
	{
		this.nacionalidades.add(nacionalidad);
	}
	public void removeNacionadlidad(Pais nacionalidad)
	{
		this.nacionalidades.remove(nacionalidad);
	}
	
	
	  @Override 
	  public String toString() 
	  { 
		  return "Persona{" + "id=" + id_persona +
		  ", per_id_pais='" + per_id_pais + '\'' + ", per_id_tipo_doc='" +
		  per_id_tipo_doc + '\'' + ", per_nro_documento='" + per_nro_documento + '\'' +
		  ", sexo='" + genero + '\'' + ", fecha_nacimiento='" + fecha_nacimiento + '\''
		  + '}'; 
		  }
	
}




