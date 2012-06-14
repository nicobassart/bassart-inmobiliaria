package inmobiliaria.entities;

import java.io.Serializable;

public abstract class Persona implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long idpersona;
	private String nombre;
    private String apellido;
    private String tipoDoc;
    private int nroDoc;
    private String tel_fijo;
    private String tel_celular;
    private String tel_opcional;
    private String email;


	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public int getNroDoc() {
		return nroDoc;
	}
	public void setNroDoc(int nroDoc) {
		this.nroDoc = nroDoc;
	}
	public String getTel_fijo() {
		return tel_fijo;
	}
	public void setTel_fijo(String tel_fijo) {
		this.tel_fijo = tel_fijo;
	}
	public String getTel_celular() {
		return tel_celular;
	}
	public void setTel_celular(String tel_celular) {
		this.tel_celular = tel_celular;
	}
	public String getTel_opcional() {
		return tel_opcional;
	}
	public void setTel_opcional(String tel_opcional) {
		this.tel_opcional = tel_opcional;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Persona() {
    }
    public Long getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(Long idpersona) {
		this.idpersona = idpersona;
	}
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String nombreCompleto(){
    	return apellido +", "+ nombre;
    }

}
