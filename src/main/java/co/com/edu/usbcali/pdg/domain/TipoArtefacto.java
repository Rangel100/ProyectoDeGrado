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

import co.com.edu.usbcali.pdg.dto.TipoArtefactoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@NamedNativeQueries({
	@NamedNativeQuery(name = "TipoArtefacto.consultarTipoArtefacto", query = "", resultSetMapping = "consultarTipoArtefacto")
	
})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="consultarTipoArtefacto",
	classes = { @ConstructorResult(targetClass = TipoArtefactoDTO.class,
	columns = {
			@ColumnResult(name = "tiarId", type = Long.class),
			@ColumnResult(name = "codigo", type = String.class),
			@ColumnResult(name = "nombre", type = String.class)
	}) })

})

@Entity
@Table(name = "tipo_artefacto", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoArtefacto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tiar_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tiarId;

	@NotNull
	@Column(name = "codigo")
	private String codigo;

	@NotNull
	@Column(name = "estado")
	private String estado;

	@NotNull
	@Column(name = "nombre")
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoArtefacto")
	private List<Artefacto> artefactos = new ArrayList<>();

}