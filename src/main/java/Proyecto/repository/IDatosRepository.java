package Proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Proyecto.model.Datos;
import Proyecto.model.Persona;

public interface IDatosRepository extends JpaRepository<Datos, Integer> {

Datos findByNumero(String nummero);

}
