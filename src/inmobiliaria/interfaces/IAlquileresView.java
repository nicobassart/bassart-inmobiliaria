package inmobiliaria.interfaces;

import java.util.Date;

import javafx.beans.property.StringProperty;

public interface IAlquileresView {
	public StringProperty importeProperty();
	public String getPorcentajeDueno();
	public Long getIdCuota();
	public String cuotaFomateada();
	public String nombreDuenoCompleta();
	
	public StringProperty inquilinoNombreApellidoProperty();
	public Date getFechaCuota();
	public String calleCompleta();
}
