package inmobiliaria;

import inmobiliaria.model.AlquileresInmueblePersonaView;
import inmobiliaria.model.AlquileresView;
import inmobiliaria.model.Inmueble;
import inmobiliaria.model.Persona;

import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.security.CodeSource;
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
        launch(args);
    }

    public void start(Stage primaryStage) {
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

        Parent page = (Parent) FXMLLoader.load(url, bundle, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 70);
            scene.getStylesheets().add(App.class.getResource("/styles/StyleSheet.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        
        
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
