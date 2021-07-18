package co.com.edu.usbcali.pdg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;
import co.com.edu.usbcali.pdg.mapper.UsuarioMapper;
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
    
    @Autowired
    private UsuarioMapper usuarioMapper;

    @GetMapping(value = "/{usuaId}")
    public ResponseEntity<?> findById(@PathVariable("usuaId")
    Long usuaId) throws Exception {
        log.debug("Request to findById() Usuario");

        Usuario usuario = (usuarioService.findById(usuaId).isPresent() == true)
            ? usuarioService.findById(usuaId).get() : null;

        return ResponseEntity.ok()
                             .body(usuarioMapper.usuarioToUsuarioDTO(usuario));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Usuario");

        return ResponseEntity.ok()
                             .body(usuarioMapper.listUsuarioToListUsuarioDTO(
                usuarioService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    UsuarioDTO usuarioDTO) throws Exception {
        log.debug("Request to save Usuario: {}", usuarioDTO);

        Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
        usuario = usuarioService.save(usuario);

        return ResponseEntity.ok()
                             .body(usuarioMapper.usuarioToUsuarioDTO(usuario));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    UsuarioDTO usuarioDTO) throws Exception {
        log.debug("Request to update Usuario: {}", usuarioDTO);

        Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
        usuario = usuarioService.update(usuario);

        return ResponseEntity.ok()
                             .body(usuarioMapper.usuarioToUsuarioDTO(usuario));
    }

    @DeleteMapping(value = "/{usuaId}")
    public ResponseEntity<?> delete(@PathVariable("usuaId")
    Long usuaId) throws Exception {
        log.debug("Request to delete Usuario");

        usuarioService.deleteById(usuaId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(usuarioService.count());
    }
    
	////////////////////////////////////////////////
	    
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
	
}
