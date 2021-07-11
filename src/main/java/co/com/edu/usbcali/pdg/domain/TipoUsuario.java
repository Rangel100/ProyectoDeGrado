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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import co.com.edu.usbcali.pdg.dto.TipoUsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@NamedNativeQueries({
	@NamedNativeQuery(name = "TipoUsuario.consultarTipoUsuario", query = "", resultSetMapping = "consultarTipoUsuario")
	
})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="consultarTipoUsuario", 
	classes = { @ConstructorResult(targetClass = TipoUsuarioDTO.class,
	columns = {
			@ColumnResult(name = "tiusId", type = Long.class),
			@ColumnResult(name = "nombre", type = String.class)
	}) })

})

@Entity
@Table(name = "tipo_usuario", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoUsuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tius_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tiusId;

	@NotNull
	@Column(name = "estado")
	private String estado;

	@NotNull
	@Column(name = "nombre")
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoUsuario")
	private List<Usuario> usuarios = new ArrayList<>();

}