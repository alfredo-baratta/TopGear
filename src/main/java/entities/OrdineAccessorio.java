package entities;

public class OrdineAccessorio {
	private int id;
	private int quantita;
	private Accessorio accessorio;
	
	public OrdineAccessorio(){
		
	}
	
	public OrdineAccessorio(int id, int quantita, Accessorio accessorio){
		this.id = id;
		this.quantita = quantita;
		this.accessorio = accessorio;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getQuantita() {
		return quantita;
	}
	
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public Accessorio getAccessorio() {
		return accessorio;
	}
	
	public void setAccessorio(Accessorio accessorio) {
		this.accessorio = accessorio;
	}
}
