package co.com.edu.usbcali.pdg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long arteId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tiar_id")
	@NotNull
	private TipoArtefacto tipoArtefacto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usua_id")
	@NotNull
	private Usuario usuario;

	@NotNull
	@Column(name = "codigo")
	private String codigo;

	@NotNull
	@Column(name = "estado")
	private String estado;

	@Column(name = "url")
	private String url;
}
