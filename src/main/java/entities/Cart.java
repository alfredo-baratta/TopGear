package entities;

import java.util.*;

public class Cart {
	
	ArrayList<Accessorio>carrello_prodotti;
	
	public Cart(){
		carrello_prodotti = new ArrayList<Accessorio>();
	}
	
	//funzione per inserire un Accessorio nell'ArrayList
	public void inserisciInList(Accessorio accessorio) {
		carrello_prodotti.add(accessorio);
	}
	
	//funzione per inserire i dati in un Accessorio
	public Accessorio inserisciDati(int id, String nome, String descrizione, float prezzo, int disponibilita, String immagine) {
		Accessorio accessorio = new Accessorio(id, nome, descrizione, prezzo, disponibilita, immagine);
		return accessorio;
	}
	
	//funzione per ottenere l'intera ArrayList (y?)
	ArrayList<Accessorio> getList(){
		return carrello_prodotti;
	}
	
	//funzione per ottenere il primo accessorio dell'ArrayList e lo rimuove pure
	public Accessorio getAccessorio() {
		Accessorio first = carrello_prodotti.get(0);
		carrello_prodotti.remove(0);
		//io spero che il tutto poi venga shiftato automaticamente a sx altrimenti :(
		return first;
	}

}
