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
	private String img;
	
	//costruttore
	public Item(String i1, String n, String d, float p, String i2, boolean isd, int disp) {
		this.id = i1;
		this.nome = n;
		this.descrizione = d;
		this.prezzo = p;
		this.isDisponibile = isd;
		this.img = i2;
		this.disponibilita = disp;
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
	
	public void setImg(String val) {
		this.img = val;
	}
	public String getImg() {
		return this.img;
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
}
