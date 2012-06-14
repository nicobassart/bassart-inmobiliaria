package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.Cliente;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.Inmueble;
import inmobiliaria.model.Persona;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import org.hibernate.Session;

public class BuscarInmuebleController implements Initializable {
	private TableView<Inmueble> table = new TableView<Inmueble>();

	private ObservableList<Inmueble> data;

	@FXML private TextField apellido;
	@FXML private TextField nombre;
	@FXML private TextField calle;
	@FXML private TextField calleNumero;
	@FXML private TextField callePiso;
	@FXML private TextField calleDpto;
	@FXML private TextArea address;
	@FXML private CheckBox subscribed;
	@FXML private Label success;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	protected void processBuscar(ActionEvent event) {

		BorderPane par = (BorderPane) App.getInstance().getScene().getRoot();

		GridPane grid = (GridPane) par.getCenter();

		TableView tab = (TableView) grid.getChildren().get(4);
		data = tab.getItems();

		Session session = SessionManager.getSession();

		// session.beginTransaction();

		List<inmobiliaria.entities.Inmueble> inmuebles = session.createQuery(
				"FROM inmobiliaria.entities.Inmueble").list();
		Iterator<inmobiliaria.entities.Inmueble> itInmuebles = inmuebles.iterator();
		while (itInmuebles.hasNext()) {
			inmobiliaria.entities.Inmueble inmueble = itInmuebles.next();
			if (inmueble != null)
				data.add(new Inmueble(inmueble.getCalle(), inmueble.getCalleNro(), inmueble.getCallePiso(),inmueble.getCalleDpto(),inmueble.getIdInmueble(),inmueble));

		}
		// Compromete los cambios
		// session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();

		TableColumn colum0 = (TableColumn) tab.getColumns().get(0);
		colum0.setCellValueFactory(new PropertyValueFactory("calle"));

		TableColumn colum1 = (TableColumn) tab.getColumns().get(1);
		colum1.setCellValueFactory(new PropertyValueFactory("calleNro"));

		TableColumn colum2 = (TableColumn) tab.getColumns().get(2);
		colum2.setCellValueFactory(new PropertyValueFactory("callePiso"));

		tab.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// Acá tenemos la perona que selecciono dentro del listado de personas
				Inmueble selectedItem = (Inmueble)((TableView )arg0.getSource()).getSelectionModel().getSelectedItem();
				App.getInstance().setInmueble(selectedItem);
				try {
					App.getInstance().pageAnterior();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		tab.setEditable(false);
		tab.setItems(data);

	}

}
