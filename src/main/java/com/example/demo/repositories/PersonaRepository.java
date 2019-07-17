package com.example.demo.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long>
{	

}
