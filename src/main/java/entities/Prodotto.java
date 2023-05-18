package entities;

public class Prodotto extends Item{
	//rappresenta l'Item all'interno del carrello (ci sta anche il valore qta)
	
	private int qta = 1;	//di base 1
	
	//costruttori
	
	public Prodotto() {
		super();
	}
	
	//					id		nome	  desc	    prezzo     isDisp      disp      iva
	public Prodotto(String i1, String n, String d, float p, boolean isd, int disp, int iva){
		super(i1, n, d, p, isd, disp, iva);
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
	
	public String toString() {
		String result ="";
		result = super.toString() + "/" + this.qta;
		return result;
	}
	
}
