package co.com.edu.usbcali.pdg.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import co.com.edu.usbcali.pdg.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@NamedNativeQueries({
	@NamedNativeQuery(name = "Usuario.consultarUsuario", query = "", resultSetMapping = "consultarUsuario")
	
})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="consultarUsuario", 
	classes = { @ConstructorResult(targetClass = UsuarioDTO.class,
	columns = {
			@ColumnResult(name = "usuaId", type = Long.class),
			@ColumnResult(name = "nombre", type = String.class),
			@ColumnResult(name = "codigo", type = String.class),
			@ColumnResult(name = "direccion", type = String.class),
			@ColumnResult(name = "artefactosList", type = String.class)
	}) })

})

@Entity
@Table(name = "usuario", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usua_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long usuaId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tius_id")
	@NotNull
	private TipoUsuario tipoUsuario;

	@NotNull
	@Column(name = "codigo")
	private String codigo;
	
	@NotNull
	@Column(name = "direccion")
	private String direccion;
	
	@NotNull
	@Column(name = "estado")
	private String estado;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<Artefacto> artefactos = new ArrayList<>();

}