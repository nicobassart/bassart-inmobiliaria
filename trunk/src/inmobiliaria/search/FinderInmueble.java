package inmobiliaria.search;

import inmobiliaria.entities.Alquileres;
import inmobiliaria.manager.SessionManager;
import inmobiliaria.model.AlquileresView;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;

public class FinderInmueble {
	
	public AlquileresView finderInmueble(AlquileresView alquileresView){
		Session session = SessionManager.getSession();
		
		Query cuota = session.createQuery("select alq FROM inmobiliaria.entities.Alquileres alq WHERE alq.idAlquiler = :id");
		cuota.setLong("id", alquileresView.getIdAlquiler());
		
		Iterator it = cuota.iterate();
		
		alquileresView.setAlquileres((Alquileres) it.next());
		return alquileresView;
	}
}
