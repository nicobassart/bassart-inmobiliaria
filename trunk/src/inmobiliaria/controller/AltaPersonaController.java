package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.Cliente;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.utils.MessageBox;
import inmobiliaria.utils.UtilValidarCampos;

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


public class AltaPersonaController implements Initializable {
	@FXML private TextField apellido;
	@FXML private TextField nombre;
	@FXML private TextField nroDoc;
	@FXML private TextField tel_fijo;
	@FXML private TextField tel_celular;
	@FXML private TextField tel_opcional;
	@FXML private TextField email;
	
    @Override 
    public void initialize(URL location, ResourceBundle resources) {
   	
    }
    
    @FXML protected void guardar(ActionEvent event) {

    	if(!this.validarForm(apellido,nombre,nroDoc,email)) return;
    	
    	Session session = SessionManager.getSession();

    	session.beginTransaction();
		BorderPane par = (BorderPane) App.getInstance().getScene().getRoot();

		GridPane grid = (GridPane) par.getCenter();

		ChoiceBox<String> tab = (ChoiceBox<String> ) grid.getChildren().get(10);
		
		Cliente c1 = new Cliente();
		
		c1.setNombre(nombre.getText());
		c1.setApellido( apellido.getText());
		c1.setTipo(Cliente.PERSONA);
		c1.setNroDoc(Integer.parseInt(nroDoc.getText()));
		c1.setTipoDoc(tab.getSelectionModel().getSelectedItem());
		c1.setTel_fijo(tel_fijo.getText());
		c1.setTel_celular(tel_celular.getText());
		c1.setTel_opcional(tel_opcional.getText());
		c1.setEmail(email.getText());
		
		session.save(c1);

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

	private boolean validarForm(TextField apellido2, TextField nombre2,
			TextField nroDoc2, TextField email2) {
		String errorAcum="";

		if(!UtilValidarCampos.notEmptyString(apellido2.getText())) errorAcum += "Debe ingresar apellido.\n";
		
		if(!UtilValidarCampos.notEmptyString(nombre2.getText())) errorAcum += "Debe ingresar nombre.\n";
			
		if(!UtilValidarCampos.notEmptyString(nroDoc2.getText())) errorAcum += "Debe ingresar número de documento.\n";
			
		if(!UtilValidarCampos.notEmptyString(email2.getText())) errorAcum += "Debe ingresar email.\n";
		
		if(!UtilValidarCampos.isNumeric(nroDoc2.getText())) errorAcum+= ("El número de documento debe ser númerico.\n");
		
		if(!errorAcum.isEmpty()){ 
			MessageBox a = new MessageBox((App.getInstance().getStage()),errorAcum);
			return false;
		}
	
		return true;
	}
}
