package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.Cliente;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.Persona;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import org.hibernate.Session;

public class BuscarPersonaController implements Initializable {
	private ObservableList<Persona> data;

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

		List<Cliente> vendedores = session.createQuery("FROM inmobiliaria.entities.Cliente").list();

		Iterator<Cliente> itVendedores = vendedores.iterator();
		while (itVendedores.hasNext()) {
			Cliente cli = itVendedores.next();
			if (cli != null)
				data.add(new Persona(cli.getNombre(), cli.getApellido(), cli.getCalle(),cli.getIdpersona(),cli));

		}

		// Cierra la sesion de trabajo
		session.close();

		TableColumn colum0 = (TableColumn) tab.getColumns().get(0);
		colum0.setCellValueFactory(new PropertyValueFactory("nombre"));

		TableColumn colum1 = (TableColumn) tab.getColumns().get(1);
		colum1.setCellValueFactory(new PropertyValueFactory("apellido"));

		TableColumn colum2 = (TableColumn) tab.getColumns().get(2);
		colum2.setCellValueFactory(new PropertyValueFactory("email"));

		tab.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// Acá tenemos la perona que selecciono dentro del listado de personas
				Persona selectedItem = (Persona)((TableView )arg0.getSource()).getSelectionModel().getSelectedItem();
				App.getInstance().setPersona(selectedItem);
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
