package inmobiliaria.entities;

import java.io.Serializable;
import java.util.Date;

public class CuotasInquilinosPersonaInmuebleDueno implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String apellido;
	private String nombreDueno;
	private String apellidoDueno;
	private int mesanio;
	private int valor;
	private String calle;
	private String calleNro;
	private String callePiso;
	private String calleDpto;
	private Long idcuota;
	private Long idinmueble;
	private Long idalquiler;
	private String porcentajeDueno;
	private String idPersonaDueno;
	private Date fecha;
	private Date fechaInicioContrato;
	private Date fechaFinContrato;
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFechaInicioContrato() {
		return fechaInicioContrato;
	}
	public void setFechaInicioContrato(Date fechaInicioContrato) {
		this.fechaInicioContrato = fechaInicioContrato;
	}
	public Date getFechaFinContrato() {
		return fechaFinContrato;
	}
	public void setFechaFinContrato(Date fechaFinContrato) {
		this.fechaFinContrato = fechaFinContrato;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNombreDueno() {
		return nombreDueno;
	}
	public void setNombreDueno(String nombreDueno) {
		this.nombreDueno = nombreDueno;
	}
	public String getApellidoDueno() {
		return apellidoDueno;
	}
	public void setApellidoDueno(String apellidoDueno) {
		this.apellidoDueno = apellidoDueno;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getIdPersonaDueno() {
		return idPersonaDueno;
	}
	public void setIdPersonaDueno(String idPersonaDueno) {
		this.idPersonaDueno = idPersonaDueno;
	}
	public String getPorcentajeDueno() {
		return porcentajeDueno;
	}
	public void setPorcentajeDueno(String porcentajeDueno) {
		this.porcentajeDueno = porcentajeDueno;
	}
	public void setIdalquiler(Long idalquiler) {
		this.idalquiler = idalquiler;
	}
	public Long getIdcuota() {
		return idcuota;
	}
	public void setIdcuota(Long idcuota) {
		this.idcuota = idcuota;
	}
	public Long getIdinmueble() {
		return idinmueble;
	}
	public void setIdinmueble(Long idinmueble) {
		this.idinmueble = idinmueble;
	}
	public int getMesanio() {
		return mesanio;
	}
	public void setMesanio(int mesanio) {
		this.mesanio = mesanio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
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
}
