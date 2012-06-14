package inmobiliaria.entities;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.mapping.Set;

public class Alquileres implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idAlquiler;
	private Long idPersonaDueno;
	private Long idPersonaInquilino;
	private Long idPersonaGarante;
	private Long idInmueble;
	private Date fechaInicio;
	private Date fechaFin;
	private int importeDeposito;
	private int porcentajeDueno;
	private Set cuotasInquilino;
	private String estado;
	
	private boolean abl;
	private boolean gas;
	private boolean internet;
	private boolean agua;
	private boolean cable;
	private boolean telefono;
	
	

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean isTelefono() {
		return telefono;
	}
	public void setTelefono(boolean telefono) {
		this.telefono = telefono;
	}
	public boolean isAbl() {
		return abl;
	}
	public void setAbl(boolean abl) {
		this.abl = abl;
	}
	public boolean isGas() {
		return gas;
	}
	public void setGas(boolean gas) {
		this.gas = gas;
	}
	public boolean isInternet() {
		return internet;
	}
	public void setInternet(boolean internet) {
		this.internet = internet;
	}
	public boolean isAgua() {
		return agua;
	}
	public void setAgua(boolean agua) {
		this.agua = agua;
	}
	public boolean isCable() {
		return cable;
	}
	public void setCable(boolean cable) {
		this.cable = cable;
	}
	public Set getCuotasInquilino() {
		return cuotasInquilino;
	}
	public void setCuotasInquilino(Set cuotasInquilino) {
		this.cuotasInquilino = cuotasInquilino;
	}
	public int getPorcentajeDueno() {
		return porcentajeDueno;
	}
	public void setPorcentajeDueno(int porcentajeDueno) {
		this.porcentajeDueno = porcentajeDueno;
	}
	public int getImporteDeposito() {
		return importeDeposito;
	}
	public void setImporteDeposito(int importeDeposito) {
		this.importeDeposito = importeDeposito;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Long getIdAlquiler() {
		return idAlquiler;
	}
	public void setIdAlquiler(Long idAlquiler) {
		this.idAlquiler = idAlquiler;
	}
	public Long getIdPersonaDueno() {
		return idPersonaDueno;
	}
	public void setIdPersonaDueno(Long idPersonaDueno) {
		this.idPersonaDueno = idPersonaDueno;
	}
	public Long getIdPersonaInquilino() {
		return idPersonaInquilino;
	}
	public void setIdPersonaInquilino(Long idPersonaInquilino) {
		this.idPersonaInquilino = idPersonaInquilino;
	}
	public Long getIdPersonaGarante() {
		return idPersonaGarante;
	}
	public void setIdPersonaGarante(Long idPersonaGarante) {
		this.idPersonaGarante = idPersonaGarante;
	}
	public Long getIdInmueble() {
		return idInmueble;
	}
	public void setIdInmueble(Long idInmueble) {
		this.idInmueble = idInmueble;
	}
}
