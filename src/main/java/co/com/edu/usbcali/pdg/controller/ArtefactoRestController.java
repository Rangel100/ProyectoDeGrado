package co.com.edu.usbcali.pdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.service.ArtefactoService;
import lombok.extern.slf4j.Slf4j;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@RestController
@RequestMapping("/api/v1/artefacto")
@CrossOrigin(origins = "*")
@Slf4j
public class ArtefactoRestController {
	
    @Autowired
    private ArtefactoService artefactoService;
    
    @PostMapping("/crearArtefacto")
    public ResponseEntity<?> crearArtefacto(@RequestBody ArtefactoDTO artefactoDTO) throws Exception {
        log.debug("Request to crearArtefacto Artefacto: {}", artefactoDTO);
        
        artefactoService.crearArtefacto(artefactoDTO);
        
        return ResponseEntity.ok().body(artefactoDTO);
    }
    
    @PostMapping("/actualizarArtefacto")
    public ResponseEntity<?> actualizarArtefacto(@RequestBody ArtefactoDTO artefactoDTO) throws Exception {
        log.debug("Request to actualizarArtefacto Artefacto: {}", artefactoDTO);
        
        artefactoService.actualizarArtefacto(artefactoDTO);
        
        return ResponseEntity.ok().body(artefactoDTO);
    }
    
    @PostMapping("/eliminarArtefacto")
    public ResponseEntity<?> eliminarArtefacto(@RequestBody ArtefactoDTO artefactoDTO) throws Exception {
        log.debug("Request to eliminarArtefacto Artefacto: {}", artefactoDTO);
        
        artefactoService.eliminarArtefacto(artefactoDTO);
        
        return ResponseEntity.ok().body(artefactoDTO);
    }
    
    @PostMapping("/consultarArtefacto")
    public ResponseEntity<?> consultarArtefacto(@RequestBody Long arteId) throws Exception {
        log.debug("Request to consultarArtefacto Artefacto: {}", arteId);
        
        return ResponseEntity.ok().body(artefactoService.consultarArtefacto(arteId));
    }
    
    @PostMapping("/consultarArtefactosPorUsuario")
    public ResponseEntity<?> consultarArtefactosPorUsuario(@RequestBody Long usuaid) throws Exception {
        log.debug("Request to consultarArtefactosPorUsuario Artefacto: {}", usuaid);
        
        return ResponseEntity.ok().body(artefactoService.consultarArtefactosPorUsuario(usuaid));
    }
    
}
