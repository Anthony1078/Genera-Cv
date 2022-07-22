package Proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import Proyecto.model.*;
import Proyecto.repository.*;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioRepository repou;
	
	
	@GetMapping("/login/cargar")
	public String abrirLogin(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "loginavance";
	}
	
	@PostMapping("/login/validar")
	public String validarLogin(@ModelAttribute Usuario usuario, Model model) {
		List<Usuario> listaUsuarios = repou.findAll();
		System.out.println("Enviado :" + usuario); // del formulario de login
		
		Usuario u = repou.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());
		
		System.out.println(u); // de la busqueda
		
		if (u == null) {
			model.addAttribute("mensaje","Usuario o clave incorrecto");
			return "loginavance";
		}
		
		
		model.addAttribute("usuario",u);
		model.addAttribute("listaUsuarios", listaUsuarios);
		
		return "principal";
	
		
		
	}
	
	@GetMapping("/usuarios/cargar")
	public String abrirRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "register";
	}
	
	
	@PostMapping("/usuarios/grabar")
	public String grabarRegistro(@ModelAttribute Usuario usuario, Model model) {
		
		System.out.println("Enviado :" + usuario);
		
		try {
			repou.save(usuario);
			model.addAttribute("sucess","Usuario registrado");
			
		} catch (Exception e) {
			model.addAttribute("error","Error al registrar");
		}
		
		
		return "register";
	}
	
	@GetMapping("/usuario/lista")
	public String ListaUsuario(Model model) {
		model.addAttribute("listaUsuarios", repou.findAll());
		return "listado";
	}
	
	
	/*
	@PostMapping("/verificarxDNI/{dni}")
	public ResponseEntity<Object> getDatosdni(Model model, @PathVariable int dni) {
		
		 RestTemplate rest = new RestTemplate();
		 String url = "https://dniruc.apisperu.com/api/v1/dni/"+dni +"?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFudGhvbnkuZ3VpemFkb0Bob3RtYWlsLmNvbSJ9.TJfAY-fQLxNEmg8tmYowU3lFchI-mfR-rBjnKhPbusE";
		 ResponseEntity<Persona> response = rest.exchange(url, HttpMethod.GET, null, Persona.class);
		 Persona psevo = response.getBody();
		 System.out.println(psevo);
		 
		 if(psevo.getDni() == 0) {		
			// return "Error";
		 }else {
			 /*
			 model.addAttribute("dni", psevo.getDni());
			 model.addAttribute("nombres", psevo.getNombre());
			 model.addAttribute("ApePaterno", psevo.getApellidopaterno());
			 model.addAttribute("ApeMaterno", psevo.getApellidomaterno());
			 
			 
			return  new ResponseEntity<Object>(psevo, HttpStatus.OK);		 
		 }
			 
		 return  new ResponseEntity<Object>(psevo, HttpStatus.OK);		
		
	}
	
	
	@PostMapping("/savePersona")
    public void savePersona(@ModelAttribute Persona persona, Model model) {
		
		System.out.println("Nueva Persona :" + persona);		
		try {
			repop.save(persona);
		} catch (Exception e) {	
			
		}	   
 
    }
	*/
	
	
	
}