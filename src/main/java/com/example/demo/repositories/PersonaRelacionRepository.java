package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.PersonaRelacion;


@Repository
public interface PersonaRelacionRepository extends CrudRepository<PersonaRelacion, Long> {

}
