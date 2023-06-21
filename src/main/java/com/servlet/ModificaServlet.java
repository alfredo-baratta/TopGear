package com.servlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;


@WebServlet("/modifica")
public class ModificaServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/topgear")
	DataSource dataSource;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idAccessorio = request.getParameter("id");
        
        if(idAccessorio == null || idAccessorio.isEmpty()) {
        	response.sendRedirect("/TopGear/catalogo");
        	return;
        }

        Connection conn = null;

        try {
        	conn = dataSource.getConnection();
        	
        	int id = Integer.parseInt(idAccessorio);
            
            String query = "SELECT * FROM accessori WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String descrizione = rs.getString("descrizione");
                float prezzo = (float) rs.getDouble("prezzo");
                float iva = (float) rs.getDouble("iva");
                int disponibilita = rs.getInt("disponibilita");
                int visibilita = rs.getInt("visibilita");
                
                String q2 = "SELECT id FROM immagini_accessorio WHERE fk_accessorio = ?";
                PreparedStatement stmt2 = conn.prepareStatement(q2);
                stmt2.setInt(1, id);
                ResultSet rs2 = stmt2.executeQuery();
                
                List<String> immagini = new ArrayList<String>();
                
                while(rs2.next()) {
                	int idImmagine = rs2.getInt("id");
                	immagini.add(String.valueOf(idImmagine));
                }
               
                request.setAttribute("id", id);
                request.setAttribute("nome", nome);
                request.setAttribute("descrizione", descrizione);
                request.setAttribute("prezzo", prezzo);
                request.setAttribute("iva", iva);
                request.setAttribute("disponibilita", disponibilita);
                request.setAttribute("visibilita", visibilita);
                request.setAttribute("immagini", immagini);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/modificaprodotto.jsp");
                dispatcher.forward(request, response);
            } else {
            	response.sendRedirect("/TopGear/catalogoadmin");
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