package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.CuotasInquilinosPersonaInmuebleDueno;
import inmobiliaria.entities.CuotasPagasInquilinosPersonaInmuebleDueno;
import inmobiliaria.interfaces.IAlquileresView;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.AlquileresPagosView;
import inmobiliaria.model.AlquileresView;
import inmobiliaria.reporte.ManagerReporte;
import inmobiliaria.utils.TablasUtils;
import inmobiliaria.utils.Utils;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

public class VencimientosHistoController implements Initializable {

	@FXML
	private TableView<AlquileresPagosView> tableDataInquilino = new TableView<AlquileresPagosView>();

	private List<AlquileresPagosView> listaAlquileres;

	public VencimientosHistoController() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listaAlquileres = tableDataInquilino.getItems();
		listaAlquileres.clear();
		
		Session session = SessionManager.getSession();

		@SuppressWarnings("unchecked")
		List<CuotasPagasInquilinosPersonaInmuebleDueno> cuota = session.createQuery("FROM inmobiliaria.entities.CuotasPagasInquilinosPersonaInmuebleDueno").list();

		Iterator<CuotasPagasInquilinosPersonaInmuebleDueno> itVendedores = cuota.iterator();
		while (itVendedores.hasNext()) {
			CuotasPagasInquilinosPersonaInmuebleDueno cli = itVendedores.next();

			SimpleDateFormat sdf = new SimpleDateFormat("MMyyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date;
			try {
				
				date = sdf.parse(StringUtils.leftPad(String.valueOf(cli.getMesanio()), 6, "0"));
				listaAlquileres.add(new AlquileresPagosView(Utils.formatearNomApe(cli.getApellido(), cli.getNombre()), 
						cli.getCalle()
						,cli.getCalleNro()
						, cli.getCallePiso()
						, cli.getCalleDpto(), 
						String.valueOf(cli.getValor()),
						sdf1.format(date), 
						cli.getIdinmueble(),
						cli.getIdcuota(),
						cli.getIdalquiler(),
						date,
						cli.getApellidoDueno(),
						cli.getNombreDueno(),
						cli.getPorcentajeDueno(),cli));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		tableDataInquilino.setItems( FXCollections.observableArrayList(listaAlquileres));
		TablasUtils.armarColumnasHome(tableDataInquilino);
		tableDataInquilino.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		//TableColumn<String, String> actionCol = new TableColumn<String, String>("Cobrar");
		TableColumn<AlquileresPagosView, String> actionCol =  (TableColumn <AlquileresPagosView, String>) tableDataInquilino.getColumns().get(4);
		actionCol.setCellFactory(new Callback<TableColumn<AlquileresPagosView, String>, TableCell<AlquileresPagosView, String>>() {

					@Override
					public TableCell<AlquileresPagosView, String> call(TableColumn<AlquileresPagosView, String> arg0) {
						final TableCell<AlquileresPagosView,String> cell = new TableCell<AlquileresPagosView,String>() {
							@Override
							public void updateItem(String value, boolean empty) {
								super.updateItem(value, empty);

								final VBox vbox = new VBox(1);
								//Image image = new Image(getClass().getResourceAsStream("/inmobiliaria/cash.png"));
								//Button button = new Button("Cobrar", new ImageView(image));
								Button button = new Button("Ver");
								button.getStyleClass().add("button");
								button.setStyle("-fx-padding: 2;");
								button.minWidth(80);
								final TableCell<AlquileresPagosView,String> c = this;
								button.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										TableRow tableRow = c.getTableRow();
										AlquileresPagosView adl = (AlquileresPagosView)tableRow.getItem();
										//App.getInstance().setAlquilerViewHome(adl);
										try {
											ManagerReporte.generarComprobante(100,(IAlquileresView)adl);
											//App.getInstance().replaceSceneContent("pagarCuota.fxml");
										} catch (Exception e) {
											// TODO Auto-generated catch block
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
