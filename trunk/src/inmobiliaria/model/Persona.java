package inmobiliaria.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty email;
    private StringProperty tel_celular;
    private StringProperty tel_fijo;
    private Long idPersona;
    private inmobiliaria.entities.Persona personaEntiti;

	public Persona(String fName, String lName, String email,String tel_celular, String tel_fijo,Long idPersona, inmobiliaria.entities.Persona cli) {
        this.nombre = new SimpleStringProperty(fName);
        this.apellido = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
        this.tel_celular = new SimpleStringProperty(tel_celular);
        this.tel_fijo = new SimpleStringProperty(tel_fijo);
        this.idPersona = idPersona;
        this.personaEntiti= cli;
    }
	public Long getIdPersona() {
		return idPersona;
	}
	
	public inmobiliaria.entities.Persona getPersonaEntiti() {
		return personaEntiti;
	}
	public void setPersonaEntiti(inmobiliaria.entities.Persona personaEntiti) {
		this.personaEntiti = personaEntiti;
	}
    public StringProperty nombreProperty() { return nombre; }
    public StringProperty apellidoProperty() { return apellido; }
    public StringProperty emailProperty() { return email; }
    public StringProperty tel_celularProperty() { return tel_celular; }
    public StringProperty tel_fijoProperty() { return tel_fijo; }
    
    public String nombreCompleto(){
    	return apellido.getValue() +", "+ nombre.getValue();
    }
}
