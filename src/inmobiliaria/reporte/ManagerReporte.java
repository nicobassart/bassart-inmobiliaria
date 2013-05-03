package inmobiliaria.reporte;

import inmobiliaria.App;

import java.io.File;
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
	
	static public void generarComprobante(int totalServicios){
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd 'de' MMMMMMMMM 'del' yyyy");
		
		int impor  =Integer.parseInt( App.getInstance().getAlquilerViewHome().importeProperty().getValue());
		int porcen = Integer.parseInt(App.getInstance().getAlquilerViewHome().getPorcentajeDueno() );
		int comi = impor * porcen/ 100;
		int subtotal = impor - comi;
		String cuota =  App.getInstance().getAlquilerViewHome().getIdCuota().toString();
		String cuotaNro= App.getInstance().getAlquilerViewHome().cuotaFomateada();
		String porcentaje = App.getInstance().getAlquilerViewHome().getPorcentajeDueno();
		String nombredueno = App.getInstance().getAlquilerViewHome().nombreDuenoCompleta();
		
		//El importe dueno es el importe menos el porcentaje que se le cobra al dueno
		String importeDueno;
		String comision  = String.valueOf(comi);
		String netoDueno = String.valueOf(subtotal+totalServicios);
		String netoInquilino = String.valueOf(impor + totalServicios);
		try {
			Map<String, Object> parameters = new HashMap<String,Object>();
			parameters.put("cuota",cuota);
			parameters.put("cuotaNro",cuotaNro);
			parameters.put("nombreinquilino", App.getInstance().getAlquilerViewHome().inquilinoNombreApellidoProperty().getValue());
			parameters.put("importe", App.getInstance().getAlquilerViewHome().importeProperty().getValue());
			parameters.put("mesalquiler",sdf1.format(App.getInstance().getAlquilerViewHome().getFechaCuota()) );
			parameters.put("nombredueno", nombredueno);
			parameters.put("calle",  App.getInstance().getAlquilerViewHome().calleCompleta());
			parameters.put("fechaDelDia", sdf1.format(Calendar.getInstance().getTime()));
			parameters.put("importeDueno", String.valueOf(impor));
			parameters.put("comision", String.valueOf(comision));
			parameters.put("netoDueno", String.valueOf(netoDueno));
			parameters.put("servicios", String.valueOf(totalServicios));
			parameters.put("subtotal", String.valueOf(subtotal));
			parameters.put("totalInquilino", netoInquilino);
			
			Collection<String> colec = new ArrayList<String>();
			colec.add("field");

			String pat = new File(".").getAbsolutePath();
			

			JasperPrint print = JasperFillManager.fillReport(App.class.getResourceAsStream("reporte//comprobante_limpio.jasper"), parameters, new JRBeanCollectionDataSource(colec));
			
			JasperViewer.viewReport(print);
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