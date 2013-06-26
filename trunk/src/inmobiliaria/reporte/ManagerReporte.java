package inmobiliaria.reporte;

import inmobiliaria.App;
import inmobiliaria.interfaces.IAlquileresView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ManagerReporte {
	
	static public void generarComprobante(int totalServicios,IAlquileresView alqView){
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd 'de' MMMMMMMMM 'del' yyyy");
		
		int impor  = Integer.parseInt( alqView.importeProperty().getValue());
		int porcen = Integer.parseInt(alqView.getPorcentajeDueno() );
		int comi = impor * porcen/ 100;
		int subtotal = impor - comi;
		String cuota =  alqView.getIdCuota().toString();
		String cuotaNro= alqView.cuotaFomateada();
		//String porcentaje = App.getInstance().getAlquilerViewHome().getPorcentajeDueno();
		String nombredueno = alqView.nombreDuenoCompleta();
		
		//El importe dueno es el importe menos el porcentaje que se le cobra al dueno
		String comision  = String.valueOf(comi);
		String netoDueno = String.valueOf(subtotal+totalServicios);
		String netoInquilino = String.valueOf(impor + totalServicios);
		try {
			Map<String, Object> parameters = new HashMap<String,Object>();
			parameters.put("cuota",cuota);
			parameters.put("cuotaNro",cuotaNro);
			parameters.put("nombreinquilino",alqView.inquilinoNombreApellidoProperty().getValue());
			parameters.put("importe",alqView.importeProperty().getValue());
			parameters.put("mesalquiler",sdf1.format(alqView.getFechaCuota()) );
			parameters.put("nombredueno", nombredueno);
			parameters.put("calle", alqView.calleCompleta());
			parameters.put("fechaDelDia", sdf1.format(Calendar.getInstance().getTime()));
			parameters.put("importeDueno", String.valueOf(impor));
			parameters.put("comision", String.valueOf(comision));
			parameters.put("netoDueno", String.valueOf(netoDueno));
			parameters.put("servicios", String.valueOf(totalServicios));
			parameters.put("subtotal", String.valueOf(subtotal));
			parameters.put("totalInquilino", netoInquilino);
			
			Collection<String> colec = new ArrayList<String>();
			colec.add("field");

			JasperPrint print = JasperFillManager.fillReport(App.class.getResourceAsStream("reporte//comprobante_limpio.jasper"), parameters, new JRBeanCollectionDataSource(colec));
			
			JasperViewer.viewReport(print,false);
//			JasperViewer.exportReportToPdfFile(print,"C:\\prueba\\comprobante_" +  nombredueno +"_" + cuota +".pdf");
//			try {
//				File path = new File ("C:\\prueba\\comprobante_" +  nombredueno +"_" + cuota +".pdf");
//				Desktop.getDesktop().open(path);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
}