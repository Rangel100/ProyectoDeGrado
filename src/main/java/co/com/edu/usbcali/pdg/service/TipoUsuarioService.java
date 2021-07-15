package co.com.edu.usbcali.pdg.service;

import java.util.List;

import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.dto.TipoUsuarioDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface TipoUsuarioService extends GenericService<TipoUsuario, Long> {

	void crearTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) throws Exception;

	void actualizarTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) throws Exception;

	void eliminarTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) throws Exception;

	TipoUsuarioDTO consultarTipoUsuario(Long tiusId) throws Exception;

	List<TipoUsuarioDTO> consultarTipoUsuarioActivos() throws Exception;

}
