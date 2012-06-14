package inmobiliaria.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilValidarCampos {
	/**
	 * Valida que no este vacio ni null
	 * returno true si tiene valor
	 * 
	 */
	public static boolean notEmptyString(String valor){
		return !("".equals(valor) || valor == null);
	}
	/**
	 * Verofica si es numerico 
	 * @param valor
	 * 
	 */
	public static boolean isNumeric(String valor){
		return valor.matches("[0-9]*"); 
	}
	/**
	 * Varifica que sea un DNI
	 * retorna true si es DNI
	 */
	public static boolean isDNI(String documento){
		return (documento.length()<10 && documento.length()>4) ;
	}
	/**
	 *  Retorna true si pasa la validacion
	 * @param text
	 * @return
	 */
	public static boolean validarFormatoFecha(String text) {
		return text.matches("[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]");
	}
	public static boolean validarDiferenciaFechas(String fechaInicio, String fechaFin) {
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		Date fechaInicioD=null ;
		Date fechaFinD=null ;
		try {
			fechaInicioD = sdf.parse(fechaInicio);
			fechaFinD = sdf.parse(fechaFin);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

		return (fechaFinD.compareTo(fechaInicioD)>0);
	}
}
