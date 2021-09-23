package co.com.edu.usbcali.pdg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.TipoUsuarioDTO;
import co.com.edu.usbcali.pdg.entity.service.ZatTipoUsuarioService;
import co.com.edu.usbcali.pdg.exception.ZMessManager;
import co.com.edu.usbcali.pdg.mapper.TipoUsuarioMapper;
import co.com.edu.usbcali.pdg.repository.TipoUsuarioRepository;
import co.com.edu.usbcali.pdg.utility.Constantes;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service
@Slf4j
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	
	@Autowired
	private ZatTipoUsuarioService zatTipoUsuarioService;
	
	@Autowired
	private TipoUsuarioMapper tipoUsuarioMapper;
	
	@Autowired
	private UsuarioService usuarioService;
	
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
			
			zatTipoUsuarioService.update(tipoUsuario);
			
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
			
			zatTipoUsuarioService.update(tipoUsuario);
			
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
		if (tipoUsuarioDTO.getNombre() != null && !tipoUsuarioDTO.getNombre().isEmpty()) {
			
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
