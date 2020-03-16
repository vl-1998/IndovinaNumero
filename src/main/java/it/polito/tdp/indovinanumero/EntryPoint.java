package it.polito.tdp.indovinanumero;

import javafx.application.Application;
import static javafx.application.Application.launch;

import it.polito.tdp.indovinanumero.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	//bisogna modificare il codice che l'archetipo ci da, ma e sempre lo stesso il metodo per modificarlo
    	
    	//1) Creo il modello
    	Model model = new Model ();
    	FXMLController controller;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml")); //l'FXML Loader va a recuperare il controller associato al file FXML
    	
    	
    	//2) creo la scena prima di ottenere il controller
    	
    	//la radice della nostra interfaccia grafica ora possiamo ottenerla direttamente dal loader
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Parent root= loader.load();
    	
        //creiamo la scena a partire dalla radice
        Scene scene = new Scene(root);
    	
    	
    	//3) recupero un riferimento al controller
    	controller = loader.getController();

    	//4) sul controller bisogna fare il set model del modello appena creato
    	controller.setModel(model);
    	 
        //scene.getStylesheets().add("/styles/Styles.css"); Non ci serve possiamo eliminarlo
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
