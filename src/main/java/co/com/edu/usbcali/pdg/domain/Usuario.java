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
@Table(name = "usuario", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usua_id", unique = true, nullable = false)
	@NotNull
	private Long usuaId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tius_id")
	@NotNull
	private TipoUsuario tipoUsuario;

	@Column(name = "codigo")
	private String codigo;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "estado")
	private String estado;
	@Column(name = "nombre")
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<Artefacto> artefactos = new ArrayList<>();

}