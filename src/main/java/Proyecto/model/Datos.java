package Proyecto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name = "tb_datos")
@Data
public class Datos {
	
	@Id
	@Column(name = "id")
	private int id;
		
	@Column(name = "dni_datos")
	private String numero;
	
	@Column(name = "nom_datos")
	private String nombres;
		
	@Column(name = "ape_pater_datos")
	private String apellido_paterno;
	
	@Column(name = "ape_mater_datos")
	private String apellido_materno;
	
	@Column(name = "fech_datos")
	private String fecha_nacimiento;
	
	@Column(name = "sex_datos")
	private String sexo;
	
	@Column(name = "estcivil_datos")
	private String estado_civil;
	
	@Column(name = "dirrec_datos")
	private String direccion_completa;
	
	@Column(name = "distri_datos")
	private String distrito;
	
	

}
