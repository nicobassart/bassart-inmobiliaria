package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.CuotasInquilinosPersonaInmuebleDueno;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.AlquileresView;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

public class HomeController implements Initializable {

	@FXML
	private TableView<AlquileresView> tableDataInquilino = new TableView<AlquileresView>();

	private List<AlquileresView> listaAlquileres;

	public HomeController() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listaAlquileres = tableDataInquilino.getItems();
		listaAlquileres.clear();
		
		Session session = SessionManager.getSession();

		@SuppressWarnings("unchecked")
		List<CuotasInquilinosPersonaInmuebleDueno> cuota = session.createQuery("FROM inmobiliaria.entities.CuotasInquilinosPersonaInmuebleDueno").list();

		Iterator<CuotasInquilinosPersonaInmuebleDueno> itVendedores = cuota.iterator();
		while (itVendedores.hasNext()) {
			CuotasInquilinosPersonaInmuebleDueno cli = itVendedores.next();

			SimpleDateFormat sdf = new SimpleDateFormat("MMyyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date;
			try {
				
				date = sdf.parse(StringUtils.leftPad(String.valueOf(cli.getMesanio()), 6, "0"));
				listaAlquileres.add(new AlquileresView(Utils.formatearNomApe(cli.getApellido(), cli.getNombre()), 
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
		TableColumn<AlquileresView, String> actionCol =  (TableColumn <AlquileresView, String>) tableDataInquilino.getColumns().get(4);
		actionCol.setCellFactory(new Callback<TableColumn<AlquileresView, String>, TableCell<AlquileresView, String>>() {

					@Override
					public TableCell<AlquileresView, String> call(TableColumn<AlquileresView, String> arg0) {
						final TableCell<AlquileresView,String> cell = new TableCell<AlquileresView,String>() {
							@Override
							public void updateItem(String value, boolean empty) {
								super.updateItem(value, empty);

								final VBox vbox = new VBox(1);
								//Image image = new Image(getClass().getResourceAsStream("/inmobiliaria/cash.png"));
								//Button button = new Button("Cobrar", new ImageView(image));
								Button button = new Button("Cobrar");
								button.getStyleClass().add("button");
								button.setStyle("-fx-padding: 2;");
								button.minWidth(80);
								final TableCell<AlquileresView,String> c = this;
								button.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										TableRow tableRow = c.getTableRow();
										AlquileresView adl = (AlquileresView)tableRow.getItem();
										App.getInstance().setAlquilerViewHome(adl);
										try {
											App.getInstance().replaceSceneContent("pagarCuota.fxml");
										} catch (Exception e) {
											// TODO Auto-generated catch block
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


//		//7777777
//		
// ContextMenu cm = new ContextMenu();
//		MenuItem cmItem1 = new MenuItem("Copy Image");
//		cmItem1.setOnAction(new EventHandler<ActionEvent>() {
//		    public void handle(ActionEvent e) {
//		        Clipboard clipboard = Clipboard.getSystemClipboard();
//		        ClipboardContent content = new ClipboardContent();
//		        //content.putImage(pic.getImage());
//		        clipboard.setContent(content);
//		    }
//		});
//
//		cm.getItems().add(cmItem1);
//		tableDataInquilino.setContextMenu(cm);
////		tableDataInquilino.buildEventDispatchChain(arg0)
////		tableDataInquilino.getContextMenu().addEventHandler(MouseEvent.MOUSE_CLICKED,
////		    new EventHandler<MouseEvent>() {
////		        @Override public void handle(MouseEvent e) {
////		           // if (e.getButton() == MouseButton.SECONDARY)  
////		               // cm.show(pic, e.getScreenX(), e.getScreenY());
////		        }
////		});
//		//777777
	}
}
