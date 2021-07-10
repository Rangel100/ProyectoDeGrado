package co.com.edu.usbcali.pdg.service;

import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface ArtefactoService extends GenericService<Artefacto, Long> {

	void crearArtefacto(ArtefactoDTO artefactoDTO) throws Exception;

	void eliminarArtefactosPorUsuario(UsuarioDTO usuarioDTO) throws Exception;
}
