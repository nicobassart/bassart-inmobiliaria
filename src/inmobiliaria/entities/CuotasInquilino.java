package inmobiliaria.entities;

import java.io.Serializable;
import java.util.Date;

public class CuotasInquilino implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private int cuotaNro;
	private int mesesContrato;
	private int mesanio;
	private int porcetaje;
	private float valor;
	private Date fecha;
	private Pagos pago;
	private Alquileres alquileres;

	
	public int getMesesContrato() {
		return mesesContrato;
	}
	public void setMesesContrato(int mesesContrato) {
		this.mesesContrato = mesesContrato;
	}
	public int getCuotaNro() {
		return cuotaNro;
	}
	public void setCuotaNro(int cuotaNro) {
		this.cuotaNro = cuotaNro;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pagos getPago() {
		return pago;
	}
	public void setPago(Pagos pago) {
		this.pago = pago;
	}
	
	public Alquileres getAlquileres() {
		return alquileres;
	}
	public void setAlquileres(Alquileres alquileres) {
		this.alquileres = alquileres;
	}
	public int getMesanio() {
		return mesanio;
	}
	public void setMesanio(int mesanio) {
		this.mesanio = mesanio;
	}
	public int getPorcetaje() {
		return porcetaje;
	}
	public void setPorcetaje(int porcetaje) {
		this.porcetaje = porcetaje;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
//	public int getIdalquiler() {
//		return idalquiler;
//	}
//	public void setIdalquiler(int idalquiler) {
//		this.idalquiler = idalquiler;
//	}
}
