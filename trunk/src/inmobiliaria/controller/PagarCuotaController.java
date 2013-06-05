package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.Alquileres;
import inmobiliaria.entities.CuotasInquilino;
import inmobiliaria.entities.Pagos;
import inmobiliaria.interfaces.IAlquileresView;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.AlquileresView;
import inmobiliaria.reporte.ManagerReporte;
import inmobiliaria.search.FinderInmueble;
import inmobiliaria.utils.UtilValidarCampos;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.hibernate.Session;

public class PagarCuotaController implements Initializable {

	@FXML private TextField mesanio;
	@FXML private TextField porcentaje;
	@FXML private TextField valor;
	@FXML private TextField nombreApellidoInquilino;

	
    @FXML private TextField abl;
    @FXML private TextField gas;
    @FXML private TextField internet;
    @FXML private TextField telefono;
    @FXML private TextField agua;
    @FXML private TextField cable;
    @FXML private TextArea nota;
    
	@FXML private Label error;

		  
	@FXML
	protected void imprimir(ActionEvent event) throws Exception {
		if(!this.validarForm(abl,gas,internet,telefono,agua,cable)) return;
		//Acá vamos a generar el PDF de pago, y tambien vamos a guardar el registro como pagado

		Session session = SessionManager.getSession();

		session.beginTransaction();

		CuotasInquilino cuota = (CuotasInquilino) session.get(CuotasInquilino.class, new Long(App.getInstance().getAlquilerViewHome().getIdCuota()));
		
		Alquileres alq = (Alquileres)session.load(Alquileres.class, new Long(App.getInstance().getAlquilerViewHome().getIdAlquiler()));
		Pagos unPago = new Pagos();		
		unPago.setAlquiler(alq);
		unPago.setImporte(Integer.parseInt(App.getInstance().getAlquilerViewHome().importeProperty().getValue()));
		unPago.setNota(nota.getText());
		unPago.setFechaPago(Calendar.getInstance().getTime());
		unPago.setImporteAbl(this.evaluarText(this.abl.getText()));
		unPago.setImporteGas(this.evaluarText(this.gas.getText()));
		unPago.setImporteInternet(this.evaluarText(this.internet.getText()));
		unPago.setImporteTelefono(this.evaluarText(this.telefono.getText()));
		unPago.setImporteAgua(this.evaluarText(this.agua.getText()));
		unPago.setImporteCable(this.evaluarText(this.cable.getText()));

		session.save(unPago);
		
		cuota.setPago(unPago);

		session.update(cuota);
		
		// Compromete los cambios
		session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();
		
		ManagerReporte.generarComprobante(this.calcularServicio(),(IAlquileresView)App.getInstance().getAlquilerViewHome());
		
		App.getInstance().replaceSceneContent(App.messages_ok);
	}

	private int calcularServicio() {
		int total = 0;
		total  = this.evaluarText(this.abl.getText());
		total += this.evaluarText(this.gas.getText());
		total += this.evaluarText(this.internet.getText());
		total += this.evaluarText(this.telefono.getText());
		total += this.evaluarText(this.agua.getText());
		total += this.evaluarText(this.cable.getText());
		return total;
	}

	private int evaluarText(String text) {
		if(text!=null){
			
			try {
				return Integer.parseInt(text);
		
			} catch (NumberFormatException nfe){
				return 0;
			}	
		}
		return 0;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		mesanio.setText(App.getInstance().getAlquilerViewHome().periodoProperty().getValue());
		porcentaje.setText(App.getInstance().getAlquilerViewHome().inquilinoNombreApellidoProperty().getValue());
		valor.setText(App.getInstance().getAlquilerViewHome().importeProperty().getValue());
		nombreApellidoInquilino.setText(App.getInstance().getAlquilerViewHome().inquilinoNombreApellidoProperty().getValue());
		FinderInmueble finder = new FinderInmueble();
		AlquileresView alquiler =  finder.finderInmueble(App.getInstance().getAlquilerViewHome());

	    this.abl.setDisable(alquiler.getAlquileres().isAbl());
	    this.gas.setDisable(alquiler.getAlquileres().isGas());
	    this.internet.setDisable(alquiler.getAlquileres().isInternet());
	    this.telefono.setDisable(alquiler.getAlquileres().isTelefono());
	    this.agua.setDisable(alquiler.getAlquileres().isAgua());
	    this.cable.setDisable(alquiler.getAlquileres().isCable());
	}
	private boolean validarForm(TextField abl2, TextField gas2, TextField internet2, TextField telefono2, TextField agua2, TextField cable2){
		String errorAcum="";
		error.setText("");
		
		if(abl2.getText()!=null && !UtilValidarCampos.isNumeric(abl2.getText())) errorAcum += "Debe ingresar un importe númerico para ABL.";
		if(gas2.getText()!=null && !UtilValidarCampos.isNumeric(gas2.getText())) errorAcum += "Debe ingresar un importe númerico para GAS. ";
		if(internet2.getText()!=null && !UtilValidarCampos.isNumeric(internet2.getText())) errorAcum += "Debe ingresar un importe númerico para Internet.";
		if(telefono2.getText()!=null && !UtilValidarCampos.isNumeric(telefono2.getText())) errorAcum += "Debe ingresar un importe númerico para Telefono.";
		if(agua2.getText()!=null && !UtilValidarCampos.isNumeric(agua2.getText())) errorAcum += "Debe ingresar un importe númerico para Agua.";
		if(cable2.getText()!=null && !UtilValidarCampos.isNumeric(cable2.getText())) errorAcum += "Debe ingresar un importe númerico para Cable.";
		
		if(!errorAcum.isEmpty()){ 
			error.setText(errorAcum); 
			return false;
		}
	
		return true;	
	}
}
