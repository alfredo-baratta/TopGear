package entities;

import java.sql.*;
import java.util.Date;
import java.util.List;

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
	
	public int salvaOrdine(List<String> cartItems, int totalProducts, float totalPrice, String fkUtente) {
		
		long millis = System.currentTimeMillis();  
        Date date = new Date(millis);
        
		this.setTotale(totalPrice);
		this.dataPagamento = date;
		int fkOrdine = 0;
		
		try (Connection conn = DriverManagerConnectionPool.getConnection()) {
			
			//Inserimento dell'ordine nel DB
	        String insertOrdineQuery = "INSERT INTO ordini (totale, data_pagamento, fk_utente) VALUES (?, ?, ?)";
	        PreparedStatement insertOrdineStmt = conn.prepareStatement(insertOrdineQuery);
	        insertOrdineStmt.setFloat(1, totalPrice);
	        insertOrdineStmt.setDate(2, new java.sql.Date(dataPagamento.getTime()));
	        insertOrdineStmt.setString(3, fkUtente);
	        insertOrdineStmt.executeUpdate();

	        //Recupero dell'ID dell'ordine appena inserito
	        String selectLastInsertIdQuery = "SELECT LAST_INSERT_ID()";
	        PreparedStatement selectLastInsertIdStmt = conn.prepareStatement(selectLastInsertIdQuery);
	        ResultSet resultSet = selectLastInsertIdStmt.executeQuery();
	        if (resultSet.next()) {
	            fkOrdine = resultSet.getInt(1);
	        }
	        
	        //Inserimento degli elementi dell'ordine nel DB
	        String insertOrdiniAccessorioQuery = "INSERT INTO ordini_accessorio (quantita, fk_ordine, fk_accessorio) VALUES (?, ?, ?)";
	        PreparedStatement insertOrdiniAccessorioStmt = conn.prepareStatement(insertOrdiniAccessorioQuery);

	        for (int i = 0; i < totalProducts/5; i++) {
	        	
				int fkAccessorio = 0;
				int quantity = 0;

        		for(int j = 0; j < 5; j++) {
        			
        			int index = (j + 5*i);
        			String couple_temp = cartItems.get(index);
        			
        			String[] couple = couple_temp.split(":");
        			
        			if(couple[0].contentEquals("productId")) {
        				fkAccessorio = Integer.parseInt(couple[1]);
        			}
        			else if(couple[0].contentEquals("quantity")) {
        				quantity = Integer.parseInt(couple[1]);
        			}
        		}
        		
	            insertOrdiniAccessorioStmt.setInt(1, quantity);
	            insertOrdiniAccessorioStmt.setInt(2, fkOrdine);
	            insertOrdiniAccessorioStmt.setInt(3, fkAccessorio);
	            insertOrdiniAccessorioStmt.executeUpdate();
	        }

	        //Chiusura delle risorse
	        insertOrdineStmt.close();
	        selectLastInsertIdStmt.close();
	        insertOrdiniAccessorioStmt.close();
	        resultSet.close();
	        conn.close();
		} 
		catch (SQLException e) {
	        e.printStackTrace();
	    }
        return fkOrdine;
	}
	
	public void salvaFattura(List<String> cartItems,  int totalProducts) {
		
		
		int ordineAccessorioId = 0; //di default
        PreparedStatement stmt = null;
        String ordineAccessorioIdQuery = "";
        
        System.out.println("Elementi: " + totalProducts/5);
        
        //Query per prelevare l'ultimo ID
        try (Connection conn = DriverManagerConnectionPool.getConnection()){
        	ordineAccessorioIdQuery = "SELECT id FROM ordini_accessorio ORDER BY id DESC LIMIT " + totalProducts/5;
        		
        		Statement statement = conn.createStatement();
        	    ResultSet resultSet = statement.executeQuery(ordineAccessorioIdQuery);
        	    int i = 0;
        	    
                while (resultSet.next()) {
                    ordineAccessorioId = resultSet.getInt("id");

            		try {
                        //Query di inserimento
                        String query = "INSERT INTO fattura_accessori (prezzo, iva, fk_accessorio, fk_ordine_accessorio) VALUES (?, ?, ?, ?)";
                        stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        
            	        	
            			float prezzo = 0.0f;
                        int fkAccessorio = 0;

                    	for(int l = 0; l < 5; l++) {
                    			
                    		int index = (l + 5*i);
                    		String couple_temp = cartItems.get(index);
                    			
                			String[] couple = couple_temp.split(":");
                			if(l == 0) {
                				fkAccessorio = Integer.parseInt(couple[1]);
                			}
                			else if (l == 3) {
                				prezzo = Float.parseFloat(couple[1]);
                			}
                    	}
                    		
                        stmt.setFloat(1, prezzo);
                        stmt.setFloat(2, 22);
                        stmt.setInt(3, fkAccessorio);
                        stmt.setInt(4, ordineAccessorioId);
                        stmt.executeUpdate();
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            		
            		i++;
                }
                
        	conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
 
        }

	}
}
