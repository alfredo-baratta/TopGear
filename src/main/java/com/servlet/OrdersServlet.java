package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

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
	    	
	    	//deve mostrare a schermo alcune info sugli ordini presi dal DB, come Data, quantità di oggetti
	    	//e totale speso per quell'ordine
	    	Connection conn = null;
	    	
	    	/*
	    	 * 
	    	 * SISI questo è ancora un WIP copiato da... Pietro? mi pare? yea, poi quando arrivo a fare la pagina 
	    	 * dove mostrare gli ordini lo modifico opportunamente questo codice
	    	try {
	        	conn = dataSource.getConnection();
	            
	            String query = "SELECT * FROM accessori WHERE visibilita = true";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();
	            
	            List<Accessorio> accessori = new ArrayList<Accessorio>();

	            while (rs.next()) {
	            	int id = rs.getInt("id");
	                String nome = rs.getString("nome");
	                String descrizione = rs.getString("descrizione");
	                float prezzo = (float) rs.getDouble("prezzo");
	                int disponibilita = rs.getInt("disponibilita");
	                int idImmagine = -1;
	                
	                String q2 = "SELECT id FROM immagini_accessorio WHERE fk_accessorio = ? LIMIT 1";
	                PreparedStatement stmt2 = conn.prepareStatement(q2);
	                stmt2.setInt(1, id);
	                ResultSet rs2 = stmt2.executeQuery();
	                
	                if(rs2.next()) {
	                	idImmagine = rs2.getInt("id");
	                }
	                
	                accessori.add(new Accessorio(id, nome, descrizione, prezzo, disponibilita, String.valueOf(idImmagine)));
	            }
	            
	            request.setAttribute("accessori", accessori);
	            
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/catalogo.jsp");
	            dispatcher.forward(request, response);
	        } catch (Exception e) {
	            System.out.println("Errore: " + e.getMessage());
	        } finally {
	            if (conn != null) {
	                try {
	                    conn.close();
	                    RequestDispatcher dispatcher = request.getRequestDispatcher("/orders.jsp");
	        			dispatcher.forward(request,  response);
	                } catch (SQLException e) {
	                    System.out.println("Errore durante la chiusura della connessione: " + e.getMessage());
	                }
	            }
	        }
	        */
	    }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
