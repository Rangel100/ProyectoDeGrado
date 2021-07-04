package co.com.edu.usbcali.pdg.controller;

import co.com.edu.usbcali.pdg.domain.*;
import co.com.edu.usbcali.pdg.dto.TipoArtefactoDTO;
import co.com.edu.usbcali.pdg.mapper.TipoArtefactoMapper;
import co.com.edu.usbcali.pdg.service.TipoArtefactoService;

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
@RequestMapping("/api/v1/tipoArtefacto")
@CrossOrigin(origins = "*")
@Slf4j
public class TipoArtefactoRestController {
    @Autowired
    private TipoArtefactoService tipoArtefactoService;
    @Autowired
    private TipoArtefactoMapper tipoArtefactoMapper;

    @GetMapping(value = "/{tiarId}")
    public ResponseEntity<?> findById(@PathVariable("tiarId")
    Long tiarId) throws Exception {
        log.debug("Request to findById() TipoArtefacto");

        TipoArtefacto tipoArtefacto = (tipoArtefactoService.findById(tiarId)
                                                           .isPresent() == true)
            ? tipoArtefactoService.findById(tiarId).get() : null;

        return ResponseEntity.ok()
                             .body(tipoArtefactoMapper.tipoArtefactoToTipoArtefactoDTO(
                tipoArtefacto));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() TipoArtefacto");

        return ResponseEntity.ok()
                             .body(tipoArtefactoMapper.listTipoArtefactoToListTipoArtefactoDTO(
                tipoArtefactoService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(
        @Valid
    @RequestBody
    TipoArtefactoDTO tipoArtefactoDTO) throws Exception {
        log.debug("Request to save TipoArtefacto: {}", tipoArtefactoDTO);

        TipoArtefacto tipoArtefacto = tipoArtefactoMapper.tipoArtefactoDTOToTipoArtefacto(tipoArtefactoDTO);
        tipoArtefacto = tipoArtefactoService.save(tipoArtefacto);

        return ResponseEntity.ok()
                             .body(tipoArtefactoMapper.tipoArtefactoToTipoArtefactoDTO(
                tipoArtefacto));
    }

    @PutMapping()
    public ResponseEntity<?> update(
        @Valid
    @RequestBody
    TipoArtefactoDTO tipoArtefactoDTO) throws Exception {
        log.debug("Request to update TipoArtefacto: {}", tipoArtefactoDTO);

        TipoArtefacto tipoArtefacto = tipoArtefactoMapper.tipoArtefactoDTOToTipoArtefacto(tipoArtefactoDTO);
        tipoArtefacto = tipoArtefactoService.update(tipoArtefacto);

        return ResponseEntity.ok()
                             .body(tipoArtefactoMapper.tipoArtefactoToTipoArtefactoDTO(
                tipoArtefacto));
    }

    @DeleteMapping(value = "/{tiarId}")
    public ResponseEntity<?> delete(@PathVariable("tiarId")
    Long tiarId) throws Exception {
        log.debug("Request to delete TipoArtefacto");

        tipoArtefactoService.deleteById(tiarId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(tipoArtefactoService.count());
    }
}
