package co.com.edu.usbcali.pdg.dto;

import java.io.Serializable;
import java.util.List;

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
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;

	private String direccion;

	private String estado;

	private String nombre;
	
	private String apellido;

	private Long usuaId;

	private Long tiusId_TipoUsuario;
	
	private String artefactosList;
	
	private List<ArtefactoDTO> artefactoDTOs;

	public UsuarioDTO(String codigo, String direccion, String nombre, Long usuaId, String artefactosList) {
		super();
		this.usuaId = usuaId;
		this.nombre = nombre;
		this.codigo = codigo;
		this.direccion = direccion;
		this.artefactosList = artefactosList;
	}
	
}
