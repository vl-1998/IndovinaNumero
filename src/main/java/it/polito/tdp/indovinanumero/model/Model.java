package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	/*sicuramente il controller che deve mettere in relazione la vista con l'interfaccia grafica conterrà
	un riferimento al nostro modello*/
	
	//Il modello deve contenere la logica dell'applicazione
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	//private boolean inGioco = false; più corretto avere l'inizializzazione nel costruttore
	private boolean inGioco;
	
	//DEVO SCEGLIERE UN CONTENITORE PER TUTTI I TENTATIVI, per verificare che non venga inserito di nuovo lo stesso numero
	private Set<Integer> tentativi;
	
	//bisogna pensare anche al costruttore e ci mettiamo tutte le operazioni che vogliamo fare quando eseguiamo la NEW del modello
	public Model() {
		this.inGioco=false;
		this.tentativiFatti=0;
		this.tentativi= new HashSet<Integer>(); //permette facilmente di cercare in questo contenitore
		
	}
	
	/*ragioniamo sulle azioni che l'utente può fare nel gioco:
	 * - INIZIARE UNA NUOVA PARTITA;
	 * - FARE UN NUOVO TENTATIVO.
	 */
	
	//METODO PER NUOVA PARTITA
	//pubblico perche deve essere richiamato dall'esterno e void perche non deve ritornare niente
	public void nuovaPartita (){
		//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
	}
	
	//FARE NUOVO TENTATIVO
	//il controller dovra sapere dal modello l'esito di questo tentativo
	//la piu semplice e pensare un valore di ritorno intero, se il tentativo e corretto e l'utente ha indovinato il 
	//numero la funzione restituisca zero, se troppo alto 1, se troppo basso -1
	public int tentativo(int tentativo) {
		//controllo se la partita e in corso
		
		//l'utente ha fatto un tentativo, ma la variabile in gioco ha valore falso
		if (!inGioco) {
			throw new IllegalStateException("La partita e' gia' terminata\n"); //siamo in uno stato non legale del sistema 
		}
		
		//controllare l'input, il tentativo dell'utente e' un numero valido? Ha inserito piu volte lo stesso numero
		if (!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra 1 e"+NMAX+"\n");
		}
		
		//ciao
		
		//il tentativo e valido ->possiamo "provarlo"
    	this.tentativiFatti ++; //incremento i tentativi
    	
    	//controllo che impedisce all'utente di inserire lo stesso numero
    	
    	//aggiungo al contenitore il tentatvio fatto
    	this.tentativi.add(tentativo);
    	
    	//CASO TENTATIVI ESAURITI
    	if (this.tentativiFatti==TMAX) {
    		this.inGioco=false;
    	}
    	
    	//HO INDOVINATO!!
    	if (tentativo == this.segreto) {
    		this.inGioco=false; //il gioco e finito
    		return 0; //comunico all'esterno che ho vinto
    	}
    	
    	//IL TENTATIVO E' MINORE o MAGGIORE
    	if (tentativo < this.segreto) {
    		return -1;
    	}else {
    		return 1;
    	}

	}
	
	//Creo un metodo che mi controlli se il tentativo che ho effettuato è valido e lo richiamo poi dentro l'if nel 
	//metodo tentativo per non avere un if lunghissimo in quel controllo
	//Definisco il metodo come privato, perche serve solo a me programmatore, non interessa la logica del gioco
	private boolean tentativoValido (int tentativo){
		if(tentativo<1 || tentativo>NMAX) {
			return false;
		}else {
			if (this.tentativi.contains(tentativo)) { //controllo che i miei tentativi non contengano gia il tentativo
				return false;
			}
			return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
	

}
