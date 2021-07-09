package co.com.edu.usbcali.pdg.service;

import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.dto.TipoUsuarioDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface TipoUsuarioService extends GenericService<TipoUsuario, Long> {

	void crearTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) throws Exception;
}
