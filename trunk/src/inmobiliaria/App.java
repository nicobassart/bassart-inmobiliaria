package inmobiliaria;

import inmobiliaria.model.AlquileresInmueblePersonaView;
import inmobiliaria.model.AlquileresView;
import inmobiliaria.model.Inmueble;
import inmobiliaria.model.Persona;

import java.awt.Toolkit;
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
        try {
            stage = primaryStage;
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
    	Logger.getLogger(App.class.getName()).log(Level.INFO, "Direcciona a la pagina " + fxml );
    	
    	pageAnterior.add(fxml);
    	
    	titulo =  (fxml.split(".fxml")[0]).toUpperCase();
        Parent page = (Parent) FXMLLoader.load(App.class.getResource("../" + fxml), ResourceBundle.getBundle("boundle"), new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 70);
            scene.getStylesheets().add(App.class.getResource("../styles/StyleSheet.css").toExternalForm());
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
