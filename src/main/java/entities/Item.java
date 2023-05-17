package entities;

public class Item {
	//rappresenta un elemento del database (non nel carrello).
	//Per l'elemento nel carrello, vedi la classe Prodotto
	
	private String id;
	private String nome;
	private String descrizione;
	private float prezzo;
	private boolean isDisponibile;
	private int disponibilita;
	private int iva;
	
	//costruttore
	public Item(String i1, String n, String d, float p, boolean isd, int disp, int iva) {
		this.id = i1;
		this.nome = n;
		this.descrizione = d;
		this.prezzo = p;
		this.isDisponibile = isd;
		this.disponibilita = disp;
		this.iva = iva;
	}
	
	public Item() {
		this.id = null;
	}
	
	//metodi
	public void setId(String val) {
		this.id = val;
	}
	public String getId() {
		return this.id;
	}
	
	public void setNome(String val) {
		this.nome = val;
	}
	public String getNome() {
		return this.nome;
	}
	
	public void setDescrizione(String val) {
		this.descrizione = val;
	}
	public String getDescrizione() {
		return this.descrizione;
	}
	
	public void setPrezzo(float val) {
		this.prezzo = val;
	}
	public float getPrezzo() {
		return this.prezzo;
	}
	
	public void setDisponibile(boolean val) {
		this.isDisponibile = val;
	}
	public boolean getDisponibile() {
		return this.isDisponibile;
	}
	
	public void setDisponibilita(int val) {
		this.disponibilita = val;
	}
	public int getDisponibilita() {
		return this.disponibilita;
	}
	
	public void setIva(int val) {
		this.iva = val;
	}
	public int getIva() {
		return this.iva;
	}
	
	public String toString() {
		String result = "";
		result = this.id + "/" + this.nome + "/" + this.descrizione + "/" + this.prezzo + "/" + this.disponibilita + "/" + this.getIva();
		return result;
	}
}
