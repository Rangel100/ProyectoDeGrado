package co.com.edu.usbcali.pdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.edu.usbcali.pdg.domain.Artefacto;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface ArtefactoRepository extends JpaRepository<Artefacto, Long> {
	
	List<Artefacto> findByUsuario_usuaId(Long usuaId);
}
