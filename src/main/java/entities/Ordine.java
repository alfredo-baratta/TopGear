package entities;

import java.sql.*;
import Model.DriverManagerConnectionPool;

public class Ordine {
	private int id;
	private float totale;
	private String utente;
	private boolean type = true;	//true per accessorio, false per personalizzazione
	
	public Ordine(int id, float totale, String utente, boolean type) {
		this.id = id;
		this.totale = totale;
		this.utente = utente;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public float getTotale() {
		return totale;
	}
	
	public void setTotale(float totale) {
		this.totale = totale;
	}
	
	public String getUtente() {
		return utente;
	}
	
	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	public boolean getType() {
		return type;
	}
	
	public void setType(boolean type) {
		this.type = type;
	}
	
	//L'idea è prima quello di creare l'ordine eseguendo il costruttore
	//e successivamente si esegue questa funzione per salvare l'ordine nel DB
	public Risposta salvaOrdine() {
		Risposta r = new Risposta();
		
		
		
		return r;
	}
}
