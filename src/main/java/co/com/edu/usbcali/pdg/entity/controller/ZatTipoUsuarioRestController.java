package co.com.edu.usbcali.pdg.entity.controller;

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
import co.com.edu.usbcali.pdg.entity.service.ZatTipoUsuarioService;
import co.com.edu.usbcali.pdg.mapper.TipoUsuarioMapper;
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
public class ZatTipoUsuarioRestController {
	
    @Autowired
    private ZatTipoUsuarioService zatTipoUsuarioService;
    
    @Autowired
    private TipoUsuarioMapper tipoUsuarioMapper;

    @GetMapping(value = "/{tiusId}")
    public ResponseEntity<?> findById(@PathVariable("tiusId")
    Long tiusId) throws Exception {
        log.debug("Request to findById() TipoUsuario");

        TipoUsuario tipoUsuario = (zatTipoUsuarioService.findById(tiusId)
                                                     .isPresent() == true)
            ? zatTipoUsuarioService.findById(tiusId).get() : null;

        return ResponseEntity.ok()
                             .body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(
                tipoUsuario));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() TipoUsuario");

        return ResponseEntity.ok()
                             .body(tipoUsuarioMapper.listTipoUsuarioToListTipoUsuarioDTO(
                            		 zatTipoUsuarioService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(
        @Valid
    @RequestBody
    TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
        log.debug("Request to save TipoUsuario: {}", tipoUsuarioDTO);

        TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);
        tipoUsuario = zatTipoUsuarioService.save(tipoUsuario);

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
        tipoUsuario = zatTipoUsuarioService.update(tipoUsuario);

        return ResponseEntity.ok()
                             .body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(
                tipoUsuario));
    }

    @DeleteMapping(value = "/{tiusId}")
    public ResponseEntity<?> delete(@PathVariable("tiusId")
    Long tiusId) throws Exception {
        log.debug("Request to delete TipoUsuario");

        zatTipoUsuarioService.deleteById(tiusId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(zatTipoUsuarioService.count());
    }
	
}
