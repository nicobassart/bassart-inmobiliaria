package inmobiliaria.controller;

import inmobiliaria.App;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class HeaderController implements Initializable  {
	@FXML private Label titulo;
	@FXML private TabPane tabPane;
	@FXML private ToolBar toolBar;
	
	private double mouseDragOffsetX = 0;
	private double mouseDragOffsetY = 0;
	private WindowResizeButton windowResizeButton;
	WindowButtons windowButtons;

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

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
	protected void processBuscarPersona(ActionEvent event) throws Exception{
		App.getInstance().clear();
		App.getInstance().replaceSceneContent(App.buscarPersona);
	}
    @FXML protected void processBuscarPropiedad(ActionEvent event) throws Exception{
    	App.getInstance().clear();
    	App.getInstance().replaceSceneContent(App.buscarInmueble);
    }

	@FXML
	protected void consultarAlquileres(ActionEvent event) throws Exception {
		App.getInstance().replaceSceneContent("consultarAlquileres.fxml");
	}

	@FXML
	protected void volver(ActionEvent event) throws Exception {
		App.getInstance().replaceSceneContent("home.fxml");
	}
	@FXML
	protected void vencimientosHisto(ActionEvent event) throws Exception {
		App.getInstance().replaceSceneContent("vencimientosHisto.fxml");
	}

	@FXML
	protected void changed(MouseEvent event) throws Exception {
		App.getInstance().setTabSeleccionado(tabPane.getSelectionModel().getSelectedIndex());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titulo.setText(App.getInstance().getTitulo());
		tabPane.getSelectionModel().select(App.getInstance().getTabSeleccionado());
		
		windowResizeButton = new WindowResizeButton(App.getInstance().getStage(), 800, 600);

		windowButtons = new WindowButtons(App.getInstance().getStage());

		toolBar.getItems().add(windowButtons);

//		// add window header double clicking
//		toolBar.setonMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				if (event.getClickCount() == 2) {
//					windowButtons.toogleMaximized();
//				}
//			}
//		});

		// add window dragging
//		toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				mouseDragOffsetX = event.getSceneX();
//				mouseDragOffsetY = event.getSceneY();
//			}
//		});

//		toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				if (!windowButtons.isMaximized()) {
//					App.getInstance().getStage().setX(event.getScreenX()-mouseDragOffsetX);
//					App.getInstance().getStage().setY(event.getScreenY()-mouseDragOffsetY);
//				}
//			}
//		});
	}
	@FXML
	public void onMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			windowButtons.toogleMaximized();
		}
	}
	@FXML
	public void onMouseDragged(MouseEvent event) {
		if (!windowButtons.isMaximized()) {
			App.getInstance().getStage().setX(event.getScreenX()-mouseDragOffsetX);
			App.getInstance().getStage().setY(event.getScreenY()-mouseDragOffsetY);
		}
	}
	@FXML
	public void onMousePressed(MouseEvent event) {
			mouseDragOffsetX = event.getSceneX();
			mouseDragOffsetY = event.getSceneY();
	}
}
