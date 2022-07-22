package Proyecto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table (name = "tb_persona")
@Data
public class Persona {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "dni_Usu")
	private String dni;
	
	@Column(name = "nom_Usu")
	private String nombres;
	
	@Column(name = "ape_pater_usu")
	private String apellidoPaterno;
	
	@Column(name = "ape_mater_usu")
	private String apellidoMaterno;
	
	@Column(name = "fech_usu")
	private String fechanacimiento;
	
	@Column(name = "sex_usu")
	private String sexo;
	
	@Column(name = "estcivil_usu")
	private String estadocivil;
	
	@Column(name = "fono_usu")
	private String telefono;
	
	@Column(name = "correo_usu")
	private String correo;
	
	@Column(name = "dirrec_usu")
	private String direccion;
	
	@Column(name = "distri_usu")
	private String distrito;	
	
	@Column(name = "sobremi_usu")
	private String sobremi;
	
	@Column(name = "carrera_usu")
	private String carrera;
	
	@Column(name = "conocimientos_usu")
	private String conocimientos;
	
	@Column(name = "idiomas_usu")
	private String idiomas;
	
	@Column(name = "institucion_usu")
	private String institucion;
	
	@Column(name = "fechaedu_usu")
	private String fechaeducacion;
	
	@Column(name = "decripedu_usu")
	private String descripeduca;
	
	@Column(name = "empresa_usu")
	private String empresa;
	
	@Column(name = "puesto_usu")
	private String puesto;
	
	@Column(name = "fechalob_usu")
	private String fechalabor;
	
	@Column(name = "descriplab_usu")
	private String descriplabor;
	
	@Column(name = "twiter_usu")
	private String twiter;
	
	@Column(name = "facebook_usu")
	private String facebook;
	
	@Column(name = "instagram_usu")
	private String instagram;
	
	@Column(name = "linkedin_usu")
	private String linkedin;	
	
	@Column(name = "foto_usu")
	private String foto;
}
