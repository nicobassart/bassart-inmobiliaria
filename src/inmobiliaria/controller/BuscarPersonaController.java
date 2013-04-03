package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.Cliente;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.Persona;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import org.hibernate.Session;

public class BuscarPersonaController implements Initializable {
	@FXML
	private TableView<Persona> tableDataPersonas = new TableView<Persona>();
	@FXML
	private TextField filterField;
	
	private ObservableList<Persona> masterData = FXCollections.observableArrayList();
	private ObservableList<Persona> filteredData = FXCollections.observableArrayList();
	
	@SuppressWarnings("unchecked")
	public BuscarPersonaController(){
		Session session = SessionManager.getSession();

		List<Cliente> vendedores = session.createQuery("FROM inmobiliaria.entities.Cliente").list();

		Iterator<Cliente> itVendedores = vendedores.iterator();
		while (itVendedores.hasNext()) {
			Cliente cli = itVendedores.next();
			if (cli != null){
				masterData.add(new Persona(cli.getNombre(), cli.getApellido(), cli.getEmail(),cli.getTel_celular(),cli.getTel_fijo(),cli.getIdpersona(),cli));
				
			}

		}
		filteredData.addAll(masterData);
		
		masterData.addListener(new ListChangeListener<Persona>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends Persona> change) {
				updateFilteredData();
			}
		});
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tableDataPersonas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn colum0 = (TableColumn) tableDataPersonas.getColumns().get(0);
		colum0.setCellValueFactory(new PropertyValueFactory("nombre"));

		TableColumn colum1 = (TableColumn) tableDataPersonas.getColumns().get(1);
		colum1.setCellValueFactory(new PropertyValueFactory("apellido"));

		TableColumn colum2 = (TableColumn) tableDataPersonas.getColumns().get(2);
		colum2.setCellValueFactory(new PropertyValueFactory("email"));

		TableColumn colum3 = (TableColumn) tableDataPersonas.getColumns().get(3);
		colum3.setCellValueFactory(new PropertyValueFactory("tel_fijo"));

		TableColumn colum4 = (TableColumn) tableDataPersonas.getColumns().get(4);
		colum4.setCellValueFactory(new PropertyValueFactory("tel_celular"));

		TableColumn<Persona, String> actionCol =  (TableColumn <Persona, String>) tableDataPersonas.getColumns().get(5);
		actionCol.setCellFactory(new Callback<TableColumn<Persona, String>, TableCell<Persona, String>>() {

					@Override
					public TableCell<Persona, String> call(TableColumn<Persona, String> arg0) {
						final TableCell<Persona,String> cell = new TableCell<Persona,String>() {
							@Override
							public void updateItem(String value, boolean empty) {
								super.updateItem(value, empty);

								final VBox vbox = new VBox(1);
								Button button = new Button("Borrar");
								button.getStyleClass().add("button");
								button.setStyle("-fx-padding: 2;");
								button.minWidth(80);
								final TableCell<Persona,String> c = this;
								button.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										TableRow tableRow = c.getTableRow();
										Persona adl = (Persona)tableRow.getItem();
										App.getInstance().setPersona(adl);
										try {
											App.getInstance().replaceSceneContent("bajaPersona.fxml");
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
								button.setVisible((c.getTableView().getItems().size()>c.getIndex()));
								vbox.getChildren().add(button);
								setGraphic(vbox);
							}
						};

						return cell;
					}

				});
		TableColumn<Persona, String> actionColEditar =  (TableColumn <Persona, String>) tableDataPersonas.getColumns().get(6);
		actionColEditar.setCellFactory(new Callback<TableColumn<Persona, String>, TableCell<Persona, String>>() {

					@Override
					public TableCell<Persona, String> call(TableColumn<Persona, String> arg0) {
						final TableCell<Persona,String> cell = new TableCell<Persona,String>() {
							@Override
							public void updateItem(String value, boolean empty) {
								super.updateItem(value, empty);

								final VBox vbox = new VBox(1);
								Button button = new Button("Editar");
								button.getStyleClass().add("button");
								button.setStyle("-fx-padding: 2;");
								button.minWidth(80);
								final TableCell<Persona,String> c = this;
								button.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										TableRow tableRow = c.getTableRow();
										Persona adl = (Persona)tableRow.getItem();
										App.getInstance().setPersona(adl);
										try {
											App.getInstance().replaceSceneContent("modificacionPersona.fxml");
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
								button.setVisible((c.getTableView().getItems().size()>c.getIndex()));
								vbox.getChildren().add(button);
								setGraphic(vbox);
							}
						};

						return cell;
					}

				});
		
		
		tableDataPersonas.setOnMousePressed(new EventHandler<MouseEvent>() {

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

		tableDataPersonas.setItems(filteredData);
		tableDataPersonas.setEditable(false);
		
		filterField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				
				updateFilteredData();
			}
		});


	}
	private void updateFilteredData() {
		filteredData.clear();
			
		for (Persona p : masterData) {
			if (matchesFilter(p)) {
				filteredData.add(p);
			}
		}
		
		reapplyTableSortOrder();
	}
	/**
	 * Retorna verdadero en el caso de mach 
	 * 
	 * @param perona
	 * @return
	 */
	private boolean matchesFilter(Persona perona) {
		String filterString = filterField.getText();
		if (filterString == null || filterString.isEmpty()) {
			return true;
		}
		
		String lowerCaseFilterString = filterString.toLowerCase();
		
		if (perona.nombreProperty().getValue().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (perona.apellidoProperty().getValue().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (perona.emailProperty().getValue().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}
		
		return false; 
	}
	
	private void reapplyTableSortOrder() {
		ArrayList<TableColumn<Persona, ?>> sortOrder = new ArrayList<>(tableDataPersonas.getSortOrder());
		tableDataPersonas.getSortOrder().clear();
		tableDataPersonas.getSortOrder().addAll(sortOrder);
	}
}
