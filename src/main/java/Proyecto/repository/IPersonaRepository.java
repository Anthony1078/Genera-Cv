package Proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Proyecto.model.Persona;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
	
Persona findByDni(String dni);

}
