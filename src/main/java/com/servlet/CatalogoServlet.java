package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import entities.*;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CatalogoServlet() {
        super();
    }

	@Resource(name="jdbc/topgear")
	DataSource dataSource;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection conn = null;
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
                String immagineBase64 = null;
                
                String q2 = "SELECT immagine FROM immagini_accessorio WHERE id = ? LIMIT 1";
                PreparedStatement stmt2 = conn.prepareStatement(q2);
                stmt2.setInt(1, id);
                ResultSet rs2 = stmt2.executeQuery();
                
                if(rs2.next()) {
                	byte[] immagine = rs2.getBytes("immagine");
                	immagineBase64 = Base64.getEncoder().encodeToString(immagine);
                }
                
                accessori.add(new Accessorio(id, nome, descrizione, prezzo, disponibilita, immagineBase64));
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
