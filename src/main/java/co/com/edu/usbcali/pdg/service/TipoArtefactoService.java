package co.com.edu.usbcali.pdg.service;

import co.com.edu.usbcali.pdg.domain.TipoArtefacto;
import co.com.edu.usbcali.pdg.dto.TipoArtefactoDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface TipoArtefactoService extends GenericService<TipoArtefacto, Long> {

	void crearTipoArtefacto(TipoArtefactoDTO TipoArtefactoDTO) throws Exception;
}
