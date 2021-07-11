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

import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.dto.TipoUsuarioDTO;
import co.com.edu.usbcali.pdg.mapper.TipoUsuarioMapper;
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
    @Autowired
    private TipoUsuarioMapper tipoUsuarioMapper;

    @GetMapping(value = "/{tiusId}")
    public ResponseEntity<?> findById(@PathVariable("tiusId")
    Long tiusId) throws Exception {
        log.debug("Request to findById() TipoUsuario");

        TipoUsuario tipoUsuario = (tipoUsuarioService.findById(tiusId)
                                                     .isPresent() == true)
            ? tipoUsuarioService.findById(tiusId).get() : null;

        return ResponseEntity.ok()
                             .body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(
                tipoUsuario));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() TipoUsuario");

        return ResponseEntity.ok()
                             .body(tipoUsuarioMapper.listTipoUsuarioToListTipoUsuarioDTO(
                tipoUsuarioService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(
        @Valid
    @RequestBody
    TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
        log.debug("Request to save TipoUsuario: {}", tipoUsuarioDTO);

        TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);
        tipoUsuario = tipoUsuarioService.save(tipoUsuario);

        return ResponseEntity.ok()
                             .body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(
                tipoUsuario));
    }

    @PutMapping()
    public ResponseEntity<?> update(
        @Valid
    @RequestBody
    TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
        log.debug("Request to update TipoUsuario: {}", tipoUsuarioDTO);

        TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);
        tipoUsuario = tipoUsuarioService.update(tipoUsuario);

        return ResponseEntity.ok()
                             .body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(
                tipoUsuario));
    }

    @DeleteMapping(value = "/{tiusId}")
    public ResponseEntity<?> delete(@PathVariable("tiusId")
    Long tiusId) throws Exception {
        log.debug("Request to delete TipoUsuario");

        tipoUsuarioService.deleteById(tiusId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(tipoUsuarioService.count());
    }
    
	////////////////////////////////////////////////
	    
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
	
}
