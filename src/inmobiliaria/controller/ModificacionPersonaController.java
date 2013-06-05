package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.Cliente;
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

public class ModificacionPersonaController implements Initializable {
	@FXML private TextField apellido;
	@FXML private TextField nombre;
	@FXML private TextField nroDoc;
	@FXML private ChoiceBox<String> tipoDoc= new ChoiceBox<String>();
	@FXML private TextField tel_fijo;
	@FXML private TextField tel_celular;
	@FXML private TextField tel_opcional;
	@FXML private TextField email;
	
    @Override public void initialize(URL location, ResourceBundle resources) {
    	if(App.getInstance().getPersona()!=null){
    		apellido.setText(App.getInstance().getPersona().getPersonaEntiti().getApellido());
    		nombre.setText(App.getInstance().getPersona().getPersonaEntiti().getNombre());
    		tipoDoc.getSelectionModel().select(App.getInstance().getPersona().getPersonaEntiti().getTipoDoc());
    		nroDoc.setText(String.valueOf(App.getInstance().getPersona().getPersonaEntiti().getNroDoc()));
    		tel_fijo.setText(App.getInstance().getPersona().getPersonaEntiti().getTel_fijo());
    		tel_celular.setText(App.getInstance().getPersona().getPersonaEntiti().getTel_celular());
    		tel_opcional.setText(App.getInstance().getPersona().getPersonaEntiti().getTel_opcional());
    		email.setText(App.getInstance().getPersona().getPersonaEntiti().getEmail());
    	}
    }
    
    @FXML protected void processBuscarPersona(ActionEvent event) throws Exception{
    	App.getInstance().replaceSceneContent(App.buscarPersona);
    }
    
    @FXML protected void processEliminarPersona(ActionEvent event) {

    	Session session = SessionManager.getSession();

    	session.beginTransaction();
	
		Cliente c1 = (Cliente)App.getInstance().getPersona().getPersonaEntiti();
		
		c1.setNombre(nombre.getText());
		c1.setApellido( apellido.getText());
		c1.setTipo(Cliente.PERSONA);
		c1.setNroDoc(Integer.parseInt(nroDoc.getText()));
		c1.setTipoDoc(tipoDoc.getSelectionModel().getSelectedItem());
		c1.setTel_fijo(tel_fijo.getText());
		c1.setTel_celular(tel_celular.getText());
		c1.setTel_opcional(tel_opcional.getText());
		c1.setEmail(email.getText());

		
		session.update(c1);

		// Compromete los cambios
		session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();
		
		try {
			App.getInstance().replaceSceneContent(App.messages_ok);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
	@FXML
	protected void processVolver(ActionEvent event) {
		App.getInstance().pageAnterior();
	}

}
