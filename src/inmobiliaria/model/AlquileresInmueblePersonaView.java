package inmobiliaria.model;

import inmobiliaria.entities.AlquileresInmueblePersona;
import inmobiliaria.utils.Utils;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class AlquileresInmueblePersonaView implements Serializable {

	private static final long serialVersionUID = 1L;

	private StringProperty nombreApellido;
	private StringProperty calleCompleta;
	private StringProperty estado;
	private AlquileresInmueblePersona alquileresInmueblePersona;
	
	public AlquileresInmueblePersonaView(AlquileresInmueblePersona alquiler) {
		this.nombreApellido = new SimpleStringProperty(Utils.formatearNomApe(
				alquiler.getApellido(), alquiler.getNombre()));
		this.calleCompleta = new SimpleStringProperty(Utils.formatearCalle(
				alquiler.getCalle(), alquiler.getCalleNro(),
				alquiler.getCallePiso(), alquiler.getCalleDpto()));
		this.estado= new SimpleStringProperty(alquiler.getEstadoDescripcion());
		alquileresInmueblePersona = alquiler;
	}

	public StringProperty nombreApellidoProperty() {
		return nombreApellido;
	}

	public StringProperty calleCompletaProperty() {
		return calleCompleta;
	}
	public StringProperty estadoProperty() {
		return estado;
	}
	public AlquileresInmueblePersona getAlquileresInmueblePersona(){
		return alquileresInmueblePersona;
	}
	public Button buttonProperty(){
		return new Button();
	}
}
