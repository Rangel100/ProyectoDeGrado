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
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.exception.ZMessManager;
import co.com.edu.usbcali.pdg.repository.UsuarioRepository;
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

}
