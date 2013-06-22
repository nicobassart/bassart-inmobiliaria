package inmobiliaria.model;

import inmobiliaria.entities.Alquileres;
import inmobiliaria.entities.CuotasInquilinosPersonaInmuebleDueno;
import inmobiliaria.interfaces.IAlquileresView;
import inmobiliaria.utils.DateUtil;
import inmobiliaria.utils.Utils;

import java.io.Serializable;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AlquileresView implements Serializable,IAlquileresView {

	private static final long serialVersionUID = 1L;
	private Long idAlquiler;
	private Long idPersonaDueno;
	private Long idPersonaInquilino;
	private Long idPersonaGarante;
	private Long idInmueble;
	private Date fechaInicio;
	private Date fechaFin;
	private int importeDeposito;
	private Long idCuota;
	private Date fechaCuota;
	private StringProperty inquilinoNombreApellido;
    private StringProperty calleCompleta;
    private StringProperty importeCobrar;
    private StringProperty periodo;
    private String calle;
    private String calleNro;
    private String callePiso;
    private String calleDpto;
    private String nombreDueno;
    private String apellidoDueno;
    private String porcentajeDueno;
    private Alquileres alquileres;
    private CuotasInquilinosPersonaInmuebleDueno cuota;

	public StringProperty getInquilinoNombreApellido() {
		return inquilinoNombreApellido;
	}
	public void setInquilinoNombreApellido(StringProperty inquilinoNombreApellido) {
		this.inquilinoNombreApellido = inquilinoNombreApellido;
	}
	public StringProperty getCalleCompleta() {
		return calleCompleta;
	}
	public void setCalleCompleta(StringProperty calleCompleta) {
		this.calleCompleta = calleCompleta;
	}
	public StringProperty getImporteCobrar() {
		return importeCobrar;
	}
	public void setImporteCobrar(StringProperty importeCobrar) {
		this.importeCobrar = importeCobrar;
	}
	public StringProperty getPeriodo() {
		return periodo;
	}
	public void setPeriodo(StringProperty periodo) {
		this.periodo = periodo;
	}
	public String calleCompleta(){
		return Utils.formatearCalle(calle, calleNro, callePiso, calleDpto);
	}
	public String nombreDuenoCompleta(){
		return Utils.formatearNomApe(apellidoDueno, nombreDueno);
	}
	
	public AlquileresView(String inquilinoNombreApellido, String calle,String calleNro,String callePiso,String calleDpto, String importeCobrar,String periodo,Long idInmueble, Long idcuota,Long idAlquiler,Date fecha, String nombreDueno, String apellidoDueno, String porcentajeDueno,CuotasInquilinosPersonaInmuebleDueno cuota) {
        this.inquilinoNombreApellido = new SimpleStringProperty(inquilinoNombreApellido);
        this.calleCompleta = new SimpleStringProperty(Utils.formatearCalle(calle, calleNro, callePiso, calleDpto));
        this.importeCobrar = new SimpleStringProperty(importeCobrar);
        this.periodo = new SimpleStringProperty(periodo);
        this.idInmueble = idInmueble;
        this.idCuota= idcuota;
        this.idAlquiler= idAlquiler;
        this.fechaCuota= fecha;
        this.calle=calle;
        this.calleNro=calleNro;
        this.callePiso=callePiso;
        this.calleDpto=calleDpto;
        this.nombreDueno=nombreDueno;
        this.apellidoDueno=apellidoDueno;
        this.porcentajeDueno = porcentajeDueno;
        this.cuota=cuota;
    }
	
    public CuotasInquilinosPersonaInmuebleDueno getCuota() {
		return cuota;
	}
	public void setCuota(CuotasInquilinosPersonaInmuebleDueno cuota) {
		this.cuota = cuota;
	}
	public StringProperty inquilinoNombreApellidoProperty() { return inquilinoNombreApellido; }
    public StringProperty calleCompletaProperty() { return calleCompleta; }
    public StringProperty importeProperty() { return importeCobrar; }
    public StringProperty periodoProperty() { return periodo; }

    public Date getFechaCuota() {
    	return fechaCuota;
    }
    
    public void setFechaCuota(Date fechaCuota) {
    	this.fechaCuota = fechaCuota;
    }


	public Long getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(Long idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public Long getIdCuota() {
		return idCuota;
	}

	public void setIdCuota(Long idCuota) {
		this.idCuota = idCuota;
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
	public String getPorcentajeDueno() {
		return porcentajeDueno;
	}
	public void setPorcentajeDueno(String porcentajeDueno) {
		this.porcentajeDueno = porcentajeDueno;
	}
	public String cuotaFomateada(){
		int total = DateUtil.getDateDiffInMonths( cuota.getFechaInicioContrato(),cuota.getFechaFinContrato());
		int nroCuota = DateUtil.getDateDiffInMonths(cuota.getFechaInicioContrato(), cuota.getFecha());
		
		return String.valueOf(nroCuota) +"/" +  String.valueOf(total);
		
	}
	public Alquileres getAlquileres() {
		return alquileres;
	}
	public void setAlquileres(Alquileres alquileres) {
		this.alquileres = alquileres;
	}
}
