package inmobiliaria.entities;

import java.io.Serializable;

public class Inmueble implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long idInmueble;

	private Persona persona;
	private String calle;
    private String calleNro;
    private String callePiso;
    private String calleDpto;
    private int provincia;

	private int localidad;

    public Inmueble() {
    }
    public Inmueble(String calle, String calleNro, String callePiso, String calleDpto,int provincia, int localidad,Persona persona)
    {
        setCalle(calle);
        setCalleNro(calleNro);
        setCallePiso(callePiso);
        setCalleDpto(calleDpto);
        setProvincia(provincia);
        setLocalidad(localidad);
        setPersona(persona);
    }

    public Long getIdInmueble() {
		return idInmueble;
	}
	public void setIdInmueble(Long idInmueble) {
		this.idInmueble = idInmueble;
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
    public int getProvincia() {
		return provincia;
	}
	public void setProvincia(int provincia) {
		this.provincia = provincia;
	}
	public int getLocalidad() {
		return localidad;
	}
	public void setLocalidad(int localidad) {
		this.localidad = localidad;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
}
