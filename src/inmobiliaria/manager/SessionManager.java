/*
 * EducacionITSessionManager.java
 *
 */

package inmobiliaria.manager;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class SessionManager {
    
    // Atributo que guarda una fabrica de sesiones
    private static SessionFactory factory;
    
    /**
     * Creates a new instance of EducacionITSessionManager 
     */
    public SessionManager() {
    }
    
    public static Session getSession() throws HibernateException
    {
        // Si la fabrica de sesiones ya esta creada, entonces retorna una sesion 
        if(factory != null)
        {
            return factory.openSession();
        }
        
        // Si la fabrica de sesiones no esta creada, entonces crea una y retorna una sesion
        else
        {
            // Instancia un objeto del tipo Configuration
            Configuration config = new Configuration();

            // Registra los mappers en la configuracion
            registerMappers(config);

            // Establece las propiedades de configuracion
            config.setProperties( getHibernateProperties() );

            // Guarda la fabrica de sesiones
            factory = config.buildSessionFactory();

            // Retorna una sesion de trabajo
            return factory.openSession();
        }
    }
    
    private static Properties getHibernateProperties()
    {
        // Instancia un objeto del tipo Properties
        Properties props = new Properties();
        
        // Establece el driver de conexion dependiente del RDBMS
        props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        
        // Establece la url de conexion dependiente del RDBMS
        props.put("hibernate.connection.url", "jdbc:mysql://localhost:3307/inmobiliaria");
        System.out.println("Conecta");
        // Establece el usuario
        props.put("hibernate.connection.username", "root");
        
        // Establece la clave
        props.put("hibernate.connection.password", "root");
        
        // Establece el dialecto a utilizar
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        
        // Establece el uso de logging, deber� existir el archivo log4j.properties
        props.put("hibernate.show_sql", "true");
        
        // Retorna las propiedades
        System.out.println("Conecta");
        return props;
      
    }
    
    private static void registerMappers(Configuration config) throws MappingException
    {
        config.addResource("inmobiliaria/entities/Persona.hbm.xml");
        config.addResource("inmobiliaria/entities/Inmueble.hbm.xml");
        config.addResource("inmobiliaria/entities/Alquileres.hbm.xml");
        config.addResource("inmobiliaria/entities/Provincias.hbm.xml");
        config.addResource("inmobiliaria/entities/Localidades.hbm.xml");
        config.addResource("inmobiliaria/entities/CuotasInquilino.hbm.xml");
        config.addResource("inmobiliaria/entities/CuotasInquilinosPersonaInmuebleDueno.hbm.xml");
        config.addResource("inmobiliaria/entities/CuotasPagasInquilinosPersonaInmuebleDueno.hbm.xml");
        config.addResource("inmobiliaria/entities/Pagos.hbm.xml");
        config.addResource("inmobiliaria/entities/AlquileresInmueblePersona.hbm.xml");
    }
    
}
