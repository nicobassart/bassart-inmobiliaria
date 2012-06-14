package inmobiliaria.model;


import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mes {
	private StringProperty cuotaNroMeses;
	private StringProperty mesAnio;
	private StringProperty porcentaje;
	private StringProperty valor;
	private StringProperty estado;
	private int cuotaNro;
	private int mesesContrato;
	private Date fecha;
	
	public Mes(int mesanio,int porcentaje, String valor,Date fecha,int cuotaNro,int mesesContrato){
		this.mesAnio= new SimpleStringProperty(String.valueOf(mesanio));
		this.porcentaje= new SimpleStringProperty(String.valueOf(porcentaje));
		this.valor= new SimpleStringProperty(valor);
		this.fecha= fecha;
		this.cuotaNroMeses= new SimpleStringProperty(String.valueOf(cuotaNro)+" / "+String.valueOf(mesesContrato));
		
	}

	public StringProperty cuotaNroProperty() { return cuotaNroMeses; }
	public StringProperty mesAnioProperty() { return mesAnio; }
	public StringProperty porcentajeProperty() { return porcentaje; }
	public StringProperty valorProperty() { return valor; }
	public StringProperty estadoProperty() { return estado; }

	
	public int getCuotaNro() {
		return cuotaNro;
	}

	public void setCuotaNro(int cuotaNro) {
		this.cuotaNro = cuotaNro;
	}
	public void setEstado(String estado){
		this.estado = new SimpleStringProperty(estado);
	}

	public StringProperty getCuotaNroMeses() {
		return cuotaNroMeses;
	}

	public void setCuotaNroMeses(StringProperty cuotaNroMeses) {
		this.cuotaNroMeses = cuotaNroMeses;
	}

	public StringProperty getMesAnio() {
		return mesAnio;
	}

	public void setMesAnio(StringProperty mesAnio) {
		this.mesAnio = mesAnio;
	}

	public int getMesesContrato() {
		return mesesContrato;
	}

	public void setMesesContrato(int mesesContrato) {
		this.mesesContrato = mesesContrato;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public StringProperty getMes() {
		return mesAnio;
	}
	public void setMes(StringProperty mes) {
		this.mesAnio = mes;
	}
	public StringProperty getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(StringProperty porcentaje) {
		this.porcentaje = porcentaje;
	}
	public StringProperty getValor() {
		return valor;
	}
	public void setValor(StringProperty valor) {
		this.valor = valor;
	}

	public void setMes(String newValue) {
		this.mesAnio.setValue(newValue);
		
	}

	public void setPorcentaje(String newValue) {
		this.porcentaje.setValue(newValue);
		float valor = Float.parseFloat(this.valor.getValue());
		float valorFinal = (Float.parseFloat(newValue)/100)* valor;
		this.valor.setValue(String.valueOf(valorFinal));
		//puedo recalcular el importe
		
	}
}
