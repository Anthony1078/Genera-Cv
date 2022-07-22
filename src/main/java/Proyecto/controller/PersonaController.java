package Proyecto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import Proyecto.model.*;
import Proyecto.repository.*;

@Controller
public class PersonaController {
	
	
	@Autowired
	private IPersonaRepository repop;	
	
	@Autowired
	private IDatosRepository repod;	
	
	@PostMapping("/verificarxDNI/{dni}")
	public ResponseEntity<Object> getDatosdni(Model model, @PathVariable String dni) {
		 RestTemplate rest = new RestTemplate();
		 String url = "https://apiperu.dev/api/dni/"+dni+"?api_token=2200b795b35a6ba410e4ac53b5004137f439fe5fdcaf57e57cb1be9100309d36";
		 System.out.println(url);
		 ResponseEntity<Api> response = rest.exchange(url, HttpMethod.GET, null, Api.class);		 
		 //ResponseEntity<Persona> response = rest.getForEntity(url,Persona.class);
		 System.out.println(response);		 
		 Api psevo = response.getBody();
		 System.out.println(psevo);
		 
		
		 
		 System.out.println( psevo.getData());
		 
		 if(psevo.getData() == null) {	
			System.out.println("No Existe la Persona");
			 return  new ResponseEntity<Object>(psevo, HttpStatus.OK);		
			 
		 }else {
			 //Guardamos a las persona
			 repod.save(psevo.getData());
			 System.out.println( psevo.getData().getNumero());
			 
			 Persona newprueba = new Persona();
			 
			 newprueba.setDni(psevo.getData().getNumero());
			 newprueba.setNombres(psevo.getData().getNombres());
			 newprueba.setApellidoPaterno(psevo.getData().getApellido_paterno());
			 newprueba.setApellidoMaterno(psevo.getData().getApellido_materno());
			 newprueba.setFechanacimiento(psevo.getData().getFecha_nacimiento());
			 newprueba.setSexo(psevo.getData().getSexo());
			 newprueba.setEstadocivil(psevo.getData().getEstado_civil());
			 newprueba.setDireccion(psevo.getData().getDireccion_completa());
			 newprueba.setDistrito(psevo.getData().getDistrito());
			 repop.save(newprueba);		 
			 
			 
			return  new ResponseEntity<Object>(psevo, HttpStatus.OK);		 
		 }
			 
		 
		
	}
	@GetMapping("/persona/listar")
	public String ListaPersona(Model model) {
		model.addAttribute("lstPersona", repop.findAll());
		return "listado";
	}

	@PostMapping("/saveEmailFono")
	@ResponseBody
	public Boolean  saveEmailFono(Model model,String dni, String email, String fono) {	
		
		Persona newprueba = repop.findByDni(dni);
		
		System.out.println(newprueba.getId());
		
		newprueba.setCorreo(email);
		newprueba.setTelefono(fono);
		
		repop.save(newprueba);
		
		try {
			System.out.println("Actualización: " +dni + "   "+ email + "   "+fono);
			
			System.out.println("Persona Actualizada: " +newprueba);
			
			
		
		} catch (Exception e) {		
			
		}	
		
		return true;		 
		
	}
	
	
	
	@PostMapping("/saveset2")
	@ResponseBody
	public Boolean saveset2(Model model,String dni, String sobremi, String carrera, String conocimientos, String idiomas, String institucion, String fechainsc, String descedu) {	
		
		Persona newprueba = repop.findByDni(dni);
		
		System.out.println(newprueba.getId());
		
		newprueba.setSobremi(sobremi);
		newprueba.setCarrera(carrera);
		newprueba.setConocimientos(conocimientos);
		newprueba.setIdiomas(idiomas);
		newprueba.setInstitucion(institucion);
		newprueba.setFechaeducacion(fechainsc);
		newprueba.setDescripeduca(descedu);
		
		repop.save(newprueba);
		
		try {
			System.out.println("Actualización: " +carrera + "   "+ idiomas + "   "+institucion);			
			System.out.println("Persona Actualizada: " +newprueba);		
		
		} catch (Exception e) {		
			
		}	
		
		return true;		 
		
	}
	
	
	@PostMapping("/saveset3")
	@ResponseBody
	public Boolean saveset3(Model model,String dni, String empresa, String puesto, String fechaslab, String descriplab, String linkedin, String twittter, String instagram , String facebook) {	
		
		Persona newprueba = repop.findByDni(dni);
		
		System.out.println(newprueba.getId());
		
		newprueba.setEmpresa(empresa);
		newprueba.setPuesto(puesto);
		newprueba.setFechalabor(fechaslab);
		newprueba.setDescriplabor(descriplab);
		newprueba.setLinkedin(linkedin);
		newprueba.setTwiter(twittter);
		newprueba.setInstagram(instagram);
		newprueba.setFacebook(facebook);
		
		repop.save(newprueba);
		
		try {
			System.out.println("Actualización: " +empresa + "   "+ puesto + "   "+fechaslab);			
			System.out.println("Persona Actualizada: " +newprueba);		
		
		} catch (Exception e) {		
			
		}	
		
		return true;		 
		
	}
	
	/*
	@PostMapping("/savePersona/{persona}")
    public void savePersona(Model model, @PathVariable Persona persona) {
		
		System.out.println("Nueva Persona :" + persona);		
		try {
			repop.save(persona);
		} catch (Exception e) {	
			
		}	   
 
    }*/
	
	@GetMapping("/portafolio")
    public String InterfazWeb(Model model) {
		model.addAttribute("personas", repop.findAll());
		//System.out.println(repop.findAll());
        return "portafolio";
 
    }
	
	
	@PostMapping("/saveImagen")
    public String saveImagen(Model model,String dnioculto ,@RequestParam("file") MultipartFile file) {
		
		Persona newprueba = repop.findByDni(dnioculto);
		
		int id = newprueba.getId();
		
		System.out.println("DNI: "+dnioculto);	
		
		if(!file.isEmpty()) {
			Path directImagen = Paths.get("src//main//resources//static/img/personas");
			String rutaAb= directImagen.toFile().getAbsolutePath();
			
			try {
				byte[] bytesImg = file.getBytes();
				Path rutaComp = Paths.get(rutaAb+"//"+file.getOriginalFilename());
				Files.write(rutaComp, bytesImg);
				
				newprueba.setFoto(file.getOriginalFilename());
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("CODIGO :" + persona.getDni());	
		
		System.out.println("Foto agredada :" + newprueba);		
		try {
			repop.save(newprueba);	
		} catch (Exception e) {		
		}		
        return "redirect:/portafolio/"+id;
 
    }
	
	@GetMapping("/portafolio/{id}")
	public String buscarPersona(@PathVariable("id") Integer id, Model model) {
		Persona p = repop.getReferenceById(id);
		model.addAttribute("persona",p);
		return "index";
	}
	
	
}
