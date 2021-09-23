package co.com.edu.usbcali.pdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.edu.usbcali.pdg.dto.TipoUsuarioDTO;
import co.com.edu.usbcali.pdg.service.TipoUsuarioService;
import lombok.extern.slf4j.Slf4j;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@RestController
@RequestMapping("/api/v1/tipoUsuario")
@CrossOrigin(origins = "*")
@Slf4j
public class TipoUsuarioRestController {
	
    @Autowired
    private TipoUsuarioService tipoUsuarioService;
	    
	@PostMapping("/crearTipoUsuario")
	public ResponseEntity<?> crearTipoUsuario(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		log.debug("Request to crearTipoUsuario TipoUsuario: {}", tipoUsuarioDTO);
		
		tipoUsuarioService.crearTipoUsuario(tipoUsuarioDTO);
		
		return ResponseEntity.ok().body(tipoUsuarioDTO);
	}
	
	@PostMapping("/actualizarTipoUsuario")
	public ResponseEntity<?> actualizarTipoUsuario(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		log.debug("Request to actualizarTipoUsuario TipoUsuario: {}", tipoUsuarioDTO);
		
		tipoUsuarioService.actualizarTipoUsuario(tipoUsuarioDTO);
		
		return ResponseEntity.ok().body(tipoUsuarioDTO);
	}
	
	@PostMapping("/eliminarTipoUsuario")
	public ResponseEntity<?> eliminarTipoUsuario(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		log.debug("Request to eliminarTipoUsuario TipoUsuario: {}", tipoUsuarioDTO);
		
		tipoUsuarioService.eliminarTipoUsuario(tipoUsuarioDTO);
		
		return ResponseEntity.ok().body(tipoUsuarioDTO);
	}
	
	@PostMapping("/consultarTipoUsuario")
	public ResponseEntity<?> consultarTipoUsuario(@RequestBody Long tiusId) throws Exception {
		log.debug("Request to consultarTipoUsuario TipoUsuario: {}", tiusId);
		
		return ResponseEntity.ok().body(tipoUsuarioService.consultarTipoUsuario(tiusId));
	}
	
	@GetMapping("/consultarTipoUsuarioActivos")
	public ResponseEntity<?> consultarTipoUsuarioActivos() throws Exception {
		log.debug("Request to consultarTipoUsuarioActivos TipoUsuario: {}");
		
		return ResponseEntity.ok().body(tipoUsuarioService.consultarTipoUsuarioActivos());
	}
	
}
