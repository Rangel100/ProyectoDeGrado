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

import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.domain.TipoArtefacto;
import co.com.edu.usbcali.pdg.exception.ZMessManager;
import co.com.edu.usbcali.pdg.repository.TipoArtefactoRepository;
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
public class ZatTipoArtefactoServiceImpl implements ZatTipoArtefactoService {

	@Autowired
	private TipoArtefactoRepository tipoArtefactoRepository;

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

}
