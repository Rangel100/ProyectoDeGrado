package co.com.edu.usbcali.pdg.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org
 *         www.zathuracode.org
 *
 */
@Entity
@Table(name = "artefacto", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artefacto implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "arte_id", unique = true, nullable = false)
	@NotNull
	private Long arteId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tiar_id")
	@NotNull
	private TipoArtefacto tipoArtefacto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usua_id")
	@NotNull
	private Usuario usuario;
	@Column(name = "codigo")
	private String codigo;
	@Column(name = "estado")
	private String estado;
	@Column(name = "url")
	private String url;
}
