package co.com.edu.usbcali.pdg.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org
 *         www.zathuracode.org
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtefactoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long arteId;
	
	private String codigo;
	
	private String estado;
	
	private String url;
	
	private Long tiarId_TipoArtefacto;
	
	private Long usuaId_Usuario;
	
	private String codigoUsuario;
	
	private String nombreTipoArtefacto;

	public ArtefactoDTO(Long arteId, String codigo, String url, Long tiarId_TipoArtefacto, String codigoUsuario) {
		super();
		this.arteId = arteId;
		this.codigo = codigo;
		this.url = url;
		this.tiarId_TipoArtefacto = tiarId_TipoArtefacto;
		this.codigoUsuario = codigoUsuario;
	}
	
	public ArtefactoDTO(Long arteId, String codigo, String url, String nombreTipoArtefacto) {
		super();
		this.arteId = arteId;
		this.codigo = codigo;
		this.url = url;
		this.nombreTipoArtefacto = nombreTipoArtefacto;
	}
	
}
