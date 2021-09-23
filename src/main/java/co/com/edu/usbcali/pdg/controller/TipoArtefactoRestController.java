package co.com.edu.usbcali.pdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.edu.usbcali.pdg.dto.TipoArtefactoDTO;
import co.com.edu.usbcali.pdg.service.TipoArtefactoService;
import lombok.extern.slf4j.Slf4j;


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
    
    @PostMapping("/crearTipoArtefacto")
    public ResponseEntity<?> crearTipoArtefacto(@RequestBody TipoArtefactoDTO tipoArtefactoDTO) throws Exception {
        log.debug("Request to crearArtefacto TipoArtefacto: {}", tipoArtefactoDTO);
        
        tipoArtefactoService.crearTipoArtefacto(tipoArtefactoDTO);
        
        return ResponseEntity.ok().body(tipoArtefactoDTO);
    }
    
    @PostMapping("/actualizarTipoArtefacto")
    public ResponseEntity<?> actualizarTipoArtefacto(@RequestBody TipoArtefactoDTO tipoArtefactoDTO) throws Exception {
        log.debug("Request to actualizarTipoArtefacto TipoArtefacto: {}", tipoArtefactoDTO);
        
        tipoArtefactoService.actualizarTipoArtefacto(tipoArtefactoDTO);
        
        return ResponseEntity.ok().body(tipoArtefactoDTO);
    }
    
    @PostMapping("/eliminarTipoArtefacto")
    public ResponseEntity<?> eliminarTipoArtefacto(@RequestBody TipoArtefactoDTO tipoArtefactoDTO) throws Exception {
        log.debug("Request to eliminarTipoArtefacto TipoArtefacto: {}", tipoArtefactoDTO);
        
        tipoArtefactoService.eliminarTipoArtefacto(tipoArtefactoDTO);
        
        return ResponseEntity.ok().body(tipoArtefactoDTO);
    }
    
    @PostMapping("/consultarTipoArtefacto")
    public ResponseEntity<?> consultarTipoArtefacto(@RequestBody Long tiarId) throws Exception {
        log.debug("Request to consultarTipoArtefacto TipoArtefacto: {}", tiarId);
        
        return ResponseEntity.ok().body(tipoArtefactoService.consultarTipoArtefacto(tiarId));
    }
    
    @GetMapping("/consultarTipoArtefactoActivos")
	public ResponseEntity<?> consultarTipoArtefactoActivos() throws Exception {
		log.debug("Request to consultarTipoArtefactoActivos TipoUsuario: {}");
		
		return ResponseEntity.ok().body(tipoArtefactoService.consultarTipoArtefactosActivos());
	}
    
}
