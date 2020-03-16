package it.polito.tdp.indovinanumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.indovinanumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	//la pura dichiarazione della variabile non riempie il modello
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    @FXML
    void doNuova(ActionEvent event) {
    	
    	/*Questa parte e logica del gioco, non gestiste l'interfaccia grafica, quindi deve stare nel modello
    	//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
    	*/
    	
    	//COMUNICO al controller che sta iniziando una nuova partit
    	this.model.nuovaPartita();
    	
    	//QUI TRATTANDOSI DI GESTIONE DI INTERFACCIA DEVE RIMANERE
    	//gestione dell'interfaccia
    	layoutTentativo.setDisable(false);
    	txtRisultato.clear();
    	txtRimasti.setText(Integer.toString(this.model.getTMAX()));

    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//PRIMO controllo sull'input che può rimanere qua
    	
    	//leggere l'input dell'utente
    	String ts = txtTentativi.getText();
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	} catch (NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero!\n");
    		return;
    	}
    	
    	int risultato=-1;
    	try {
    		risultato = this.model.tentativo(tentativo); //questo e il tentativo dell'utente, lo passo al modello ed estraggo il risultato
    	} catch (IllegalStateException se) {
    		txtRisultato.appendText(se.getMessage());
    		return;
    	} catch (InvalidParameterException pe) {
    		txtRisultato.appendText(pe.getMessage());
    		return;
    	}
    	
    	//per prendere i tentativi dal modello devo predisporre i metodi GETTER nel modello	
    	if (risultato==0) {
    		txtRisultato.appendText("HAI VINTO!! Hai vinto con "+ model.getTentativiFatti() + "tentativi.");
    	} else if (risultato == -1) {
    		txtRisultato.appendText("TENTATIVO TROPPO BASSO\n");
    	} else {
    		txtRisultato.appendText("TENTATIVO TROPPO ALTO\n");
    	}
    	
    	txtRimasti.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiFatti()));
    	//Controllo delle eccezioni
    }

    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";

        //this.model=new Model(); //cosi abbiamo una forte relazione tra questo controllore e il modello che abbiamo creato, il controllore userà proprio il modello caratterizzato dalla classe Model
        //la forza del pattern e pero quella di tenerli fortemente separati, per cui io potrei cambiare il modello senza cambiare il mio controller
    }
    
    // il modello andra creato dall'esterno e poi assegnato al controller, per farlo posso prevedere un metodo
    
    //riceve un modello dall'esterno gia impostato, per cui il modello può cambiare senza che debba cmbiare anche il Controller
    public void setModel(Model model) {
    	this.model=model;
    }
}
