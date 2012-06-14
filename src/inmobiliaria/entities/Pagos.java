package inmobiliaria.entities;

import java.io.Serializable;
import java.util.Date;


public class Pagos implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long idpago;
	private Alquileres  alquiler ;
	private Date fechaPago;
	private int importe;
	private String nota;

	private int importeAbl;
	private int importeGas;
	private int importeInternet;
	private int importeTelefono;
	private int importeAgua;
	private int importeCable;
	
	public int getImporteAbl() {
		return importeAbl;
	}
	public void setImporteAbl(int importeAbl) {
		this.importeAbl = importeAbl;
	}
	public int getImporteGas() {
		return importeGas;
	}
	public void setImporteGas(int importeGas) {
		this.importeGas = importeGas;
	}
	public int getImporteInternet() {
		return importeInternet;
	}
	public void setImporteInternet(int importeInternet) {
		this.importeInternet = importeInternet;
	}
	public int getImporteTelefono() {
		return importeTelefono;
	}
	public void setImporteTelefono(int importeTelefono) {
		this.importeTelefono = importeTelefono;
	}
	public int getImporteAgua() {
		return importeAgua;
	}
	public void setImporteAgua(int importeAgua) {
		this.importeAgua = importeAgua;
	}
	public int getImporteCable() {
		return importeCable;
	}
	public void setImporteCable(int importeCable) {
		this.importeCable = importeCable;
	}
	public Long getIdpago() {
		return idpago;
	}
	public void setIdpago(Long idpago) {
		this.idpago = idpago;
	}
	
	public Alquileres getAlquiler() {
		return alquiler;
	}
	public void setAlquiler(Alquileres alquiler) {
		this.alquiler = alquiler;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public int getImporte() {
		return importe;
	}
	public void setImporte(int importe) {
		this.importe = importe;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
}
