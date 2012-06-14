package inmobiliaria.model;

import inmobiliaria.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inmueble {
	private Long idInmueble;
    private StringProperty calle;
    private StringProperty calleNro;
    private StringProperty callePiso;
    private StringProperty calleDpto;
    private inmobiliaria.entities.Inmueble inmuebleEntiti;

    public Inmueble(String calle, String calleNro, String callePiso,String calleDpto,Long idInmueble,inmobiliaria.entities.Inmueble inmuebleEntiti) {
        this.calle = new SimpleStringProperty(calle);
        this.calleNro = new SimpleStringProperty(calleNro);
        this.callePiso = new SimpleStringProperty(callePiso);
        this.calleDpto = new SimpleStringProperty(calleDpto);
        this.idInmueble = idInmueble;
        this.inmuebleEntiti=inmuebleEntiti;
        
    }
    
    public StringProperty calleProperty() { return calle; }
    public StringProperty calleNroProperty() { return calleNro; }
    public StringProperty callePisoProperty() { return callePiso; }
    public StringProperty calleDptoProperty() { return calleDpto; }

	public Long getIdInmueble() {
		return idInmueble;
	}
	
	public inmobiliaria.entities.Inmueble getInmuebleEntiti() {
		return inmuebleEntiti;
	}

	public void setInmuebleEntiti(inmobiliaria.entities.Inmueble inmuebleEntiti) {
		this.inmuebleEntiti = inmuebleEntiti;
	}

	public String calleCompleta(){
		return Utils.formatearCalle(calle.getValue(), calleNro.getValue() , callePiso.getValue(), calleDpto.getValue());
	}
}
