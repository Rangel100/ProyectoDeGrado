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
public class TipoUsuarioServiceImpl implements TipoUsuarioService{

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(TipoUsuario tipoUsuario)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<TipoUsuario>> constraintViolations =validator.validate(tipoUsuario);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return tipoUsuarioRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoUsuario> findAll(){
		log.debug("finding all TipoUsuario instances");
       	return tipoUsuarioRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public TipoUsuario save(TipoUsuario entity) throws Exception {
		log.debug("saving TipoUsuario instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("TipoUsuario");
		}
		
		validate(entity);	
	
		if(tipoUsuarioRepository.existsById(entity.getTiusId())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return tipoUsuarioRepository.save(entity);
	   
    }
			
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(TipoUsuario entity) throws Exception {
            	log.debug("deleting TipoUsuario instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("TipoUsuario");
	    		}
    	
                                if(entity.getTiusId()==null){
                    throw new ZMessManager().new EmptyFieldException("tiusId");
                    }
                        
            if(tipoUsuarioRepository.existsById(entity.getTiusId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getTiusId()).ifPresent(entidad->{	            	
	                													List<Usuario> usuarios = entidad.getUsuarios();
							                    if(Utilities.validationsList(usuarios)==true){
                       	 	throw new ZMessManager().new DeletingException("usuarios");
                        }
	                	            });
                       

           
            
            tipoUsuarioRepository.deleteById(entity.getTiusId());
            log.debug("delete TipoUsuario successful");
            
           
            	
            }
            
            @Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(Long id) throws Exception {            
            	log.debug("deleting TipoUsuario instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("tiusId");
            	}
            	if(tipoUsuarioRepository.existsById(id)){
           			delete(tipoUsuarioRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public TipoUsuario update(TipoUsuario entity) throws Exception {

				log.debug("updating TipoUsuario instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("TipoUsuario");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(tipoUsuarioRepository.existsById(entity.getTiusId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return tipoUsuarioRepository.save(entity);
	        
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<TipoUsuario> findById(Long tiusId) {            
            	log.debug("getting TipoUsuario instance");
            	return tipoUsuarioRepository.findById(tiusId);
            }
			
}
