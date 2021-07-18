package co.com.edu.usbcali.pdg.controller;

import co.com.edu.usbcali.pdg.domain.*;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.mapper.ArtefactoMapper;
import co.com.edu.usbcali.pdg.service.ArtefactoService;

import lombok.extern.slf4j.Slf4j;

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

import javax.validation.Valid;


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
    
    @Autowired
    private ArtefactoMapper artefactoMapper;

    @GetMapping(value = "/{arteId}")
    public ResponseEntity<?> findById(@PathVariable("arteId")
    Long arteId) throws Exception {
        log.debug("Request to findById() Artefacto");

        Artefacto artefacto = (artefactoService.findById(arteId).isPresent() == true)
            ? artefactoService.findById(arteId).get() : null;

        return ResponseEntity.ok()
                             .body(artefactoMapper.artefactoToArtefactoDTO(
                artefacto));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Artefacto");

        return ResponseEntity.ok()
                             .body(artefactoMapper.listArtefactoToListArtefactoDTO(
                artefactoService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    ArtefactoDTO artefactoDTO) throws Exception {
        log.debug("Request to save Artefacto: {}", artefactoDTO);

        Artefacto artefacto = artefactoMapper.artefactoDTOToArtefacto(artefactoDTO);
        artefacto = artefactoService.save(artefacto);

        return ResponseEntity.ok()
                             .body(artefactoMapper.artefactoToArtefactoDTO(
                artefacto));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    ArtefactoDTO artefactoDTO) throws Exception {
        log.debug("Request to update Artefacto: {}", artefactoDTO);

        Artefacto artefacto = artefactoMapper.artefactoDTOToArtefacto(artefactoDTO);
        artefacto = artefactoService.update(artefacto);

        return ResponseEntity.ok()
                             .body(artefactoMapper.artefactoToArtefactoDTO(
                artefacto));
    }

    @DeleteMapping(value = "/{arteId}")
    public ResponseEntity<?> delete(@PathVariable("arteId")
    Long arteId) throws Exception {
        log.debug("Request to delete Artefacto");

        artefactoService.deleteById(arteId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(artefactoService.count());
    }
    
    //////////////////////////////////////////////////////////
    
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
