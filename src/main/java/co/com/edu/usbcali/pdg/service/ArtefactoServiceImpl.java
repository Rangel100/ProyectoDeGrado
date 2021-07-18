package co.com.edu.usbcali.pdg.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

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
	private ArtefactoService artefactoService;
	
	@Autowired
	private TipoArtefactoService tipoArtefactoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private Validator validator;

	@Override
	public void validate(Artefacto artefacto) throws ConstraintViolationException {
		Set<ConstraintViolation<Artefacto>> constraintViolations = validator.validate(artefacto);

		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return artefactoRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Artefacto> findAll() {
		log.debug("finding all Artefacto instances");

		return artefactoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Artefacto save(Artefacto entity) throws Exception {
		log.debug("saving Artefacto instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Artefacto");
		}

		validate(entity);

		if (artefactoRepository.existsById(entity.getArteId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return artefactoRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Artefacto entity) throws Exception {
		log.debug("deleting Artefacto instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Artefacto");
		}

		if (entity.getArteId() == null) {
			throw new ZMessManager().new EmptyFieldException("arteId");
		}

		if (artefactoRepository.existsById(entity.getArteId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		artefactoRepository.deleteById(entity.getArteId());
		log.debug("delete Artefacto successful");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		log.debug("deleting Artefacto instance");

		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("arteId");
		}

		if (artefactoRepository.existsById(id)) {
			delete(artefactoRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Artefacto update(Artefacto entity) throws Exception {
		log.debug("updating Artefacto instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Artefacto");
		}

		validate(entity);

		if (artefactoRepository.existsById(entity.getArteId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return artefactoRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Artefacto> findById(Long arteId) {
		log.debug("getting Artefacto instance");

		return artefactoRepository.findById(arteId);
	}
	
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
				throw new ZMessManager("El identificador del artefacto esta nulo o vacío.");
			}
			
			Artefacto artefacto = artefactoOpt.get();
			
			//Metodo que implementa las validaciones
			validarArtefacto(artefactoDTO, artefacto);
			
			//Seteo el estado
			artefacto.setEstado(Constantes.ESTADO_ACTIVO);
			
			artefactoService.update(artefacto);
			
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
				throw new ZMessManager("El identificador del artefacto esta nulo o vacío.");
			}
			
			Artefacto artefacto = artefactoOpt.get();
			
			//Seteo el estado inactivo
			artefacto.setEstado(Constantes.ESTADO_INACTIVO);
			
			artefactoService.update(artefacto);
			
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
		if (artefactoDTO.getCodigo() != null && !artefactoDTO.getCodigo().isBlank()) {
			
			//Seteo del codigo
			artefacto.setCodigo(artefactoDTO.getCodigo());
			
		} else {
			throw new ZMessManager("El codigo se encuentra nulo o vacío");
		}
		
		//Validar que el url no sea null 
		if (artefactoDTO.getUrl() != null && !artefactoDTO.getUrl().isBlank()) {
			
			//Seteo el url
			artefacto.setUrl(artefactoDTO.getUrl());
			
		} else {
			throw new ZMessManager("El url se encuentra nulo o vacío.");
		}
		
		//Validar que el url no sea null 
		if (artefactoDTO.getTiarId_TipoArtefacto() != null) {
			
			//Valido que el tipo de artefacto exista
			Optional<TipoArtefacto> tipoArtefactoOpt = tipoArtefactoService.findById(artefactoDTO.getTiarId_TipoArtefacto());
			
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
				throw new ZMessManager("El usuario seleccionado seleccionado no exíste.");
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
					
					artefactoService.update(artefacto);
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
		log.debug("consultarArtefactosPorTipoArtefacto instances");
		try {
			
			return artefactoRepository.findByTipoArtefacto_tiarIdAndEstado(tiarId, Constantes.ESTADO_ACTIVO);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ArtefactoDTO> consultarArtefactosPorUsuario(Long usuaId) {
		log.debug("consultarArtefactosPorUsuario instances");
		try {
			
			return artefactoRepository.consultarArtefactosPorUsuario(usuaId, Constantes.ESTADO_ACTIVO);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

	}
	
	
}
