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
public class TipoArtefactoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	
	private String estado;
	
	private String nombre;
	
	private Long tiarId;
}
