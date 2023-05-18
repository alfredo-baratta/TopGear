package entities;
import java.util.ArrayList;
import java.util.Hashtable;

public class Carrello {
	//rappresenta il carrello dell'utente
	
	private int num_prodotti;		//count di prodotti
	private float totale_prodotti;	//totale PREZZO prodotti
	protected Hashtable<String, Prodotto> lista_prodotti = new Hashtable<String, Prodotto>();		//utilizzata per memorizzare gli elementi
	
	//costruttori
	public Carrello() {
		this.num_prodotti = 0;
		this.totale_prodotti = (float)0.0;	//??
	}
	
	//metodi
	public void setNumProdotti(int val) {
		this.num_prodotti = val;
	}
	
	public int getNumProdotti() {
		return this.num_prodotti; 
	}
	
	public void increaseNumProdotti() {
		this.num_prodotti++;
	}
	
	public void setTotaleProdotti(float val) {
		this.totale_prodotti = val;
	}
	
	public float getTotaleProdotti() {
		return this.totale_prodotti;
	}
	
	public Hashtable<String, Prodotto> getListaProdotti() {
		return this.lista_prodotti;
	}
	
	//questo metodo converte e restituisce la hashtable come arraylist
	public ArrayList<Prodotto> getListaProdottiAsArrayList() {
		ArrayList<Prodotto> lista = new ArrayList<Prodotto>(lista_prodotti.values());
		return lista;
	}
	
	//questo metodo inserisce un nuovo prodotto nel carrello
	public void insert(Prodotto p) {
		
		increaseNumProdotti();
		p.setDisponibilita(p.getDisponibilita() - 1);
		String id = p.getId();
		
		//se nella Hashtable non c'è l'ID del prodotto vuol dire che non è stato già aggiunto
		if(!lista_prodotti.containsKey(id)) {
			lista_prodotti.put(id, p);
		}
		else {
			
			//altrimenti mi basta incrementare il contatore qta specifico di quel prodotto
			Prodotto temp = lista_prodotti.get(id);
			temp.increaseQta();
			lista_prodotti.put(id, temp);
		}
		
		//a prescindere, incrementa la spesa totale del carrello
		float temp = p.getPrezzo();
		totale_prodotti = totale_prodotti + temp;
	}

	//rimuovo un prodotto dato l'id
	public void remove(String id){
		//System.out.println("Rimuovo questo prodotto: " +id);
		if(!lista_prodotti.isEmpty()) {
			if(lista_prodotti.containsKey(id)) {
			
				Prodotto p = lista_prodotti.get(id);
				float temp = p.getPrezzo();
				int qta = p.getQta();
				p.setDisponibilita(p.getDisponibilita() + qta);
				num_prodotti = num_prodotti - qta;
				lista_prodotti.remove(id);
				totale_prodotti = totale_prodotti - (temp * qta);
				//System.out.println("Ho rimosso questo prodotto: " +id);
			}
		}
	}

	public void remove(Prodotto p) {
		String id = p.getId();
		remove(id);
	}
	
	//Questo metodo riduce la qta di 1
	public void decreaseQta(String id){
		
		if(lista_prodotti.containsKey(id)) {
			Prodotto p = lista_prodotti.get(id);
			lista_prodotti.remove(id);
			p.decreaseQta();
			totale_prodotti = totale_prodotti - p.getPrezzo();
			num_prodotti--;
			lista_prodotti.put(id, p);
		}
	}
	
	//Questo metodo incrementa la qta di 1
	public void increaseQta(String id){
		
		if(lista_prodotti.containsKey(id)) {
			Prodotto p = lista_prodotti.get(id);
			lista_prodotti.remove(id);
			p.increaseQta();
			totale_prodotti = totale_prodotti + p.getPrezzo();
			num_prodotti++;
			lista_prodotti.put(id, p);
		}
	}
	
	//Questo metodo modifica la qta
	public void changeQta(String id, int newqta) {
		if(lista_prodotti.containsKey(id)) {
			Prodotto p = lista_prodotti.get(id);
			
			//se si vuole ridurre la qta
			if(newqta < p.getQta()) {
				int diff = p.getQta() - newqta;
				p.setDisponibilita( p.getDisponibilita() + diff);
				num_prodotti = num_prodotti - diff;
				totale_prodotti = totale_prodotti - (p.getPrezzo() * diff);
			}
			
			//se si vuole aumentare la qta
			else if(newqta > p.getQta()){
				int diff = newqta - p.getQta();
				
				//ovviamente la disponibilità non può assumere valori negativi
				if((p.getDisponibilita() - diff) >= 0) {
					p.setDisponibilita( p.getDisponibilita() - diff);
					num_prodotti = num_prodotti + diff;
					totale_prodotti = totale_prodotti + (p.getPrezzo() * diff);
				}
			}
			else if(newqta == p.getQta()) {
				return;
			}
			
			p.setQta(newqta);
		}
	}
	
	public float calcolaIVA() {
		
		float result = (float)0.0;	//??
		
		for(Prodotto p : this.getListaProdottiAsArrayList()) {
			result = result + (p.getPrezzo() + ((p.getPrezzo() * p.getIva())/100));
		}
		
		//result = this.totale_prodotti + (this.totale_prodotti )/100;
		
		return result;
	}
	
	//questo metodo resetta tutto
	public void resetEverything() {
		for(Prodotto p : this.getListaProdottiAsArrayList()) {
			this.remove(p);
			this.num_prodotti--;
		}
		//lista_prodotti = new Hashtable<String, Prodotto>();
		this.num_prodotti = 0;
		this.totale_prodotti = 0;
		
		/*
		if(lista_prodotti.isEmpty()) {
			System.out.println("Lista svuotata!");
		}
		else {
			System.out.println("Lista non svuotata :(");
		}
		*/
	}
}
