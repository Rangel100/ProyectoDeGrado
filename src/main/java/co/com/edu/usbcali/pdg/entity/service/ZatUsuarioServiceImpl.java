package co.com.edu.usbcali.pdg.entity.service;

import java.util.ArrayList;
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
import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;
import co.com.edu.usbcali.pdg.exception.ZMessManager;
import co.com.edu.usbcali.pdg.mapper.ArtefactoMapper;
import co.com.edu.usbcali.pdg.repository.UsuarioRepository;
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
public class ZatUsuarioServiceImpl implements ZatUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ZatUsuarioService usuarioService;
	
	@Autowired
	private ZatTipoUsuarioService tipoUsuarioService;
	
	@Autowired
	private ZatArtefactoService artefactoService;
	
	@Autowired
	private ArtefactoMapper artefactoMapper;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Usuario usuario) throws ConstraintViolationException {

		Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return usuarioRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		log.debug("finding all Usuario instances");
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario save(Usuario entity) throws Exception {
		log.debug("saving Usuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Usuario");
		}

		validate(entity);

		if (usuarioRepository.existsById(entity.getUsuaId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return usuarioRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Usuario entity) throws Exception {
		log.debug("deleting Usuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Usuario");
		}

		if (entity.getUsuaId() == null) {
			throw new ZMessManager().new EmptyFieldException("usuaId");
		}

		if (usuarioRepository.existsById(entity.getUsuaId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getUsuaId()).ifPresent(entidad -> {
			List<Artefacto> artefactos = entidad.getArtefactos();
			if (Utilities.validationsList(artefactos) == true) {
				throw new ZMessManager().new DeletingException("artefactos");
			}
		});

		usuarioRepository.deleteById(entity.getUsuaId());
		log.debug("delete Usuario successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		log.debug("deleting Usuario instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("usuaId");
		}
		if (usuarioRepository.existsById(id)) {
			delete(usuarioRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario update(Usuario entity) throws Exception {

		log.debug("updating Usuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Usuario");
		}

		validate(entity);

		if (usuarioRepository.existsById(entity.getUsuaId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return usuarioRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long usuaId) {
		log.debug("getting Usuario instance");
		return usuarioRepository.findById(usuaId);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void crearUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			
			//Validar que usuarioDTO no sea null 
			if (usuarioDTO == null) {
				throw new ZMessManager("El usuario esta nulo o vacío.");
			}
			
			Usuario usuario = new Usuario();
			
			//Metodo que implementa las validaciones
			validarUsuario(usuarioDTO, usuario);
			
			//Seteo el estado
			usuario.setEstado(Constantes.ESTADO_ACTIVO);
			
			usuarioRepository.save(usuario);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void actualizarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			
			//Validar que usuarioDTO no sea null 
			if (usuarioDTO == null) {
				throw new ZMessManager("El usuario esta nulo o vacío.");
			}
			
			//Validar que el usuaId no sea nulo
			if (usuarioDTO.getUsuaId() == null ) {
				throw new ZMessManager("El identificador del usuario esta nulo o vacío.");
			}
			
			//Validar que el usuario exísta
			Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioDTO.getUsuaId());
			
			if (!usuarioOpt.isPresent()) {
				throw new ZMessManager("El usuario no fue encontrado.");
			}
			
			Usuario usuario = usuarioOpt.get();
			
			//Metodo que implementa las validaciones
			validarUsuario(usuarioDTO, usuario);
			
			//Seteo el estado
			usuario.setEstado(Constantes.ESTADO_ACTIVO);
			
			usuarioService.update(usuario);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			
			//Validar que usuarioDTO no sea null 
			if (usuarioDTO == null) {
				throw new ZMessManager("El usuario esta nulo o vacío.");
			}
			
			//Validar que el usuaId no sea nulo
			if (usuarioDTO.getUsuaId() == null ) {
				throw new ZMessManager("El identificador del usuario esta nulo o vacío.");
			}
			
			//Validar que el usuario exísta
			Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioDTO.getUsuaId());
			
			if (!usuarioOpt.isPresent()) {
				throw new ZMessManager("El usuario no fue encontrado.");
			}
			
			Usuario usuario = usuarioOpt.get();
			
			//Seteo el estado inactivo
			usuario.setEstado(Constantes.ESTADO_INACTIVO);
			
			usuarioService.update(usuario);
			
			//inactivo los artefactos que el usuario tenga registrados
			artefactoService.eliminarArtefactosPorUsuario(usuarioDTO);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UsuarioDTO consultarUsuario(Long usuaId) throws Exception {
		try {
			
			//Validar que el usuaId no sea null 
			if (usuaId == null) {
				throw new ZMessManager("El identificador del usuario esta nulo o vacío.");
			}
			
			//Validar que el usuario exísta
			UsuarioDTO usuarioDTO = usuarioRepository.consultarUsuario(usuaId, Constantes.ESTADO_ACTIVO);
			
			if (usuarioDTO.getArtefactosList() != null) {
				
				List<ArtefactoDTO> artefactoDTOs = new ArrayList<>();
				String[] artefactosList = usuarioDTO.getArtefactosList().split(",");
				
				for (int i = 0; i < artefactosList.length; i++) {
					
					Optional<Artefacto> artefactoOpt =  artefactoService.findById(Long.parseLong(artefactosList[i]));
					
					if (!artefactoOpt.isPresent()) {
						throw new ZMessManager("El artefacto no exíste.");
					}
					
					Artefacto artefacto = artefactoOpt.get();
					ArtefactoDTO artefactoDTO = artefactoMapper.artefactoToArtefactoDTO(artefacto);
					artefactoDTOs.add(artefactoDTO);
					
				}
				
				usuarioDTO.setArtefactoDTOs(artefactoDTOs);
				
			}
			
			return usuarioDTO;
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	private void validarUsuario(UsuarioDTO usuarioDTO, Usuario usuario) {
		//Validar que el tipo de usuario no sea null 
		if (usuarioDTO.getTiusId_TipoUsuario() != null) {
			
			//Valido que el tipo de usuario exista
			Optional<TipoUsuario> tipoUsuarioOpt = tipoUsuarioService.findById(usuarioDTO.getTiusId_TipoUsuario());
			
			if (!tipoUsuarioOpt.isPresent()) {
				throw new ZMessManager("El tipo de usuario seleccionado no exíste.");
			}
			
			TipoUsuario tipoUsuario = tipoUsuarioOpt.get();
			
			//Seteo el tipo de usuario
			usuario.setTipoUsuario(tipoUsuario);
			
		} else {
			throw new ZMessManager("El tipo de usuario se encuentra nulo o vacío");
		}
		
		//validar que el codigo no sea null
		if (usuarioDTO.getCodigo() != null && !usuarioDTO.getCodigo().isBlank()) {
			
			//Validar si el correo es diferente para crear o actualizar
			if (!usuarioDTO.getCodigo().equals(usuario.getCodigo())) {
				
				//Valido que no existan mas usuarios con ese codigo
				List<Usuario> usuarioList = usuarioRepository.findByCodigo(usuarioDTO.getCodigo().trim());
				
				if (!usuarioList.isEmpty()) {
					throw new ZMessManager("El codigo ingresado ya se encuentra en uso.");
				}
				
			}
			
			//Seteo del codigo
			usuario.setCodigo(usuarioDTO.getCodigo());
			
		} else {
			throw new ZMessManager("El codigo se encuentra nulo o vacío");
		}
		
		//Validar que el direccion no sea null 
		if (usuarioDTO.getDireccion() != null && !usuarioDTO.getDireccion().isBlank()) {
			
			//Seteo el direccion
			usuario.setDireccion(usuarioDTO.getDireccion());
			
		} else {
			throw new ZMessManager("La direccion se encuentra nulo o vacío.");
		}
		
		//Validar que el nombre no sea null 
		if (usuarioDTO.getNombre() != null && !usuarioDTO.getNombre().isBlank()) {
			
			//Validar si trae apellido
			if (usuarioDTO.getApellido() != null && !usuarioDTO.getApellido().isBlank()) {
				usuario.setNombre(usuarioDTO.getNombre() + " " + usuarioDTO.getApellido().trim());
			}else {
				
				//Seteo el nombre
				usuario.setNombre(usuarioDTO.getNombre());
			}
			

		} else {
			throw new ZMessManager("El nombre se encuentra nulo o vacío.");
		}
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> consultarUsuariosPorTipoUsuario(Long tiusId) {
		try {
			//validar que el tiusId no sea null
			if (tiusId == null) {
				throw new ZMessManager("El identificador del tipo de usuario se encuentra nulo o vacío.");
			}
			
			return usuarioRepository.findByTipoUsuario_tiusIdAndEstado(tiusId, Constantes.ESTADO_ACTIVO);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> consultarUsuariosPorCodigo(String codigo) {
		try {
			//validar que el codigo no sea null
			if (codigo == null) {
				throw new ZMessManager("El codigo se encuentra nulo o vacío.");
			}
			
			return usuarioRepository.findByCodigo(codigo);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDTO> consultarUsuarios(UsuarioDTO usuarioDTO) {
		try {
			//validar que el usuarioDTO no sea null
			if (usuarioDTO == null) {
				throw new ZMessManager("El usuario está nulo");
			}
			
			//Se realiza las validciones para los filtros correspondientes
			String nombre = usuarioDTO.getNombre() == null || usuarioDTO.getNombre().isBlank() ? "-1" : usuarioDTO.getNombre().trim();
			String codigo = usuarioDTO.getCodigo() == null || usuarioDTO.getCodigo().isBlank() ? "-1" : usuarioDTO.getCodigo().trim();
		
			return usuarioRepository.consultarUsuarios(Constantes.ESTADO_ACTIVO, nombre, codigo);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO consultarUsuariosPorCodigoOrm(String codigo) {
		try {
			//validar que el codigo no sea null
			if (codigo == null) {
				throw new ZMessManager("El codigo se encuentra nulo o vacío.");
			}
			
			return usuarioRepository.consultarUsuariosPorCodigoOrm(codigo, Constantes.ESTADO_ACTIVO);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
