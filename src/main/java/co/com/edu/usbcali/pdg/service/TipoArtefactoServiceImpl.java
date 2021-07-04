package  co.com.edu.usbcali.pdg.service;


import java.math.*;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.com.edu.usbcali.pdg.exception.*;
import co.com.edu.usbcali.pdg.repository.*;
import co.com.edu.usbcali.pdg.utility.Utilities;

import co.com.edu.usbcali.pdg.domain.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import lombok.extern.slf4j.Slf4j;

/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
* 
*/

@Scope("singleton")
@Service
@Slf4j
public class TipoArtefactoServiceImpl implements TipoArtefactoService{

	@Autowired
	private TipoArtefactoRepository tipoArtefactoRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(TipoArtefacto tipoArtefacto)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<TipoArtefacto>> constraintViolations =validator.validate(tipoArtefacto);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return tipoArtefactoRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoArtefacto> findAll(){
		log.debug("finding all TipoArtefacto instances");
       	return tipoArtefactoRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public TipoArtefacto save(TipoArtefacto entity) throws Exception {
		log.debug("saving TipoArtefacto instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("TipoArtefacto");
		}
		
		validate(entity);	
	
		if(tipoArtefactoRepository.existsById(entity.getTiarId())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return tipoArtefactoRepository.save(entity);
	   
    }
			
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(TipoArtefacto entity) throws Exception {
            	log.debug("deleting TipoArtefacto instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("TipoArtefacto");
	    		}
    	
                                if(entity.getTiarId()==null){
                    throw new ZMessManager().new EmptyFieldException("tiarId");
                    }
                        
            if(tipoArtefactoRepository.existsById(entity.getTiarId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getTiarId()).ifPresent(entidad->{	            	
	                													List<Artefacto> artefactos = entidad.getArtefactos();
							                    if(Utilities.validationsList(artefactos)==true){
                       	 	throw new ZMessManager().new DeletingException("artefactos");
                        }
	                	            });
                       

           
            
            tipoArtefactoRepository.deleteById(entity.getTiarId());
            log.debug("delete TipoArtefacto successful");
            
           
            	
            }
            
            @Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(Long id) throws Exception {            
            	log.debug("deleting TipoArtefacto instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("tiarId");
            	}
            	if(tipoArtefactoRepository.existsById(id)){
           			delete(tipoArtefactoRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public TipoArtefacto update(TipoArtefacto entity) throws Exception {

				log.debug("updating TipoArtefacto instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("TipoArtefacto");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(tipoArtefactoRepository.existsById(entity.getTiarId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return tipoArtefactoRepository.save(entity);
	        
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<TipoArtefacto> findById(Long tiarId) {            
            	log.debug("getting TipoArtefacto instance");
            	return tipoArtefactoRepository.findById(tiarId);
            }
			
}
