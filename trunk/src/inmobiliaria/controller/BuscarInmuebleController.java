package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.Inmueble;

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

public class BuscarInmuebleController implements Initializable {
	@FXML
	private TableView<Inmueble> tableDataInmuebles = new TableView<Inmueble>();
	@FXML
	private TextField filterField;
	
	private ObservableList<Inmueble> masterData = FXCollections.observableArrayList();
	private ObservableList<Inmueble> filteredData = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	public BuscarInmuebleController(){
		Session session = SessionManager.getSession();

		List<inmobiliaria.entities.Inmueble> inmuebles = session.createQuery("FROM inmobiliaria.entities.Inmueble").list();
		Iterator<inmobiliaria.entities.Inmueble> itInmuebles = inmuebles.iterator();
		while (itInmuebles.hasNext()) {
			inmobiliaria.entities.Inmueble inmueble = itInmuebles.next();
			if (inmueble != null)
				masterData.add(new Inmueble(inmueble.getCalle(), inmueble.getCalleNro(), inmueble.getCallePiso(),inmueble.getCalleDpto(),inmueble.getIdInmueble(),inmueble));
		}

		filteredData.addAll(masterData);
		
		masterData.addListener(new ListChangeListener<Inmueble>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends Inmueble> change) {
				updateFilteredData();
			}
		});
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn colum0 = (TableColumn) tableDataInmuebles.getColumns().get(0);
		colum0.setCellValueFactory(new PropertyValueFactory("calle"));

		TableColumn colum1 = (TableColumn) tableDataInmuebles.getColumns().get(1);
		colum1.setCellValueFactory(new PropertyValueFactory("calleNro"));

		TableColumn colum2 = (TableColumn) tableDataInmuebles.getColumns().get(2);
		colum2.setCellValueFactory(new PropertyValueFactory("callePiso"));
		
		TableColumn<Inmueble, String> actionCol =  (TableColumn <Inmueble, String>) tableDataInmuebles.getColumns().get(3);
		actionCol.setCellFactory(new Callback<TableColumn<Inmueble, String>, TableCell<Inmueble, String>>() {

					@Override
					public TableCell<Inmueble, String> call(TableColumn<Inmueble, String> arg0) {
						final TableCell<Inmueble,String> cell = new TableCell<Inmueble,String>() {
							@Override
							public void updateItem(String value, boolean empty) {
								super.updateItem(value, empty);

								final VBox vbox = new VBox(1);
								Button button = new Button("Borrar");
								button.getStyleClass().add("button");
								button.setStyle("-fx-padding: 2;");
								button.minWidth(80);
								final TableCell<Inmueble,String> c = this;
								button.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										TableRow tableRow = c.getTableRow();
										Inmueble adl = (Inmueble)tableRow.getItem();
										App.getInstance().setInmueble(adl);
										try {
											App.getInstance().replaceSceneContent("bajaPropiedad.fxml");
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
		TableColumn<Inmueble, String> actionColEditar =  (TableColumn <Inmueble, String>) tableDataInmuebles.getColumns().get(4);
		actionColEditar.setCellFactory(new Callback<TableColumn<Inmueble, String>, TableCell<Inmueble, String>>() {

					@Override
					public TableCell<Inmueble, String> call(TableColumn<Inmueble, String> arg0) {
						final TableCell<Inmueble,String> cell = new TableCell<Inmueble,String>() {
							@Override
							public void updateItem(String value, boolean empty) {
								super.updateItem(value, empty);

								final VBox vbox = new VBox(1);
								Button button = new Button("Editar");
								button.getStyleClass().add("button");
								button.setStyle("-fx-padding: 2;");
								button.minWidth(80);
								final TableCell<Inmueble,String> c = this;
								button.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										TableRow tableRow = c.getTableRow();
										Inmueble adl = (Inmueble)tableRow.getItem();
										App.getInstance().setInmueble(adl);
										try {
											App.getInstance().replaceSceneContent("modificacionPropiedad.fxml");
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

		tableDataInmuebles.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableDataInmuebles.setItems(filteredData);
		tableDataInmuebles.setEditable(false);
		tableDataInmuebles.setOnMousePressed(new EventHandler<MouseEvent>() {
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
			
		for (Inmueble p : masterData) {
			if (matchesFilter(p)) {
				filteredData.add(p);
			}
		}
		
		reapplyTableSortOrder();
	}
	/**
	 * Retorna verdadero en el caso de mach 
	 * 
	 * @param inmueble
	 * @return
	 */
	private boolean matchesFilter(Inmueble inmueble) {
		String filterString = filterField.getText();
		if (filterString == null || filterString.isEmpty()) {
			return true;
		}
		
		String lowerCaseFilterString = filterString.toLowerCase();
		
		if (inmueble.calleProperty().getValue().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (inmueble.calleDptoProperty().getValue().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (inmueble.callePisoProperty().getValue().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}
		
		return false; 
	}
	
	private void reapplyTableSortOrder() {
		ArrayList<TableColumn<Inmueble, ?>> sortOrder = new ArrayList<>(tableDataInmuebles.getSortOrder());
		tableDataInmuebles.getSortOrder().clear();
		tableDataInmuebles.getSortOrder().addAll(sortOrder);
	}
}
