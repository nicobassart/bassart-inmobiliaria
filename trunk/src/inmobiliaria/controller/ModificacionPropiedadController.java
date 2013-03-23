package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.manager.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import org.hibernate.Session;

public class ModificacionPropiedadController implements Initializable {
	@FXML private TextField calle;
	@FXML private TextField calleNro;
	@FXML private TextField callePiso;
	@FXML private TextField calleDpto;
	@FXML private TextField provincia;
	@FXML private TextField localidad;
	@FXML private TextField nombreDueno;

	@FXML private ChoiceBox<String> choice = new ChoiceBox<String>();


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
    @FXML protected void processBuscarPropiedad(ActionEvent event) throws Exception{
    	App.getInstance().replaceSceneContent(App.buscarInmueble);
    }
	@FXML
	protected void processUpdate(ActionEvent event) throws Exception {

		Session session = SessionManager.getSession();

		session.beginTransaction();

		BorderPane par = (BorderPane) App.getInstance().getScene().getRoot();

		GridPane grid = (GridPane) par.getCenter();

		ChoiceBox<String> combo = (ChoiceBox<String>) grid.getChildren().get(12);
		
		App.getInstance().getInmueble().getInmuebleEntiti().setCalle(calle.getText());
		App.getInstance().getInmueble().getInmuebleEntiti().setCalleDpto(calleDpto.getText());
		App.getInstance().getInmueble().getInmuebleEntiti().setCallePiso(callePiso.getText());
		App.getInstance().getInmueble().getInmuebleEntiti().setLocalidad(1);
		App.getInstance().getInmueble().getInmuebleEntiti().setProvincia(1);
		session.update(App.getInstance().getInmueble().getInmuebleEntiti());

		// Compromete los cambios
		session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();

		App.getInstance().replaceSceneContent(App.messages_ok);

	}
}
