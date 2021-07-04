package co.com.edu.usbcali.pdg.service;

import co.com.edu.usbcali.pdg.domain.*;
import co.com.edu.usbcali.pdg.exception.*;
import co.com.edu.usbcali.pdg.repository.*;
import co.com.edu.usbcali.pdg.utility.Utilities;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
@Slf4j
public class ArtefactoServiceImpl implements ArtefactoService {
    @Autowired
    private ArtefactoRepository artefactoRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(Artefacto artefacto)
        throws ConstraintViolationException {
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
}
