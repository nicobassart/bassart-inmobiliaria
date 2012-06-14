package inmobiliaria.controller;

import inmobiliaria.App;
import inmobiliaria.entities.Alquileres;
import inmobiliaria.entities.CuotasInquilino;
import inmobiliaria.entities.CuotasInquilinosPersonaInmuebleDueno;
import inmobiliaria.entities.Inmueble;
import inmobiliaria.entities.Persona;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.Mes;
import inmobiliaria.utils.DateUtil;
import inmobiliaria.utils.TablasUtils;
import inmobiliaria.utils.Utils;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.hibernate.Session;

public class PreEliminarAlquilerController implements Initializable {
	private ObservableList<Mes> tableDataInquilino;
	@FXML 
	private TableView tableViewInquilino;
	
	@FXML private TextField nombreInquilino;
    @FXML private TextField calle;
    @FXML private TextField fechaInicio;
    @FXML private TextField fechaFin;
    @FXML private TextField importeDeposito;
    @FXML private TextField porcentajeDueno;

    @FXML private CheckBox abl;
    @FXML private CheckBox gas;
    @FXML private CheckBox internet;
    @FXML private CheckBox telefono;
    @FXML private CheckBox agua;
    @FXML private CheckBox cable;
	@FXML
	protected void processVolver(ActionEvent event) {
		App.getInstance().pageAnterior();
	}

	@FXML
	protected void proyectarInquilino(ActionEvent event) {
		
	}

	@FXML
	protected void processEliminar(ActionEvent event) {
		Session session = SessionManager.getSession();

		session.beginTransaction();

		Alquileres alq = (Alquileres) session.load(Alquileres.class, App
				.getInstance().getAlquileresInmueblePersonaView()
				.getAlquileresInmueblePersona().getIdalquiler());

		alq.setEstado("E");

		session.update(alq);
		// Compromete los cambios
		session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();

		try {
			App.getInstance().replaceSceneContent(App.messages_ok);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PreEliminarAlquilerController() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Session session = SessionManager.getSession();

		session.beginTransaction();

		Alquileres alq = (Alquileres) session.load(Alquileres.class, App
				.getInstance().getAlquileresInmueblePersonaView()
				.getAlquileresInmueblePersona().getIdalquiler());
		Persona personaInquilino= (Persona) session.load(Persona.class, alq.getIdPersonaInquilino());
		Inmueble inm= (Inmueble) session.load(Inmueble.class, alq.getIdInmueble());

		List<CuotasInquilino> cuota = session.createQuery("FROM inmobiliaria.entities.CuotasInquilino cuota WHERE cuota.alquileres.idAlquiler="+alq.getIdAlquiler() ).list();
		this.nombreInquilino.setText(personaInquilino.nombreCompleto());
		this.porcentajeDueno.setText(String.valueOf(alq.getPorcentajeDueno()));
		this.importeDeposito.setText(String.valueOf(alq.getImporteDeposito()));
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");  
		
		this.fechaInicio.setText(sdf.format(alq.getFechaInicio()));
		this.fechaFin.setText(sdf.format(alq.getFechaFin()));
		this.calle.setText(Utils.formatearCalle(inm.getCalle(), inm.getCalleNro(), inm.getCallePiso(), inm.getCalleDpto()));
		
		this.agua.setSelected(alq.isAgua());
		this.abl.setSelected(alq.isAbl());
		this.gas.setSelected(alq.isGas());
		this.telefono.setSelected(alq.isTelefono());
		this.agua.setSelected(alq.isAbl());
		this.cable.setSelected(alq.isCable());

		Iterator<CuotasInquilino> itVendedores = cuota.iterator();
		tableDataInquilino= tableViewInquilino.getItems();
		
		int total = DateUtil.getDateDiffInMonths( alq.getFechaInicio(),alq.getFechaFin());
		
		while (itVendedores.hasNext()) {
			CuotasInquilino cuot = itVendedores.next();
			
			int nroCuota = DateUtil.getDateDiffInMonths(alq.getFechaInicio(), cuot.getFecha());
			
    		Mes mescuota = new Mes(cuot.getMesanio() ,cuot.getPorcetaje(),String.valueOf(cuot.getValor()),cuot.getFecha(),nroCuota+1,total);
    		if(cuot.getPago()!=null){
    			mescuota.setEstado("Pagado");
    		}else{
    			mescuota.setEstado("Adeuda");
    		}
    		tableDataInquilino.add(mescuota);
		}

		tableViewInquilino.setItems(tableDataInquilino);
		
		TablasUtils.armarColumnasConsultarPagos(tableViewInquilino);
		// Compromete los cambios
		session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();
	}
}
