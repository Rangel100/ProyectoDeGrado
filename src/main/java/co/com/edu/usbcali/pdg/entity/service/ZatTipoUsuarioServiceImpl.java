package co.com.edu.usbcali.pdg.entity.service;

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

import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.TipoUsuarioDTO;
import co.com.edu.usbcali.pdg.exception.ZMessManager;
import co.com.edu.usbcali.pdg.mapper.TipoUsuarioMapper;
import co.com.edu.usbcali.pdg.repository.TipoUsuarioRepository;
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
public class ZatTipoUsuarioServiceImpl implements ZatTipoUsuarioService {

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	
	@Autowired
	private ZatTipoUsuarioService tipoUsuarioService;
	
	@Autowired
	private TipoUsuarioMapper tipoUsuarioMapper;
	
	@Autowired
	private ZatUsuarioService usuarioService;

	@Autowired
	private Validator validator;

	@Override
	public void validate(TipoUsuario tipoUsuario) throws ConstraintViolationException {

		Set<ConstraintViolation<TipoUsuario>> constraintViolations = validator.validate(tipoUsuario);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return tipoUsuarioRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoUsuario> findAll() {
		log.debug("finding all TipoUsuario instances");
		return tipoUsuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TipoUsuario save(TipoUsuario entity) throws Exception {
		log.debug("saving TipoUsuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TipoUsuario");
		}

		validate(entity);

		if (tipoUsuarioRepository.existsById(entity.getTiusId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return tipoUsuarioRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TipoUsuario entity) throws Exception {
		log.debug("deleting TipoUsuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TipoUsuario");
		}

		if (entity.getTiusId() == null) {
			throw new ZMessManager().new EmptyFieldException("tiusId");
		}

		if (tipoUsuarioRepository.existsById(entity.getTiusId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getTiusId()).ifPresent(entidad -> {
			List<Usuario> usuarios = entidad.getUsuarios();
			if (Utilities.validationsList(usuarios) == true) {
				throw new ZMessManager().new DeletingException("usuarios");
			}
		});

		tipoUsuarioRepository.deleteById(entity.getTiusId());
		log.debug("delete TipoUsuario successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		log.debug("deleting TipoUsuario instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("tiusId");
		}
		if (tipoUsuarioRepository.existsById(id)) {
			delete(tipoUsuarioRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TipoUsuario update(TipoUsuario entity) throws Exception {

		log.debug("updating TipoUsuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TipoUsuario");
		}

		validate(entity);

		if (tipoUsuarioRepository.existsById(entity.getTiusId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return tipoUsuarioRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TipoUsuario> findById(Long tiusId) {
		log.debug("getting TipoUsuario instance");
		return tipoUsuarioRepository.findById(tiusId);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void crearTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		try {
			
			//Validar que tipoUsuarioDTO no sea null 
			if (tipoUsuarioDTO == null) {
				throw new ZMessManager("El tipo de usuario esta nulo o vacío.");
			}
			
			TipoUsuario tipoUsuario = new TipoUsuario();
			
			//Metodo que implementa las validaciones
			validarTipoUsuario(tipoUsuarioDTO, tipoUsuario);
			
			//Seteo el estado
			tipoUsuario.setEstado(Constantes.ESTADO_ACTIVO);
			
			tipoUsuarioRepository.save(tipoUsuario);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void actualizarTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		try {
			
			//Validar que tipoUsuarioDTO no sea null 
			if (tipoUsuarioDTO == null) {
				throw new ZMessManager("El tipo de usuario esta nulo o vacío.");
			}
			
			//Validar que tiusId no sea null 
			if (tipoUsuarioDTO.getTiusId() == null) {
				throw new ZMessManager("El identificador del tipo de usuario esta nulo o vacío.");
			}
			
			//Validar que el tipo de usuario exísta
			Optional<TipoUsuario> tipoUsuarioOpt = tipoUsuarioRepository.findById(tipoUsuarioDTO.getTiusId());
			
			if (!tipoUsuarioOpt.isPresent()) {
				throw new ZMessManager("El tipo de usuario no fue encontrado.");
			}
			
			TipoUsuario tipoUsuario = tipoUsuarioOpt.get();
			
			//Metodo que implementa las validaciones
			validarTipoUsuario(tipoUsuarioDTO, tipoUsuario);
			
			//Seteo el estado
			tipoUsuario.setEstado(Constantes.ESTADO_ACTIVO);
			
			tipoUsuarioService.update(tipoUsuario);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		try {
			
			//Validar que tipoUsuarioDTO no sea null 
			if (tipoUsuarioDTO == null) {
				throw new ZMessManager("El tipo de usuario esta nulo o vacío.");
			}
			
			//Validar que tiusId no sea null 
			if (tipoUsuarioDTO.getTiusId() == null) {
				throw new ZMessManager("El identificador del tipo de usuario esta nulo o vacío.");
			}
			
			//Validar que el tipo de usuario exísta
			Optional<TipoUsuario> tipoUsuarioOpt = tipoUsuarioRepository.findById(tipoUsuarioDTO.getTiusId());
			
			if (!tipoUsuarioOpt.isPresent()) {
				throw new ZMessManager("El tipo de usuario no fue encontrado.");
			}
			
			TipoUsuario tipoUsuario = tipoUsuarioOpt.get();
			
			//Validar que el tipos de usuario no tenga usuarios asociados
			List<Usuario> usuariosList = usuarioService.consultarUsuariosPorTipoUsuario(tipoUsuario.getTiusId());
			
			if (!usuariosList.isEmpty()) {
				throw new ZMessManager("El tipo de usuario tiene usuarios asociados.");
			}
			
			//Seteo el estado
			tipoUsuario.setEstado(Constantes.ESTADO_INACTIVO);
			
			tipoUsuarioService.update(tipoUsuario);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TipoUsuarioDTO consultarTipoUsuario(Long tiusId) throws Exception {
		try {
			
			//Validar que tiusId no sea null 
			if (tiusId == null) {
				throw new ZMessManager("El identificador del tipo de usuario esta nulo o vacío.");
			}
			
			TipoUsuarioDTO tipoUsuarioDTO = tipoUsuarioRepository.consultarTipoUsuario(tiusId, Constantes.ESTADO_ACTIVO);
			
			return tipoUsuarioDTO;
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	private void validarTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO, TipoUsuario tipoUsuario) {
		//Validar que el nombre no sea null 
		if (tipoUsuarioDTO.getNombre() != null && !tipoUsuarioDTO.getNombre().isBlank()) {
			
			Optional<TipoUsuario> tipoUsuarioOpt = tipoUsuarioRepository.findByNombre(tipoUsuarioDTO.getNombre().toUpperCase());
			
			if (tipoUsuarioOpt.isPresent()) {
				throw new ZMessManager("Ya exíste un tipo de usuario con ese nombre.");
			}
			
			//Seteo el nombre
			tipoUsuario.setNombre(tipoUsuarioDTO.getNombre().toUpperCase());
			
		} else {
			throw new ZMessManager("El nombre se encuentra nulo o vacío.");
		}
		
	}
	
	@Override
	public List<TipoUsuarioDTO> consultarTipoUsuarioActivos() throws Exception {
		try {
			
			return tipoUsuarioMapper.listTipoUsuarioToListTipoUsuarioDTO(tipoUsuarioRepository.findByEstado(Constantes.ESTADO_ACTIVO));
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
