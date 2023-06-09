package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

import Model.DriverManagerConnectionPool;
import entities.*;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrdersServlet() {
        super();    
    }
    
	@Resource(name="jdbc/topgear")
	DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
	    if (session == null ||  session.getAttribute("username") == null) {
	        session.invalidate();
	    	response.sendRedirect("login.jsp");
	    }
	    else {
	    	
	    	
	        int pageSize = 5; // Numero di ordini per pagina
	        int currentPage = 1; // Pagina corrente (predefinita: 1)
	        int totalOrders = 0;

	        String pageParam = request.getParameter("page");
	        if (pageParam != null) {
	            currentPage = Integer.parseInt(pageParam);
	        }

	        // Calcola l'indice iniziale dell'ordine in base alla pagina corrente
	        int startIndex = (currentPage - 1) * pageSize;
	        
	        String username = (String) session.getAttribute("username"); 
	        String cf = "";
	        try (Connection conn = DriverManagerConnectionPool.getConnection();
	                PreparedStatement statement = conn.prepareStatement("SELECT * FROM utenti WHERE cf = ?")) {

	               statement.setString(1, username);
	               try (ResultSet rs = statement.executeQuery()) {
	                   if (rs.next()) {
	                       cf = rs.getString("cf");
	                   }
	               }
	               DriverManagerConnectionPool.releaseConnection(conn);
	           } catch (Exception e) {
	               System.out.println("Errore: " + e.getMessage());
	           }
	    	
	    	Connection conn = null;

	    	try {
	        	conn = dataSource.getConnection();
	        	
	        	//calcolo il numero totale di ordini:
	        	String queryOrdiniTotali = "SELECT * FROM ordini WHERE fk_utente = ?";
	            PreparedStatement stmt_ordini_totali = conn.prepareStatement(queryOrdiniTotali);
	            stmt_ordini_totali.setString(1, cf);
	            ResultSet rs_ordini_totali = stmt_ordini_totali.executeQuery();
	            
	            while (rs_ordini_totali.next()) {
	                totalOrders++;
	            }
	        	
	            // Aggiungi la logica per ottenere solo una pagina di ordini dal database
	            String query = "SELECT * FROM ordini WHERE fk_utente = ? LIMIT ?, ?";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, cf);
	            stmt.setInt(2, startIndex);
	            stmt.setInt(3, pageSize);
	            ResultSet rs = stmt.executeQuery();
	            
	            List<Ordine> ordini = new ArrayList<Ordine>();

	            while (rs.next()) {
	            	int id = rs.getInt("id");
	            	float totale = rs.getFloat("totale");
	            	Date data_pagamento = rs.getDate("data_pagamento");
	                
	                ordini.add(new Ordine(id, totale, data_pagamento, cf, true));
	            }
	            
	            request.setAttribute("ordini", ordini);
	            request.setAttribute("currentPage", currentPage);
	            request.setAttribute("pageSize", pageSize);
	            request.setAttribute("totalOrders", totalOrders);
	            
	            
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/orders.jsp");
	            dispatcher.forward(request, response);
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
