package inmobiliaria.testers;


import inmobiliaria.manager.SessionManager;

import org.hibernate.Session;

public class Test {


	public static void main(String[] args) {
		// Obtiene la sesion de trabajo
		Session session = SessionManager.getSession();

		// Inicia la transaccion
		session.beginTransaction();

		// PARTE #1 -- Inserta compradores y vendedores

		// Inserta clientes
//		Cliente c1 = new Cliente("Juan", "Perez","Las Hera",  "7545","23",
//				Cliente.PERSONA);
//		Cliente c2 = new Cliente("Pedro", "Perez",  "Las Hera",  "7545","23", Cliente.PERSONA);
//		Cliente c3 = new Cliente("Mario", "Sanchez", "Las Hera",  "7545","23", Cliente.PERSONA);
//		Cliente c4 = new Cliente("Diego", "Soler",  "Las Hera",  "7545","23", Cliente.PERSONA);
//		session.save(c1);
//		session.save(c2);
//		session.save(c3);
//		session.save(c4);

		// Inserta vendedores
//		Vendedor v1 = new Vendedor("Pablo", "Pouto", "Av San Martin 578",
//				"8-524", Date.valueOf("2002-05-01"));
//		Vendedor v2 = new Vendedor("Miguel", "Venito", "Pellegrini 269",
//				"8-254", Date.valueOf("2000-08-04"));
//		Vendedor v3 = new Vendedor("Maria", "Santos", "Paraguay 894", "8-365",
//				Date.valueOf("2003-09-24"));
//		Vendedor v4 = new Vendedor("Marcela", "Cristen", "Viamonte 422",
//				"8-529", Date.valueOf("2001-02-18"));
//		session.save(v1);
//		session.save(v2);
//		session.save(v3);
//		session.save(v4);

		System.out
				.println("Los clientes y los vendedores aun sido insertados....");

		// PARTE #2 -- Informar Compradores
		/*
		 * System.out.println("Compradores--------------------------------------"
		 * ); List<Cliente> compradores =
		 * session.createQuery("FROM ar.com.educacionit.hibernate.entities.Cliente"
		 * ).list(); Iterator<Cliente> itCompradores = compradores.iterator();
		 * while(itCompradores.hasNext()) System.out.println(
		 * itCompradores.next() );
		 * 
		 * // PARTE #3 -- Informar Vendedores
		 * System.out.println("Vendedores--------------------------------------"
		 * ); List<Vendedor> vendedores =
		 * session.createQuery("FROM ar.com.educacionit.hibernate.entities.Vendedor"
		 * ).list(); Iterator<Vendedor> itVendedores = vendedores.iterator();
		 * while(itVendedores.hasNext()) System.out.println( itVendedores.next()
		 * );
		 */

		// Compromete los cambios
		session.getTransaction().commit();

		// Cierra la sesion de trabajo
		session.close();
	}
}
