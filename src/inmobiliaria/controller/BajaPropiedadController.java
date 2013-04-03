package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.manager.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import org.hibernate.Session;

public class BajaPropiedadController implements Initializable {
	@FXML private TextField calle;
	@FXML private TextField calleNro;
	@FXML private TextField callePiso;
	@FXML private TextField calleDpto;
	@FXML private TextField provincia;
	@FXML private TextField localidad;
	@FXML private TextField nombreDueno;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	if(App.getInstance().getInmueble()!=null){
    		nombreDueno.setText(App.getInstance().getInmueble().getInmuebleEntiti().getPersona().nombreCompleto());
    		calle.setText(App.getInstance().getInmueble().getInmuebleEntiti().getCalle());
    		calleNro.setText(App.getInstance().getInmueble().getInmuebleEntiti().getCalleNro());
    		callePiso.setText(App.getInstance().getInmueble().getInmuebleEntiti().getCallePiso());
    		calleDpto.setText(App.getInstance().getInmueble().getInmuebleEntiti().getCalleDpto());
    	}
	}
    
	@FXML
	protected void processEliminar(ActionEvent event) throws Exception {

		Session session = SessionManager.getSession();

		session.beginTransaction();

		session.delete(App.getInstance().getInmueble().getInmuebleEntiti());

		// Compromete los cambios
		session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();

		App.getInstance().replaceSceneContent(App.messages_ok);

	}
}
