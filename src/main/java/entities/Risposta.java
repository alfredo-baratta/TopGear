package entities;

public class Risposta {
	private boolean esito; //true=OK, false=ERRORE
	private String message;
	
	public Risposta(){	
	}
	
	public void setEsito(boolean esito) {
		this.esito = esito;
	}
	public boolean getEsito() {
		return esito;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
