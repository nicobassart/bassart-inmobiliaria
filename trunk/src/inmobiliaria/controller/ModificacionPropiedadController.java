package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.Provincias;
import inmobiliaria.manager.SessionManager;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import org.hibernate.Session;

public class ModificacionPropiedadController implements Initializable {
	@FXML private TextField calle;
	@FXML private TextField calleNro;
	@FXML private TextField callePiso;
	@FXML private TextField calleDpto;
	@FXML private TextField localidad;
	@FXML private TextField nombreDueno;
	@FXML private ChoiceBox<String> comboProvincia;
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	if(App.getInstance().getInmueble()!=null){
    		Session session = SessionManager.getSession();
    		List<Provincias> cuota = session.createQuery("FROM inmobiliaria.entities.Provincias").list();
    		Iterator<Provincias> itVendedores = cuota.iterator();
    		while (itVendedores.hasNext()) {
    			Provincias prov = itVendedores.next();
    			comboProvincia.getItems().add(prov.getIdprovincias(),  prov.getNombre());
    		}
    		comboProvincia.getSelectionModel().select(App.getInstance().getInmueble().getInmuebleEntiti().getProvincia());
    		nombreDueno.setText(App.getInstance().getInmueble().getInmuebleEntiti().getPersona().nombreCompleto());
    		calle.setText(App.getInstance().getInmueble().getInmuebleEntiti().getCalle());
    		calleNro.setText(App.getInstance().getInmueble().getInmuebleEntiti().getCalleNro());
    		callePiso.setText(App.getInstance().getInmueble().getInmuebleEntiti().getCallePiso());
    		calleDpto.setText(App.getInstance().getInmueble().getInmuebleEntiti().getCalleDpto());
    		localidad.setText(App.getInstance().getInmueble().getInmuebleEntiti().getLocalidad());
    		
    		
    		
    	}
	}

	@FXML
	protected void processUpdate(ActionEvent event) throws Exception {

		Session session = SessionManager.getSession();

		session.beginTransaction();

		App.getInstance().getInmueble().getInmuebleEntiti().setCalle(calle.getText());
		App.getInstance().getInmueble().getInmuebleEntiti().setCalleNro(calleNro.getText());
		App.getInstance().getInmueble().getInmuebleEntiti().setCalleDpto(calleDpto.getText());
		App.getInstance().getInmueble().getInmuebleEntiti().setCallePiso(callePiso.getText());
		App.getInstance().getInmueble().getInmuebleEntiti().setLocalidad(localidad.getText());
		App.getInstance().getInmueble().getInmuebleEntiti().setProvincia(comboProvincia.selectionModelProperty().getValue().getSelectedIndex());
		session.update(App.getInstance().getInmueble().getInmuebleEntiti());

		// Compromete los cambios
		session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();

		App.getInstance().replaceSceneContent(App.messages_ok);

	}
	@FXML
	protected void processVolver(ActionEvent event) {
		App.getInstance().pageAnterior();
	}
}
