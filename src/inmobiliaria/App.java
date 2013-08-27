package inmobiliaria;

import inmobiliaria.model.AlquileresInmueblePersonaView;
import inmobiliaria.model.AlquileresView;
import inmobiliaria.model.Inmueble;
import inmobiliaria.model.Persona;

import java.awt.Toolkit;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
	public static final String messages_ok="messages_ok.fxml";
	public static final String messages_error="messages_error.fxml";
	public static final String buscarInmueble="buscarInmueble.fxml";
	public static final String buscarPersona="buscarPersona.fxml";

	private List<String> pageAnterior= new ArrayList<String> () ;
	//Son los titulos que aparece en la parte superior
	private String titulo;
	private Stage stage;
	private int tabSeleccionado=0;
	
	private static double width;
	private static double height;

	private Persona persona;
    private Inmueble inmueble;
    private AlquileresView alquilerViewHome;
    private AlquileresInmueblePersonaView alquileresInmueblePersonaView ;

	private static App instance;
	
//    ImageIcon icono,fondo;
//    TrayIcon trayicon;
    
	public int getTabSeleccionado() {
		return tabSeleccionado;
	}
	public void setWidth(double val){
		App.width=val;
	}
	public void setHeight(double val){
		App.height=val;
		
	}
	public void setTabSeleccionado(int tabSeleccionado) {
		this.tabSeleccionado = tabSeleccionado;
	}

    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public static void main(String[] args) {
    	App.width= Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    	App.height= Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 70;
        launch(args);
    }
    /**
     * Sets the java library path to the specified path
     *
     * @param path the new library path
     * @throws Exception
     */
    public static void setLibraryPath(String path) throws Exception {
        System.setProperty("java.library.path", path);
     
        //set sys_paths to null
        final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
        sysPathsField.setAccessible(true);
        sysPathsField.set(null, null);
    }
    
    public void start(Stage primaryStage) {
    	try {
    		//Para iniciar con la version de 32
    		//App.setLibraryPath(".//dlls_32_1.7");
    		
    		//Para iniciar con la version de 64
    		//App.setLibraryPath(".//dlls_64_1.7");
    		
    		//Para compilar produccion
			App.setLibraryPath(".//dlls");
			
			//Otro ejemplo
			//App.setLibraryPath("C:\\Users\\Nico\\GestionInmobiliaria2\\prog\\dlls");
		} catch (Exception e) {
			e.printStackTrace();
		}
//    	   icono=new ImageIcon(getClass().getResource("../img/logo.png"));
//    	   fondo=new ImageIcon(getClass().getResource("../img/logo.png"));
//    	   trayicon=new TrayIcon(icono.getImage(),"Java Zone",null);
//    	   try {
//    		    SystemTray.getSystemTray().add(trayicon);
//    		   } catch (AWTException e1) {
//    		    // TODO Auto-generated catch block
//    		    e1.printStackTrace();
//    		   }
        try {
            stage = primaryStage;
            stage.initStyle(StageStyle.UNDECORATED);
	        replaceSceneContent("home.fxml");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pageAnterior (){
        try {
			this.replaceSceneContent(pageAnterior.get(pageAnterior.size()-2));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
        
    public Parent replaceSceneContent(String fxml) throws Exception {
    	
    	pageAnterior.add(fxml);
    	
    	titulo =  (fxml.split(".fxml")[0]).toUpperCase();
    	ResourceBundle bundle = ResourceBundle.getBundle("boundle");

    	URL url = App.class.getResource("/" + fxml);

    	Logger.getLogger(App.class.getName()).log(Level.INFO, "Direcciona a la pagina " + url+ " Ubicación: " + App.class.getPackage());
    	

        Parent page = (Parent) FXMLLoader.load(url, bundle, new JavaFXBuilderFactory(),null, Charset.forName("UTF-8"));
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, App.width, App.height);
            scene.getStylesheets().add(App.class.getResource("/styles/StyleSheet.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        //stage.sizeToScene();
        return page;
    }
	public Scene getScene(){
    	return stage.getScene();
    }
    public Stage getStage(){
    	return stage;
    }
    public void clear(){
    	persona=null;
    	inmueble=null;
    }
    // Getters y Stters
	public String getTitulo() {
    	return titulo;
    }
    public void setTitulo(String titulo) {
    	this.titulo = titulo;
    }
    public AlquileresView getAlquilerViewHome() {
		return alquilerViewHome;
	}
	public void setAlquilerViewHome(AlquileresView alquilerViewHome) {
		this.alquilerViewHome = alquilerViewHome;
	}
	public Inmueble getInmueble() {
		return inmueble;
	}
	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}
    public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public AlquileresInmueblePersonaView getAlquileresInmueblePersonaView() {
		return alquileresInmueblePersonaView;
	}

	public void setAlquileresInmueblePersonaView(
			AlquileresInmueblePersonaView alquileresInmueblePersonaView) {
		this.alquileresInmueblePersonaView = alquileresInmueblePersonaView;
	}
}
