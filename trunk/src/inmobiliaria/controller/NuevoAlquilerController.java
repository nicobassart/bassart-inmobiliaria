package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.Alquileres;
import inmobiliaria.entities.CuotasInquilino;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.Mes;
import inmobiliaria.utils.DateUtil;
import inmobiliaria.utils.TablasUtils;
import inmobiliaria.utils.UtilValidarCampos;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import org.hibernate.Session;

public class NuevoAlquilerController  implements Initializable  {
	private ObservableList<Mes> tableDataInquilino;	
	@FXML private TextField nombreInquilino;
    @FXML private TextField calle;
    @FXML private TextField fechaInicio;
    @FXML private TextField fechaFin;
    @FXML private TextField importeDeposito;
    @FXML private TextField porcentajeDueno;
    @FXML private TextField importeInquilino;

    @FXML private CheckBox abl;
    @FXML private CheckBox gas;
    @FXML private CheckBox internet;
    @FXML private CheckBox telefono;
    @FXML private CheckBox agua;
    @FXML private CheckBox cable;
	@FXML private Label error;

    @Override public void initialize(URL location, ResourceBundle resources) {
    	if(App.getInstance().getPersona()!=null)
    		nombreInquilino.setText(App.getInstance().getPersona().nombreCompleto());

    	if(App.getInstance().getInmueble()!=null)
    		calle.setText(App.getInstance().getInmueble().calleCompleta());
    }
    
    @FXML protected void processBuscarInmueble(ActionEvent event) throws Exception{
    	App.getInstance().replaceSceneContent(App.buscarInmueble);
    }

    @FXML protected void processBuscarPersona(ActionEvent event) throws Exception{
    	App.getInstance().replaceSceneContent(App.buscarPersona);this.getClass().getClassLoader();
    }

    @FXML protected void processGuardar(ActionEvent event) throws Exception{
    	if(!this.validarFormGuardar(nombreInquilino,calle,importeDeposito,porcentajeDueno,tableDataInquilino)) return;
    	
    	
		BorderPane par = (BorderPane) App.getInstance().getScene().getRoot();

		GridPane grid = (GridPane) par.getCenter();

		Session session = SessionManager.getSession();

		session.beginTransaction();
		
		Alquileres alq = new Alquileres();
		alq.setIdPersonaDueno(App.getInstance().getPersona().getIdPersona());
		alq.setIdPersonaGarante(App.getInstance().getPersona().getIdPersona());
		alq.setIdPersonaInquilino(App.getInstance().getPersona().getIdPersona());
		alq.setIdInmueble((App.getInstance().getInmueble()).getIdInmueble());
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy"); 
		java.util.Date date=sdf.parse(fechaFin.getText()); 
		alq.setFechaFin(date);
		java.util.Date date1=sdf.parse(fechaInicio.getText()); 
		alq.setFechaInicio(date1);
		alq.setImporteDeposito(Integer.parseInt(importeDeposito.getText()));
		alq.setEstado("A");

		// Parte de Servicios
		alq.setAbl(abl.isSelected());
		alq.setGas(gas.isSelected());
		alq.setCable(cable.isSelected());
		alq.setTelefono(telefono.isSelected());
		alq.setInternet(internet.isSelected());
		alq.setAgua(agua.isSelected());
		alq.setPorcentajeDueno(Integer.parseInt(porcentajeDueno.getText()));
    	for (ListIterator<Mes> iterator = tableDataInquilino.listIterator(); iterator.hasNext();) {
    		Mes type = (Mes) iterator.next();
    		CuotasInquilino cuota = new CuotasInquilino();
    		cuota.setCuotaNro(type.getCuotaNro());
    		cuota.setMesesContrato(type.getMesesContrato());
    		cuota.setAlquileres(alq);
    		cuota.setMesanio(Integer.parseInt(type.getMes().getValue()));
    		cuota.setValor(Float.parseFloat(type.getValor().getValue()));
    		cuota.setPorcetaje(Integer.parseInt(type.getPorcentaje().getValue()));
    		cuota.setFecha(type.getFecha());
    		cuota.setAlquileres(alq);
    		session.save(cuota);	
    		
		}
		
		session.save(alq);

		// Compromete los cambios
		 session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();
		
		App.getInstance().replaceSceneContent(App.messages_ok);

    }


