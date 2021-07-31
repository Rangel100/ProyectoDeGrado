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

import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.entity.service.ZatArtefactoService;
import co.com.edu.usbcali.pdg.mapper.ArtefactoMapper;
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
public class ZatArtefactoRestController {
	
    @Autowired
    private ZatArtefactoService zatArtefactoService;
    
    @Autowired
    private ArtefactoMapper artefactoMapper;

    @GetMapping(value = "/{arteId}")
    public ResponseEntity<?> findById(@PathVariable("arteId")
    Long arteId) throws Exception {
        log.debug("Request to findById() Artefacto");

        Artefacto artefacto = (zatArtefactoService.findById(arteId).isPresent() == true)
            ? zatArtefactoService.findById(arteId).get() : null;

        return ResponseEntity.ok()
                             .body(artefactoMapper.artefactoToArtefactoDTO(
                artefacto));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Artefacto");

        return ResponseEntity.ok()
                             .body(artefactoMapper.listArtefactoToListArtefactoDTO(
                zatArtefactoService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    ArtefactoDTO artefactoDTO) throws Exception {
        log.debug("Request to save Artefacto: {}", artefactoDTO);

        Artefacto artefacto = artefactoMapper.artefactoDTOToArtefacto(artefactoDTO);
        artefacto = zatArtefactoService.save(artefacto);

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
        artefacto = zatArtefactoService.update(artefacto);

        return ResponseEntity.ok()
                             .body(artefactoMapper.artefactoToArtefactoDTO(
                artefacto));
    }

    @DeleteMapping(value = "/{arteId}")
    public ResponseEntity<?> delete(@PathVariable("arteId")
    Long arteId) throws Exception {
        log.debug("Request to delete Artefacto");

        zatArtefactoService.deleteById(arteId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(zatArtefactoService.count());
    }
    
}
