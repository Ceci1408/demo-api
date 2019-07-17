package com.example.demo.controllers;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Pais;
import com.example.demo.entities.Persona;
import com.example.demo.entities.TipoDocumento;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repositories.PaisRepository;
import com.example.demo.repositories.PersonaRepository;
import com.example.demo.repositories.TipoDocumentoRepository;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class PersonaController
{	
	@Autowired
	private PersonaRepository personaRepository;
	
	@GetMapping("/persona")
    public Iterable<Persona> getpersonas() {
        return personaRepository.findAll();
    }
	
	@GetMapping("/persona/{id}")
    public Optional<Persona> getpersona(@PathVariable("id") Long id_persona) 
	{
        return personaRepository.findById(id_persona);
    }
	
	@RequestMapping(value = "/persona", method = RequestMethod.POST)
	public Persona createPersona(@Valid @RequestBody Persona pers) 
	{
		return this.personaRepository.save(pers);
    }
    
	@RequestMapping(value = "/persona/{id}", method = RequestMethod.PUT)
    public Persona updatePersona(@PathVariable("id") Long id_persona,
                                   @Valid @RequestBody Persona personaRequest) 
	{
        
		return personaRepository.findById(id_persona)
                .map(persona -> {
                    persona.setPer_id_pais(personaRequest.getPer_id_pais());
                    persona.setPer_id_tipo_doc(personaRequest.getPer_id_tipo_doc());
                    persona.setPer_nro_documento(personaRequest.getPer_nro_documento());
                    persona.setGenero(personaRequest.getGenero());
                    persona.setFecha_nacimiento(personaRequest.getFecha_nacimiento());
                    return personaRepository.save(persona);
                }).orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con el ID " + id_persona)); /* Se puede agregar una clase con las excepciones*/
    }
	
	@RequestMapping(value = "/persona/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePersona(@PathVariable Long id_persona) {
        return personaRepository.findById(id_persona)
                .map(persona -> {
                    personaRepository.delete(persona);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con el ID " + id_persona));
    }
}
