package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.repositories.TipoDocumentoRepository;

@RestController
public class TipoDocumentoController 
{
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	@GetMapping("/tipo_documento")
    public Iterable<TipoDocumento> getTipos() {
        return tipoDocumentoRepository.findAll();
    }
	
	@RequestMapping(value = "/tipo_documento", method = RequestMethod.POST)
	public TipoDocumento createTipoDocumento(@Valid @RequestBody TipoDocumento tipodocumento) 
	{
        return tipoDocumentoRepository.save(tipodocumento);
    }
	
	@RequestMapping(value="tipo_documento/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteTipoDocumento(@PathVariable("id") Long id_tipo_documento) 
	{
        return tipoDocumentoRepository.findById(id_tipo_documento)
                .map(tipoDocumento -> {
                	tipoDocumentoRepository.delete(tipoDocumento);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Tipo de documento  no encontrado con el ID " + id_tipo_documento));
    }
	
	@RequestMapping(value="tipo_documento/{id}", method=RequestMethod.PUT)
	public TipoDocumento updateTipoDocumento(@PathVariable("id") Long id_tipo_documento,
			@Valid @RequestBody TipoDocumento tipo_doc_request)
	{
		return tipoDocumentoRepository.findById(id_tipo_documento)
                .map(tipoDocumento -> {
                	tipoDocumento.setTipo_documento(tipo_doc_request.getTipo_documento());
                    return tipoDocumentoRepository.save(tipoDocumento);
                }).orElseThrow(() -> new ResourceNotFoundException("Pais no encontrado con el ID " + id_tipo_documento)); 
 
	}
	
	
	
}
