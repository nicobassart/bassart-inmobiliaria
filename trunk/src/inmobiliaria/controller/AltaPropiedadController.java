package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.Inmueble;
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

public class AltaPropiedadController implements Initializable {
	@FXML private TextField calle;
	@FXML private TextField calleNro;
	@FXML private TextField callePiso;
	@FXML private TextField calleDpto;
	@FXML private TextField provincia;
	@FXML private TextField localidad;
	@FXML private TextField nombreDueno;

	@FXML private ChoiceBox<String> choice;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	if(App.getInstance().getPersona()!=null)
    		nombreDueno.setText(App.getInstance().getPersona().nombreCompleto());
    	
		Session session = SessionManager.getSession();

		List<Provincias> cuota = session.createQuery("FROM inmobiliaria.entities.Provincias").list();

		Iterator<Provincias> itVendedores = cuota.iterator();

		while (itVendedores.hasNext()) {
			Provincias prov = itVendedores.next();
			choice.getItems().add(prov.getIdprovincias(),  prov.getNombre());
		}
	}
    @FXML protected void processBuscarPersona(ActionEvent event) throws Exception{
    	App.getInstance().replaceSceneContent(App.buscarPersona);
    }
	@FXML
	protected void processUpdate(ActionEvent event) throws Exception {
		Session session = SessionManager.getSession();

		session.beginTransaction();

		Inmueble c1 = new Inmueble(calle.getText(), calleNro.getText(),
				callePiso.getText(), calleDpto.getText(), 1, 1,App.getInstance().getPersona().getPersonaEntiti());
		session.save(c1);

		// Compromete los cambios
		session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();

		App.getInstance().replaceSceneContent(App.messages_ok);
	}
}
