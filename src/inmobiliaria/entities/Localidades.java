package inmobiliaria.entities;

import java.io.Serializable;

public class Localidades implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private int idprovincias;
	private String nombre;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
