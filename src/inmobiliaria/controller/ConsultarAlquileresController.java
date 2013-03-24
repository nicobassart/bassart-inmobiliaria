package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.AlquileresInmueblePersona;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.AlquileresInmueblePersonaView;
import inmobiliaria.utils.TablasUtils;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import org.hibernate.Session;

public class ConsultarAlquileresController implements Initializable {
	@FXML
	public TableView<AlquileresInmueblePersonaView> tableDataAlquileres = new TableView<AlquileresInmueblePersonaView>();
	
	private ObservableList<AlquileresInmueblePersonaView> listaAlquileres;



	public ConsultarAlquileresController() {
		tableDataAlquileres.setItems(null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Session session = SessionManager.getSession();
		
		List<AlquileresInmueblePersona> cuota = session.createQuery("FROM inmobiliaria.entities.AlquileresInmueblePersona").list();
		listaAlquileres = tableDataAlquileres.getItems(); 
		Iterator<AlquileresInmueblePersona> itVendedores = cuota.iterator();
		while (itVendedores.hasNext()) {
			AlquileresInmueblePersona alquiler = itVendedores.next();
			listaAlquileres.add(new AlquileresInmueblePersonaView(alquiler));
		}
		TablasUtils.armarColumnasConsultaAlquileres(tableDataAlquileres);
		tableDataAlquileres.setItems(listaAlquileres);

		
		TableColumn<AlquileresInmueblePersonaView, String> actionCol =  (TableColumn <AlquileresInmueblePersonaView, String>) tableDataAlquileres.getColumns().get(3);
		actionCol.setCellFactory(new Callback<TableColumn<AlquileresInmueblePersonaView, String>, TableCell<AlquileresInmueblePersonaView, String>>() {
			@Override
			public TableCell<AlquileresInmueblePersonaView, String> call(TableColumn<AlquileresInmueblePersonaView, String> arg0) {
				final TableCell<AlquileresInmueblePersonaView,String> cell = new TableCell<AlquileresInmueblePersonaView,String>() {
					@Override
					public void updateItem(String value, boolean empty) {
						super.updateItem(value, empty);

						final VBox vbox = new VBox(1);
//						Image image = new Image(getClass().getResourceAsStream("/pages/img/eliminar.png"));
//						Button button = new Button("", new ImageView(image));
						Button button = new Button("Eliminar");
						button.getStyleClass().add("deleteButton");
						button.setStyle("-fx-padding: 2;");
						final TableCell<AlquileresInmueblePersonaView,String> c = this;
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								TableRow tableRow = c.getTableRow();
								AlquileresInmueblePersonaView adl = (AlquileresInmueblePersonaView)tableRow.getItem();
								App.getInstance().setAlquileresInmueblePersonaView(adl);
								try {
									App.getInstance().replaceSceneContent("preEliminarAlquiler.fxml");
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						vbox.getChildren().add(button);
						setGraphic(vbox);
					}
				};

				return cell;
			}

		});
	}

	public TableView<AlquileresInmueblePersonaView> getTableDataAlquileres() {
		return tableDataAlquileres;
	}

	public void setTableDataAlquileres(
			TableView<AlquileresInmueblePersonaView> tableDataAlquileres) {
		this.tableDataAlquileres = tableDataAlquileres;
	}
	


	@FXML
	protected void processBuscar(ActionEvent event) {

		Session session = SessionManager.getSession();
		
		List<AlquileresInmueblePersona> cuota = session.createQuery("FROM inmobiliaria.entities.AlquileresInmueblePersona").list();
		listaAlquileres = tableDataAlquileres.getItems(); 
		Iterator<AlquileresInmueblePersona> itVendedores = cuota.iterator();
		while (itVendedores.hasNext()) {
			AlquileresInmueblePersona alquiler = itVendedores.next();
			listaAlquileres.add(new AlquileresInmueblePersonaView(alquiler));
		}
		TablasUtils.armarColumnasConsultaAlquileres(tableDataAlquileres);
		tableDataAlquileres.setItems(listaAlquileres);

		
		TableColumn<AlquileresInmueblePersonaView, String> actionCol =  (TableColumn <AlquileresInmueblePersonaView, String>) tableDataAlquileres.getColumns().get(3);
		actionCol.setCellFactory(new Callback<TableColumn<AlquileresInmueblePersonaView, String>, TableCell<AlquileresInmueblePersonaView, String>>() {
			@Override
			public TableCell<AlquileresInmueblePersonaView, String> call(TableColumn<AlquileresInmueblePersonaView, String> arg0) {
				final TableCell<AlquileresInmueblePersonaView,String> cell = new TableCell<AlquileresInmueblePersonaView,String>() {
					@Override
					public void updateItem(String value, boolean empty) {
						super.updateItem(value, empty);

						final VBox vbox = new VBox(1);
						//Image image = new Image(getClass().getResourceAsStream("/pages/img/eliminar.png"));
//						Button button = new Button("Elimina", new ImageView(image));
						Button button = new Button("Elimina");
						button.getStyleClass().add("deleteButton");
						button.setStyle("-fx-padding: 2;");
						final TableCell<AlquileresInmueblePersonaView,String> c = this;
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								TableRow tableRow = c.getTableRow();
								AlquileresInmueblePersonaView adl = (AlquileresInmueblePersonaView)tableRow.getItem();
								App.getInstance().setAlquileresInmueblePersonaView(adl);
								try {
									App.getInstance().replaceSceneContent("preEliminarAlquiler.fxml");
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						vbox.getChildren().add(button);
						setGraphic(vbox);
					}
				};

				return cell;
			}

		});

	}
	
}
