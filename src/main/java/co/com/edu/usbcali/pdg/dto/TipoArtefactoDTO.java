package co.com.edu.usbcali.pdg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
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
    @NotNull
    private Long tiarId;
}
