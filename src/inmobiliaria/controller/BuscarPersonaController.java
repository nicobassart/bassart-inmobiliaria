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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
				masterData.add(new Persona(cli.getNombre(), cli.getApellido(), cli.getEmail(),cli.getIdpersona(),cli));
				
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
		TableColumn colum0 = (TableColumn) tableDataPersonas.getColumns().get(0);
		colum0.setCellValueFactory(new PropertyValueFactory("nombre"));

		TableColumn colum1 = (TableColumn) tableDataPersonas.getColumns().get(1);
		colum1.setCellValueFactory(new PropertyValueFactory("apellido"));

		TableColumn colum2 = (TableColumn) tableDataPersonas.getColumns().get(2);
		colum2.setCellValueFactory(new PropertyValueFactory("email"));

		tableDataPersonas.setItems(filteredData);
		
		
		
		
		
		
		

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
		tableDataPersonas.setEditable(false);
		tableDataPersonas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Listen for text changes in the filter text field
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
