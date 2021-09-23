package co.com.edu.usbcali.pdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.edu.usbcali.pdg.dto.UsuarioDTO;
import co.com.edu.usbcali.pdg.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin(origins = "*")
@Slf4j
public class UsuarioRestController {
	
    @Autowired
    private UsuarioService usuarioService;
	    
	@PostMapping("/crearUsuario")
	public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
	log.debug("Request to crearUsuario TipoUsuario: {}", usuarioDTO);
	
	usuarioService.crearUsuario(usuarioDTO);
	
	return ResponseEntity.ok().body(usuarioDTO);
	}
	
	@PostMapping("/actualizarUsuario")
	public ResponseEntity<?> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
	log.debug("Request to actualizarUsuario TipoUsuario: {}", usuarioDTO);
	
	usuarioService.actualizarUsuario(usuarioDTO);
	
	return ResponseEntity.ok().body(usuarioDTO);
	}
	
	@PostMapping("/eliminarUsuario")
	public ResponseEntity<?> eliminarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
	log.debug("Request to eliminarUsuario TipoUsuario: {}", usuarioDTO);
	
	usuarioService.eliminarUsuario(usuarioDTO);
	
	return ResponseEntity.ok().body(usuarioDTO);
	}
	
	@PostMapping("/consultarUsuario")
	public ResponseEntity<?> consultarUsuario(@RequestBody Long usuaId) throws Exception {
	log.debug("Request to consultarUsuario TipoUsuario: {}", usuaId);
	
	return ResponseEntity.ok().body(usuarioService.consultarUsuario(usuaId));
	}
	
	@PostMapping("/consultarUsuarios")
	public ResponseEntity<?> consultarUsuarios(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
	log.debug("Request to consultarUsuarios TipoUsuario: {}", usuarioDTO);
	
	return ResponseEntity.ok().body(usuarioService.consultarUsuarios(usuarioDTO));
	}
	
	@PostMapping("/consultarUsuariosPorCodigoOrm")
	public ResponseEntity<?> consultarUsuariosPorCodigoOrm(@RequestBody String codigo) throws Exception {
	log.debug("Request to consultarUsuariosPorCodigoOrm :", codigo);
	
	return ResponseEntity.ok().body(usuarioService.consultarUsuariosPorCodigoOrm(codigo));
	}
	
	@PostMapping("/validarUsuarioYContraseñaCorrecta")
	public ResponseEntity<?> validarUsuarioYContraseñaCorrecta(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
	log.debug("Request to validarUsuarioYContraseñaCorrecta :", usuarioDTO);
	
	return ResponseEntity.ok().body(usuarioService.validarUsuarioYContraseñaCorrecta(usuarioDTO));
	}
	
	@PostMapping("/actualizarEnviarContraseña")
	public ResponseEntity<?> actualizarEnviarContraseña(@RequestBody String correo) throws Exception {
	log.debug("Request to actualizarEnviarContraseña :", correo);
	
	usuarioService.actualizarEnviarContraseña(correo);
	
	UsuarioDTO usu= new UsuarioDTO();
	usu.setCodigo(correo);
	
	return ResponseEntity.ok().body(usu);
	}
	
}
