package com.example.demo.controllers;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Pais;
import com.example.demo.entities.Persona;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repositories.PaisRepository;
import com.example.demo.repositories.PersonaRepository;

@RestController
public class PaisController 
{
	@Autowired
	private PaisRepository paisRepository;
	private PersonaRepository personaRepository;
	
	@GetMapping("/pais")
    public Iterable<Pais> getpaises() {
        return paisRepository.findAll();
    }
	
	@RequestMapping(value = "/pais/{id}", method = RequestMethod.GET)
    public Optional<Pais> getPais(@PathVariable("id") Long id_pais)
	{
		return paisRepository.findById(id_pais);
	}
	
	@RequestMapping(value = "/pais", method = RequestMethod.POST)
	public Pais createPais(@Valid @RequestBody Pais pais) 
	{
        return paisRepository.save(pais);
    }
	
	@RequestMapping(value="pais/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deletePais(@PathVariable("id") Long id_pais) 
	{
        return paisRepository.findById(id_pais)
                .map(pais -> {
                    paisRepository.delete(pais);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Pais no encontrado con el ID " + id_pais));
    }
	
	@RequestMapping(value = "/pais/{id}", method = RequestMethod.PUT)
    public Pais updatePais(@PathVariable("id") Long id_pais,
                                   @Valid @RequestBody Pais paisRequest) 
	{
        
		return paisRepository.findById(id_pais)
                .map(pais -> {
                	pais.setPais(paisRequest.getPais());
                    return paisRepository.save(pais);
                }).orElseThrow(() -> new ResourceNotFoundException("Pais no encontrado con el ID " + id_pais)); 
    }

	@RequestMapping(value="pais/{id_pais}/persona", method=RequestMethod.PUT)
	public Pais addPersona(@PathVariable("id_pais") Long id_pais,
			@Valid @RequestBody Persona id_persona)
	{
		return paisRepository.findById(id_pais).map(
				pais -> {
					pais.addPersona(id_persona);
					return paisRepository.save(pais);
				}).orElseThrow(() -> new ResourceNotFoundException("Pais no encontrado con el ID " + id_pais)); 
	}
}
