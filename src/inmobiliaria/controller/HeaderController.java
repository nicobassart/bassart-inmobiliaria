package inmobiliaria.controller;

import java.net.URL;
import java.util.ResourceBundle;

import inmobiliaria.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HeaderController implements Initializable  {
	@FXML private Label titulo;
	
	@FXML
	protected void altaPersona(ActionEvent event) throws Exception {
		App.getInstance().clear();
		App.getInstance().replaceSceneContent("altaPersona.fxml");
	}

	@FXML
	protected void bajaPersona(ActionEvent event) throws Exception {
		App.getInstance().clear();
		App.getInstance().replaceSceneContent("bajaPersona.fxml");
	}

	@FXML
	protected void modificacionPersona(ActionEvent event) throws Exception {
		App.getInstance().clear();
		App.getInstance().replaceSceneContent("modificacionPersona.fxml");
	}

	@FXML
	protected void altaPropiedad(ActionEvent event) throws Exception {
		App.getInstance().clear();
		App.getInstance().replaceSceneContent("altaPropiedad.fxml");
	}

	@FXML
	protected void bajaPropiedad(ActionEvent event) throws Exception {
		App.getInstance().clear();
		App.getInstance().replaceSceneContent("bajaPropiedad.fxml");
	}

	@FXML
	protected void modificacionPropiedad(ActionEvent event) throws Exception {
		App.getInstance().clear();
		App.getInstance().replaceSceneContent("modificacionPropiedad.fxml");
	}

	@FXML
	protected void nuevoAlquiler(ActionEvent event) throws Exception {
		App.getInstance().clear();
		App.getInstance().replaceSceneContent("nuevoAlquiler.fxml");
	}

	@FXML
	protected void consultarAlquileres(ActionEvent event) throws Exception {
		App.getInstance().replaceSceneContent("consultarAlquileres.fxml");
	}

	@FXML
	protected void volver(ActionEvent event) throws Exception {
		App.getInstance().replaceSceneContent("home.fxml");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titulo.setText(App.getInstance().getTitulo());
	}
}
