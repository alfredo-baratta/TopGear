package entities;

public class Prodotto extends Item{
	//rappresenta l'Item all'interno del carrello
	
	private int qta = 1;	//di base 1
	
	//costruttori
	
	public Prodotto() {
		super();
	}
	
	public Prodotto(String i1, String n, String d, float p, String i2, boolean isd, int disp) {
		super(i1, n, d, p, i2, isd, disp);
		//this.qta = 1;
	}
	
	//prob inutilizzato ma idc
	public Prodotto(String i1, String n, String d, float p, String i2, boolean isd, int disp, int qta) {
		super(i1, n, d, p, i2, isd, disp);
		this.qta = qta;
	}
	
	//metodi
	
	public void setQta(int val) {
		if(val > 0) {
			this.qta = val;
		}
	}
	public int getQta() {
		return this.qta;
	}
	
	public void increaseQta() {
		
		if((this.qta + 1) <= this.getDisponibilita()) {
			this.qta = this.qta +1;
			this.setDisponibilita(this.getDisponibilita() - 1);
		}
	}
	
	public void decreaseQta() {
		if(qta >1) {
			this.qta = this.qta -1;
			this.setDisponibilita(this.getDisponibilita() + 1);
		}
	}
	
}
