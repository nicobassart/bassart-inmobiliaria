package inmobiliaria.entities;

import java.io.Serializable;

public class AlquileresInmueblePersona implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String apellido;
	private String calle;
	private String calleNro;
	private String callePiso;
	private String calleDpto;
	private Long idalquiler;
	private String estado;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setIdalquiler(Long idalquiler) {
		this.idalquiler = idalquiler;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getCalleNro() {
		return calleNro;
	}
	public void setCalleNro(String calleNro) {
		this.calleNro = calleNro;
	}
	public String getCallePiso() {
		return callePiso;
	}
	public void setCallePiso(String callePiso) {
		this.callePiso = callePiso;
	}
	public String getCalleDpto() {
		return calleDpto;
	}
	public void setCalleDpto(String calleDpto) {
		this.calleDpto = calleDpto;
	}
	public Long getIdalquiler() {
		return idalquiler;
	}
	public String getEstadoDescripcion(){
		if("E".equals(estado))
			return "Eliminado";
		if("A".equals(estado))
			return "Activo";
		return "Estado no definido";
	}
}
