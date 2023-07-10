package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.*;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/dettagliOrdine")
public class DetailsOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/topgear")
	DataSource dataSource;
       
    public DetailsOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String idOrdine = request.getParameter("id");
        
        if(idOrdine == null || idOrdine.isEmpty()) {        	
        	response.sendRedirect("/TopGear/orders");
        	return;
        }
        
        Connection conn = null;

        try {
        	      	
        	conn = dataSource.getConnection();
        	
        	int id = Integer.parseInt(idOrdine);
            
            String query1 = "SELECT * FROM ordini WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query1);
            stmt.setInt(1, id);
            ResultSet rs1 = stmt.executeQuery();

            if (rs1.next()) {
                String totale = rs1.getString("totale");
                String data_pagamento = rs1.getString("data_pagamento");
                
                String query2 = "SELECT id FROM ordini_accessorio WHERE fk_ordine = ?";
                PreparedStatement stmt2 = conn.prepareStatement(query2);
                stmt2.setInt(1, id);
                ResultSet rs2 = stmt2.executeQuery();
                
                ArrayList<OrdineAccessorio> ordini_accessorio = new ArrayList<OrdineAccessorio>();
                
                while(rs2.next()) {
                	
                	int id_ordine_accessorio = rs2.getInt("id");
                	int quantita = rs2.getInt("quantita");
                	int id_accessorio = rs2.getInt("fk_accessorio");
                	
                	String query3 = "SELECT id FROM accessori WHERE id = ?";
                    PreparedStatement stmt3 = conn.prepareStatement(query3);
                    stmt3.setInt(1, id_accessorio);
                    ResultSet rs3 = stmt3.executeQuery();
                    
                    if(rs3.next()) {
                    	
                        String nome = rs3.getString("nome");
                        String descrizione = rs3.getString("descrizione");
                        float prezzo = (float) rs3.getDouble("prezzo");
                        int disponibilita = rs3.getInt("disponibilita");
                        int idImmagine = -1;
                        
                        String query4 = "SELECT id FROM immagini_accessorio WHERE fk_accessorio = ? LIMIT 1";
                        PreparedStatement stmt4 = conn.prepareStatement(query4);
                        stmt4.setInt(1, id_accessorio);
                        ResultSet rs4 = stmt4.executeQuery();
                        
                        if(rs4.next()) {
                        	idImmagine = rs4.getInt("id");
                        }
                        Accessorio accessorio;
                        accessorio = new Accessorio(id_accessorio, nome, descrizione, prezzo, disponibilita, String.valueOf(idImmagine));
                        
                        ordini_accessorio.add(new OrdineAccessorio(id_ordine_accessorio, quantita, accessorio));
                    }
                }
                
	            request.setAttribute("totale", totale);
	            request.setAttribute("data_pagamento", data_pagamento);
	            request.setAttribute("ordini_accessorio", ordini_accessorio);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/dettagliOrdine.jsp");
                dispatcher.forward(request, response);
            } else {
            	response.sendRedirect("/TopGear/orders");
            }
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Errore durante la chiusura della connessione: " + e.getMessage());
                }
            }
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
