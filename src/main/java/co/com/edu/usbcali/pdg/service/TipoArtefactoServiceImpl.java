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
import co.com.edu.usbcali.pdg.dto.TipoArtefactoDTO;
import co.com.edu.usbcali.pdg.exception.ZMessManager;
import co.com.edu.usbcali.pdg.mapper.TipoArtefactoMapper;
import co.com.edu.usbcali.pdg.repository.TipoArtefactoRepository;
import co.com.edu.usbcali.pdg.utility.Constantes;
import co.com.edu.usbcali.pdg.utility.Utilities;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service
@Slf4j
public class TipoArtefactoServiceImpl implements TipoArtefactoService {

	@Autowired
	private TipoArtefactoRepository tipoArtefactoRepository;
	
	@Autowired
	private TipoArtefactoService tipoArtefactoService;
	
	@Autowired
	private TipoArtefactoMapper tipoArtefactoMapper;
	
	@Autowired
	private ArtefactoService artefactoService;

	@Autowired
	private Validator validator;

	@Override
	public void validate(TipoArtefacto tipoArtefacto) throws ConstraintViolationException {

		Set<ConstraintViolation<TipoArtefacto>> constraintViolations = validator.validate(tipoArtefacto);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return tipoArtefactoRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoArtefacto> findAll() {
		log.debug("finding all TipoArtefacto instances");
		return tipoArtefactoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TipoArtefacto save(TipoArtefacto entity) throws Exception {
		log.debug("saving TipoArtefacto instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TipoArtefacto");
		}

		validate(entity);

		if (tipoArtefactoRepository.existsById(entity.getTiarId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return tipoArtefactoRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TipoArtefacto entity) throws Exception {
		log.debug("deleting TipoArtefacto instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TipoArtefacto");
		}

		if (entity.getTiarId() == null) {
			throw new ZMessManager().new EmptyFieldException("tiarId");
		}

		if (tipoArtefactoRepository.existsById(entity.getTiarId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getTiarId()).ifPresent(entidad -> {
			List<Artefacto> artefactos = entidad.getArtefactos();
			if (Utilities.validationsList(artefactos) == true) {
				throw new ZMessManager().new DeletingException("artefactos");
			}
		});

		tipoArtefactoRepository.deleteById(entity.getTiarId());
		log.debug("delete TipoArtefacto successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		log.debug("deleting TipoArtefacto instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("tiarId");
		}
		if (tipoArtefactoRepository.existsById(id)) {
			delete(tipoArtefactoRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TipoArtefacto update(TipoArtefacto entity) throws Exception {

		log.debug("updating TipoArtefacto instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TipoArtefacto");
		}

		validate(entity);

		if (tipoArtefactoRepository.existsById(entity.getTiarId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return tipoArtefactoRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TipoArtefacto> findById(Long tiarId) {
		log.debug("getting TipoArtefacto instance");
		return tipoArtefactoRepository.findById(tiarId);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void crearTipoArtefacto(TipoArtefactoDTO tipoArtefactoDTO) throws Exception {
		try {
			
			//Validar que TipoArtefactoDTO no sea null 
			if (tipoArtefactoDTO == null) {
				throw new ZMessManager("El tipo de artefacto esta nulo o vacío.");
			}
			
			TipoArtefacto tipoArtefacto = new TipoArtefacto();
			
			//Metodo que implementa las validaciones
			validarTipoArtefacto(tipoArtefactoDTO, tipoArtefacto);
			
			//Seteo el estado
			tipoArtefacto.setEstado(Constantes.ESTADO_ACTIVO);
			
			tipoArtefactoRepository.save(tipoArtefacto);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void actualizarTipoArtefacto(TipoArtefactoDTO tipoArtefactoDTO) throws Exception {
		try {
			
			//Validar que TipoArtefactoDTO no sea null 
			if (tipoArtefactoDTO == null) {
				throw new ZMessManager("El tipo de artefacto esta nulo o vacío.");
			}
			
			//Validar que tiarId no sea null 
			if (tipoArtefactoDTO.getTiarId() == null) {
				throw new ZMessManager("El identificador del tipo de artefacto esta nulo o vacío.");
			}
			
			//Validar que el tipo de artefacto exista
			Optional<TipoArtefacto> tipoArtefactoOpt = tipoArtefactoRepository.findById(tipoArtefactoDTO.getTiarId());
			
			if (!tipoArtefactoOpt.isPresent()) {
				throw new ZMessManager("El tipo de artefacto no fue encontrado.");
			}
			
			TipoArtefacto tipoArtefacto = tipoArtefactoOpt.get();
			
			//Metodo que implementa las validaciones
			validarTipoArtefacto(tipoArtefactoDTO, tipoArtefacto);
			
			//Seteo el estado
			tipoArtefacto.setEstado(Constantes.ESTADO_ACTIVO);
			
			tipoArtefactoService.update(tipoArtefacto);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarTipoArtefacto(TipoArtefactoDTO tipoArtefactoDTO) throws Exception {
		try {
			
			//Validar que TipoArtefactoDTO no sea null 
			if (tipoArtefactoDTO == null) {
				throw new ZMessManager("El tipo de artefacto esta nulo o vacío.");
			}
			
			//Validar que tiarId no sea null 
			if (tipoArtefactoDTO.getTiarId() == null) {
				throw new ZMessManager("El identificador del tipo de artefacto esta nulo o vacío.");
			}
			
			//Validar que el tipo de artefacto exista
			Optional<TipoArtefacto> tipoArtefactoOpt = tipoArtefactoRepository.findById(tipoArtefactoDTO.getTiarId());
			
			if (!tipoArtefactoOpt.isPresent()) {
				throw new ZMessManager("El tipo de artefacto no fue encontrado.");
			}
			
			TipoArtefacto tipoArtefacto = tipoArtefactoOpt.get();
			
			//Valido que el Tipo de artefacto no tenga artefactos activos asociados
			List<Artefacto> artefactosList = artefactoService.consultarArtefactosPorTipoArtefacto(tipoArtefacto.getTiarId());
			
			if (!artefactosList.isEmpty()) {
				throw new ZMessManager("El tipo de artefacto tiene artefactos asociados.");
			}
			
			//Seteo el estado
			tipoArtefacto.setEstado(Constantes.ESTADO_INACTIVO);
			
			tipoArtefactoService.update(tipoArtefacto);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TipoArtefactoDTO consultarTipoArtefacto(Long tiarId) throws Exception {
		try {
			
			//Validar que tiarId no sea null 
			if (tiarId == null) {
				throw new ZMessManager("El identificador del tipo de artefacto esta nulo o vacío.");
			}
			
			//Validar que el tipo de artefacto exista
			TipoArtefactoDTO tipoArtefactoDTO = tipoArtefactoRepository.consultarTipoArtefacto(tiarId, Constantes.ESTADO_ACTIVO);
			
			return tipoArtefactoDTO;
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	private void validarTipoArtefacto(TipoArtefactoDTO tipoArtefactoDTO, TipoArtefacto tipoArtefacto) {
		//validar que el codigo no sea null
		if (tipoArtefactoDTO.getCodigo() != null && !tipoArtefactoDTO.getCodigo().isBlank()) {
			
			//Seteo del codigo
			tipoArtefacto.setCodigo(tipoArtefactoDTO.getCodigo());
			
		} else {
			throw new ZMessManager("El codigo se encuentra nulo o vacío");
		}
		
		//Validar que el nombre no sea null 
		if (tipoArtefactoDTO.getNombre() != null && !tipoArtefactoDTO.getNombre().isBlank()) {
			
			//Seteo el nombre
			tipoArtefacto.setNombre(tipoArtefactoDTO.getNombre());
			
		} else {
			throw new ZMessManager("El nombre se encuentra nulo o vacío.");
		}
		
	}
	
	@Override
	public List<TipoArtefactoDTO> consultarTipoArtefactosActivos() throws Exception {
		try {
			
			return tipoArtefactoMapper.listTipoArtefactoToListTipoArtefactoDTO(tipoArtefactoRepository.findByEstado(Constantes.ESTADO_ACTIVO));
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
