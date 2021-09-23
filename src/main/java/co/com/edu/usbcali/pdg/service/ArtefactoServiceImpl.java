package co.com.edu.usbcali.pdg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.domain.TipoArtefacto;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;
import co.com.edu.usbcali.pdg.entity.service.ZatArtefactoService;
import co.com.edu.usbcali.pdg.entity.service.ZatTipoArtefactoService;
import co.com.edu.usbcali.pdg.exception.ZMessManager;
import co.com.edu.usbcali.pdg.repository.ArtefactoRepository;
import co.com.edu.usbcali.pdg.utility.Constantes;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service
@Slf4j
public class ArtefactoServiceImpl implements ArtefactoService {
	
	@Autowired
	private ArtefactoRepository artefactoRepository;
	
	@Autowired
	private ZatArtefactoService zatArtefactoService;
	
	@Autowired
	private ZatTipoArtefactoService zatTipoArtefactoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void crearArtefacto(ArtefactoDTO artefactoDTO) throws Exception {
		try {
			
			//Validar que artefactoDTO no sea null 
			if (artefactoDTO == null) {
				throw new ZMessManager("El artefacto esta nulo o vacío.");
			}
			
			Artefacto artefacto = new Artefacto();
			
			//Metodo que implementa las validaciones
			validarArtefacto(artefactoDTO, artefacto);
			
			//Seteo el estado
			artefacto.setEstado(Constantes.ESTADO_ACTIVO);
			
			artefactoRepository.save(artefacto);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void actualizarArtefacto(ArtefactoDTO artefactoDTO) throws Exception {
		try {
			
			//Validar que artefactoDTO no sea null 
			if (artefactoDTO == null) {
				throw new ZMessManager("El artefacto esta nulo o vacío.");
			}
			
			//Validar que arteId no sea null 
			if (artefactoDTO.getArteId() == null) {
				throw new ZMessManager("El identificador del artefacto esta nulo o vacío.");
			}
			
			Optional<Artefacto> artefactoOpt = artefactoRepository.findById(artefactoDTO.getArteId());
			
			if (!artefactoOpt.isPresent()) {
				throw new ZMessManager("El artefacto seleccionado no exíste");
			}
			
			Artefacto artefacto = artefactoOpt.get();
			
			//Metodo que implementa las validaciones
			validarArtefacto(artefactoDTO, artefacto);
			
			zatArtefactoService.update(artefacto);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarArtefacto(ArtefactoDTO artefactoDTO) throws Exception {
		try {
			
			//Validar que artefactoDTO no sea null 
			if (artefactoDTO == null) {
				throw new ZMessManager("El artefacto esta nulo o vacío.");
			}
			
			//Validar que arteId no sea null 
			if (artefactoDTO.getArteId() == null) {
				throw new ZMessManager("El identificador del artefacto esta nulo o vacío.");
			}
			
			//Validar que el artefacto exísta
			Optional<Artefacto> artefactoOpt = artefactoRepository.findById(artefactoDTO.getArteId());
			
			if (!artefactoOpt.isPresent()) {
				throw new ZMessManager("El artefacto seleccionado no exíste");
			}
			
			Artefacto artefacto = artefactoOpt.get();
			
			//Seteo el estado inactivo
			artefacto.setEstado(Constantes.ESTADO_INACTIVO);
			
			zatArtefactoService.update(artefacto);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ArtefactoDTO consultarArtefacto(Long arteId) throws Exception {
		try {
			
			//Validar que arteId no sea null 
			if (arteId == null) {
				throw new ZMessManager("El identificador del artefacto esta nulo o vacío.");
			}
			
			//Validar que el artefacto exísta
			ArtefactoDTO artefactoDTO = artefactoRepository.consultarArtefacto(arteId, Constantes.ESTADO_ACTIVO);
			
			return artefactoDTO;
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	private void validarArtefacto(ArtefactoDTO artefactoDTO, Artefacto artefacto) {
		//validar que el codigo no sea null
		if (artefactoDTO.getCodigo() != null && !artefactoDTO.getCodigo().trim().isEmpty()) {
			
			//Seteo del codigo
			artefacto.setCodigo(artefactoDTO.getCodigo());
			
		} else {
			throw new ZMessManager("El codigo se encuentra nulo o vacío");
		}
		
		//Validar que el url no sea null 
		if (artefactoDTO.getUrl() != null && !artefactoDTO.getUrl().trim().isEmpty()) {
			
			//Seteo el url
			artefacto.setUrl(artefactoDTO.getUrl());
			
		} else {
			throw new ZMessManager("El url se encuentra nulo o vacío.");
		}
		
		//Validar que el tipo de artefacto no sea null 
		if (artefactoDTO.getTiarId_TipoArtefacto() != null) {
			
			//Valido que el tipo de artefacto exista
			Optional<TipoArtefacto> tipoArtefactoOpt = zatTipoArtefactoService.findById(artefactoDTO.getTiarId_TipoArtefacto());
			
			if (!tipoArtefactoOpt.isPresent()) {
				throw new ZMessManager("El tipo de artefacto seleccionado no exíste.");
			}
			
			TipoArtefacto tipoArtefacto = tipoArtefactoOpt.get();
			
			//Seteo el tipo de artefacto
			artefacto.setTipoArtefacto(tipoArtefacto);
			
		} else {
			throw new ZMessManager("El tipo de artefacto se encuentra nulo o vacío");
		}
		
		//Validar que el usuario no sea null 
		if (artefactoDTO.getCodigoUsuario() != null) {
			
			//Valido que el usuario exista
			List<Usuario> usuarioList = usuarioService.consultarUsuariosPorCodigo(artefactoDTO.getCodigoUsuario().trim());
			
			if (usuarioList.isEmpty()) {
				throw new ZMessManager("El usuario seleccionado no exíste.");
			}
			
			for (Usuario usuario : usuarioList) {
				
				//Seteo el usuario
				artefacto.setUsuario(usuario);
			}
			
		} else {
			throw new ZMessManager("El codigo del usuario se encuentra nulo o vacío");
		}
		
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarArtefactosPorUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			
			//Validar que usuarioDTO no sea null 
			if (usuarioDTO == null) {
				throw new ZMessManager("El usuario esta nulo o vacío.");
			}
			
			//Validar que el usuaId no sea nulo
			if (usuarioDTO.getUsuaId() == null ) {
				throw new ZMessManager("El identificador del usuario esta nulo o vacío.");
			}
			
			//Metodo que consulta los artefactos asociados a un usuario
			List<Artefacto> artefactosList = artefactoRepository.findByUsuario_usuaId(usuarioDTO.getUsuaId());
			
			//Si la lista no esta vacía se 
			if (!artefactosList.isEmpty()) {
				
				//recorro la lista de los artefactos para inactivarlos
				for (Artefacto  artefacto: artefactosList) {
					
					//Seteo el estado inactivo
					artefacto.setEstado(Constantes.ESTADO_INACTIVO);
					
					zatArtefactoService.update(artefacto);
				}
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Artefacto> consultarArtefactosPorTipoArtefacto(Long tiarId) {
		try {
			//Validar que tipo artefacto no sea null 
			if (tiarId == null) {
				throw new ZMessManager("El identificador del tipo de artefacto esta nulo o vacío.");
			}
			
			return artefactoRepository.findByTipoArtefacto_tiarIdAndEstado(tiarId, Constantes.ESTADO_ACTIVO);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ArtefactoDTO> consultarArtefactosPorUsuario(Long usuaId) {
		try {
			
			//Validar que tusuaId no sea null 
			if (usuaId == null) {
				throw new ZMessManager("El identificador del usuario esta nulo o vacío.");
			}
			
			return artefactoRepository.consultarArtefactosPorUsuario(usuaId, Constantes.ESTADO_ACTIVO);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

	}
	
	
}
