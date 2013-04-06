package inmobiliaria.entities;

import java.io.Serializable;

public class Provincias implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idprovincias;
	private String nombre;

	
	public int getIdprovincias() {
		return idprovincias;
	}
	public void setIdprovincias(int idprovincias) {
		this.idprovincias = idprovincias;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
