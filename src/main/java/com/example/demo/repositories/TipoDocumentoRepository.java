package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.TipoDocumento;

@Repository
public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento, Long> 
{
	
}
