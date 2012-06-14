package inmobiliaria.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty email;
    private Long idPersona;
    private inmobiliaria.entities.Persona personaEntiti;

	public Persona(String fName, String lName, String email,Long idPersona, inmobiliaria.entities.Persona cli) {
        this.nombre = new SimpleStringProperty(fName);
        this.apellido = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
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
    
    public String nombreCompleto(){
    	return apellido.getValue() +", "+ nombre.getValue();
    }
}
