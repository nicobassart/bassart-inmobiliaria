package inmobiliaria.utils;

import inmobiliaria.model.Mes;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * @author Bassart Pedro Nicolás
 * @since 20120211
 * @version 1.0
 */
public final class TablasUtils {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void armarColumnas(TableView proyectarInquilino) {
		TableColumn colum0 = (TableColumn) proyectarInquilino.getColumns().get(0);
		colum0.setCellValueFactory(new PropertyValueFactory("mesAnio"));

		TableColumn colum1 = (TableColumn) proyectarInquilino.getColumns().get(1);
		colum1.setCellValueFactory(new PropertyValueFactory("porcentaje"));
		
		Callback<TableColumn, TableCell> cellFactory = 
				new Callback<TableColumn, TableCell>() {
			public TableCell call(TableColumn p) {
				return new EditingCell();
			}
		};
		colum1.setCellFactory(cellFactory);
		colum1.setOnEditCommit(new EventHandler<CellEditEvent<Mes, String>>() {
		    @Override public void handle(CellEditEvent<Mes, String> t) {
		    	Mes unMes = (Mes)t.getTableView().getItems().get(
			            t.getTablePosition().getRow());
		    	//unMes.setMes(t.getNewValue());
		        for (Mes mes : t.getTableView().getItems()) {
		        	if(mes.getFecha().compareTo(unMes.getFecha()) >= 0){
		        		mes.setPorcentaje(t.getNewValue());
		        	}
					
				}
		    }
		});
		
		TableColumn colum2 = (TableColumn) proyectarInquilino.getColumns().get(2);
		colum2.setCellValueFactory(new PropertyValueFactory("valor"));

		TableColumn colum3 = (TableColumn) proyectarInquilino.getColumns().get(3);
		colum3.setCellValueFactory(new PropertyValueFactory("cuotaNro"));
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void armarColumnasHome(TableView alquileresACobrar) {
		TableColumn colum0 = (TableColumn) alquileresACobrar.getColumns().get(0);
		colum0.setCellValueFactory(new PropertyValueFactory("inquilinoNombreApellido"));
		TableColumn colum1 = (TableColumn) alquileresACobrar.getColumns().get(1);
		colum1.setCellValueFactory(new PropertyValueFactory("calleCompleta"));
		TableColumn colum2 = (TableColumn) alquileresACobrar.getColumns().get(2);
		colum2.setCellValueFactory(new PropertyValueFactory("importe"));
		TableColumn colum3 = (TableColumn) alquileresACobrar.getColumns().get(3);
		colum3.setCellValueFactory(new PropertyValueFactory("periodo"));
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void armarColumnasConsultaAlquileres(TableView alquileresACobrar) {
		TableColumn colum0 = (TableColumn) alquileresACobrar.getColumns().get(0);
		colum0.setCellValueFactory(new PropertyValueFactory("nombreApellido"));
		TableColumn colum1 = (TableColumn) alquileresACobrar.getColumns().get(1);
		colum1.setCellValueFactory(new PropertyValueFactory("calleCompleta"));
		TableColumn colum2 = (TableColumn) alquileresACobrar.getColumns().get(2);
		colum2.setCellValueFactory(new PropertyValueFactory("estado"));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void armarColumnasConsultarPagos(TableView mesesalquiler) {
		TableColumn colum0 = (TableColumn) mesesalquiler.getColumns().get(0);
		colum0.setCellValueFactory(new PropertyValueFactory("mesAnio"));
		TableColumn colum1 = (TableColumn) mesesalquiler.getColumns().get(1);
		colum1.setCellValueFactory(new PropertyValueFactory("porcentaje"));
		TableColumn colum2 = (TableColumn) mesesalquiler.getColumns().get(2);
		colum2.setCellValueFactory(new PropertyValueFactory("valor"));
		TableColumn colum3 = (TableColumn) mesesalquiler.getColumns().get(3);
		colum3.setCellValueFactory(new PropertyValueFactory("cuotaNro"));
		TableColumn colum4 = (TableColumn) mesesalquiler.getColumns().get(4);
		colum4.setCellValueFactory(new PropertyValueFactory("estado"));
	}
}