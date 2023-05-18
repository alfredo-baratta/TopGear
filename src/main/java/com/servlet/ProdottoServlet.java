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
import java.util.Base64;
import java.util.List;


@WebServlet("/prodotto")
public class ProdottoServlet extends HttpServlet {
    
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
                int disponibilita = rs.getInt("disponibilita");
                
                String q2 = "SELECT immagine FROM immagini_accessorio WHERE fk_accessorio = ?";
                PreparedStatement stmt2 = conn.prepareStatement(q2);
                stmt2.setInt(1, id);
                ResultSet rs2 = stmt2.executeQuery();
                
                List<String> immagini = new ArrayList<String>();
                
                while(rs2.next()) {
                	byte[] immagine = rs2.getBytes("immagine");
                    String immagineBase64 = Base64.getEncoder().encodeToString(immagine);
                    immagini.add(immagineBase64);
                }
               
                request.setAttribute("nome", nome);
                request.setAttribute("descrizione", descrizione);
                request.setAttribute("prezzo", prezzo);
                request.setAttribute("disponibilita", disponibilita);
                request.setAttribute("immagini", immagini);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/prodotto.jsp");
                dispatcher.forward(request, response);
            } else {
            	response.sendRedirect("/TopGear/catalogo");
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