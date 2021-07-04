package co.com.edu.usbcali.pdg.domain;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */
@Entity
@Table(name = "tipo_usuario", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoUsuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tius_id", unique = true, nullable = false)
	@NotNull
	private Long tiusId;

	@Column(name = "estado")
	private String estado;
	@Column(name = "nombre")
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoUsuario")
	private List<Usuario> usuarios = new ArrayList<>();

}