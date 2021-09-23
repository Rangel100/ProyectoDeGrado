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
import co.com.edu.usbcali.pdg.dto.TipoArtefactoDTO;
import co.com.edu.usbcali.pdg.entity.service.ZatTipoArtefactoService;
import co.com.edu.usbcali.pdg.exception.ZMessManager;
import co.com.edu.usbcali.pdg.mapper.TipoArtefactoMapper;
import co.com.edu.usbcali.pdg.repository.TipoArtefactoRepository;
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
public class TipoArtefactoServiceImpl implements TipoArtefactoService {

	@Autowired
	private TipoArtefactoRepository tipoArtefactoRepository;
	
	@Autowired
	private ZatTipoArtefactoService zatTipoArtefactoService;
	
	@Autowired
	private TipoArtefactoMapper tipoArtefactoMapper;
	
	@Autowired
	private ArtefactoService artefactoService;
	
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
			
			zatTipoArtefactoService.update(tipoArtefacto);
			
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
			
			zatTipoArtefactoService.update(tipoArtefacto);
			
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
		if (tipoArtefactoDTO.getCodigo() != null && !tipoArtefactoDTO.getCodigo().isEmpty()) {
			
			//Seteo del codigo
			tipoArtefacto.setCodigo(tipoArtefactoDTO.getCodigo());
			
		} else {
			throw new ZMessManager("El codigo se encuentra nulo o vacío");
		}
		
		//Validar que el nombre no sea null 
		if (tipoArtefactoDTO.getNombre() != null && !tipoArtefactoDTO.getNombre().isEmpty()) {
			
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