	/*
     * Ac� a partir de la fecha de inicio y fecha de fin vamos a cargar todas las cuotas dentro de la tabla
     * y permitirle hacer la modificacion que corresponda en cada mes.
     * 
     * luego esta tabla se tiene que bajar a la base de datos
     */
    @FXML protected void proyectarInquilino(ActionEvent event) {
    	if(!this.validarForm(fechaInicio,fechaFin,importeInquilino)) return;
    	int mesesContrato;
		BorderPane par = (BorderPane) App.getInstance().getScene().getRoot();

		GridPane grid = (GridPane) par.getCenter();
		
		mesesContrato = DateUtil.calcularMeses(fechaInicio.getText(),fechaFin.getText());

		TableView<Mes> proyectarInquilino = (TableView<Mes>) grid.getChildren().get(16);
		tableDataInquilino=proyectarInquilino.getItems();
		tableDataInquilino.clear();
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		Date inicio=null ;
		try {
		 inicio = sdf.parse(fechaInicio.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendarInicio =  Calendar.getInstance();
		
		calendarInicio.setTime(inicio);
		for (int i = 1; i <= mesesContrato; i++) {
			
			tableDataInquilino.add(new Mes( ((calendarInicio.get(Calendar.MONTH)+1)*10000)+ calendarInicio.get(Calendar.YEAR),100,importeInquilino.getText(),calendarInicio.getTime(),i,mesesContrato));
			calendarInicio.add(Calendar.MONTH, 1);
		}
		TablasUtils.armarColumnas(proyectarInquilino);
		proyectarInquilino.setEditable(true);
		proyectarInquilino.setItems(tableDataInquilino);
    }
    
    
	private boolean validarForm(TextField fechaInicio, TextField fechaFin,
			TextField importeInquilino) {
		String errorAcum="";
		error.setText("");

		if(!UtilValidarCampos.notEmptyString(fechaInicio.getText())) errorAcum += "Debe ingresar una fecha de inicio.";
		
		if(!UtilValidarCampos.notEmptyString(fechaFin.getText())) errorAcum += "Debe ingresar una fecha de fin.";
			
		if(!UtilValidarCampos.notEmptyString(importeInquilino.getText())) errorAcum += "Debe ingresar un importe Inquilino.";
		
		if(!UtilValidarCampos.isNumeric(importeInquilino.getText())) errorAcum += "Debe ingresar un importe númerico.";
		
		if(!UtilValidarCampos.validarFormatoFecha(fechaFin.getText())) errorAcum += "La fecha fin debe tener el siguiente formato DD-MM-AAAA.";
		
		if(!UtilValidarCampos.validarFormatoFecha(fechaInicio.getText())) errorAcum += "La fecha inicio debe tener el siguiente formato DD-MM-AAAA.";
		
		if(!UtilValidarCampos.validarDiferenciaFechas(fechaInicio.getText(),fechaFin.getText())) errorAcum += "La fecha inicio de contrato debe ser mayor a la fecha de fin de contrato.";
		
			
		if(!errorAcum.isEmpty()){ 
			error.setText(errorAcum); 
			return false;
		}
	
		return true;
	}
	
    private boolean validarFormGuardar(TextField nombreInquilino2,
			TextField calle2, TextField importeDeposito2,
			TextField porcentajeDueno2, ObservableList<Mes> tableDataInquilino2) {

		String errorAcum="";
		error.setText("");

		if(!UtilValidarCampos.notEmptyString(nombreInquilino2.getText())) errorAcum += "Debe buscar un inquilino.";
		
		if(!UtilValidarCampos.notEmptyString(calle2.getText())) errorAcum += "Debe buscar una vivienda.";
		
		if(!UtilValidarCampos.notEmptyString(porcentajeDueno2.getText())) errorAcum += "Completar campo Cobrar Dueño con un porcentaje de comisión.";
		
		if(!UtilValidarCampos.notEmptyString(importeDeposito2.getText())) errorAcum += "Completar campo importe deposito , si no tiene deposito ingresar 0.";
		
		if(!UtilValidarCampos.isNumeric(importeDeposito2.getText())) errorAcum += "El importe deposito debe ser numerico";
		
		if(!UtilValidarCampos.isNumeric(porcentajeDueno2.getText())) errorAcum += "El porcentaje de comisión debe ser entre el 0 y el 80, sin el signo %";
		
		if(!UtilValidarCampos.isNumeric(importeInquilino.getText())) errorAcum += "Debe ingresar un importe a cobrar al inquilino";
		
		if(tableDataInquilino2 ==null || tableDataInquilino2.isEmpty()) errorAcum += "Debe presionar el boton proyectar para generar las cuotas.";

		if(!errorAcum.isEmpty()){ 
			error.setText(errorAcum); 
			return false;
		}
	
		return true;
	}
}
