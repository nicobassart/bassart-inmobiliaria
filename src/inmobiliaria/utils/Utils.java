package inmobiliaria.utils;

public class Utils {
	static public String padIzq(String in){
		return in.format("%10s", in);
	}
	static public String formatearCalle(String calle, String calleNro,String callePiso,String calleDpto){

		if(calleNro!=null && !"".equals(calleNro))
			calle = calle + " Nro "+ calleNro ; 
		
		if(callePiso!=null && !"".equals(callePiso))
			calle = calle +" Piso "+callePiso ;
		
		if(calleDpto!=null && !"".equals(calleDpto))
			calle = calle +" Dpto "+calleDpto ; 
			
		return calle;
	}

	static public String formatearNomApe( String apellido,String nombre){
		if(nombre!=null && !"".equals(nombre))
			apellido = apellido + ", "+ nombre ; 
		return apellido;
	}
}
