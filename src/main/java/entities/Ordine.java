package entities;

import java.sql.*;

import Model.DriverManagerConnectionPool;

public class Ordine {
	private int id;
	private float totale;
	private String utente_cf;
	private Date dataPagamento;
	private boolean type = true;	//true per accessorio, false per personalizzazione
	
	public Ordine() {
		
	}
	
	public Ordine(int id, float totale, Date dataPagamento, String utente_cf, boolean type) {
		this.id = id;
		this.totale = totale;
		this.dataPagamento = dataPagamento;
		this.utente_cf = utente_cf;
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
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public String getUtente() {
		return utente_cf;
	}
	
	public void setUtente(String utente_cf) {
		this.utente_cf = utente_cf;
	}
	
	public boolean getType() {
		return type;
	}
	
	public void setType(boolean type) {
		this.type = type;
	}
	
	public Risposta salvaOrdine(String orderData, int totalProducts, float totalPrice) {
		Risposta r = new Risposta();
		
		//Converto i dati in un oggetto Java di tipo Cart che avrà al suo
		//interno una lista di entities.Accessorio basically
		
		long millis = System.currentTimeMillis();  
		
		this.dataPagamento = new Date(millis);
		
		Cart cart;  
		
		try {
			//prendo le info da orderData. how? who knows
			
			//le inserisco in tot accessori, e tali assieme a totalProducts e totalPrice li inserisco in un obj ordine
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		//salvo tutte le informazioni poste nell'obj ordine nel DB
		/*
		try (Connection conn = DriverManagerConnectionPool.getConnection()) {
        	String query = "INSERT INTO ordini (id, totale, data_pagamento, fk_utente) "
        			+ "VALUES (?, ?, ?, ?)";
        	PreparedStatement ps = conn.prepareStatement(query);
        	ps.setInt(1, id);
	        ps.setFloat(2, totalPrice);
	        ps.setDate(3, dataPagamento);
	        ps.setString(4, utente_cf);
            ps.executeUpdate();
            
            setId(id);
            setTotale(totale);
            setDataPagamento(dataPagamento);
            setUtente(utente_cf);
            
            //devo capire come associare l'ordine ad una lista di prodotti. mh.
            
            DriverManagerConnectionPool.releaseConnection(conn);
            r.setEsito(true);
            r.setMessage("OK");
        }catch (SQLException e) {
        	System.out.println(e.getMessage());
        	r.setEsito(false); 
        	r.setMessage("Ops! Qualcosa e' andato storto. Haha POV: sei me qualsiasi momento della vita");
        	//TODO: yeayea smelly code, però devo comunque ricordarmi di modificare questo ^ altrimenti piango
		}
		*/
		
        return r;
	}
}
