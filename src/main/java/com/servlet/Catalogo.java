package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import entities.Accessorio;

@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/topgear")
	DataSource dataSource;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
        	Connection conn = dataSource.getConnection();
            
            String query = "SELECT * FROM accessorio WHERE visibilita = true";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            List<Accessorio> accessori = new ArrayList<Accessorio>();

            while (rs.next()) {
            	int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descrizione = rs.getString("descrizione");
                float prezzo = (float) rs.getDouble("prezzo");
                int disponibilita = rs.getInt("disponibilita");
                String immagine = null;
                
                String q2 = "SELECT link_immagine FROM immagine WHERE idAccessorio = ? LIMIT 1";
                PreparedStatement stmt2 = conn.prepareStatement(q2);
                stmt2.setInt(1, id);
                ResultSet rs2 = stmt2.executeQuery();
                
                if(rs2.next()) {
                    immagine = rs2.getString("link_immagine");
                }
                
                accessori.add(new Accessorio(id, nome, descrizione, prezzo, disponibilita, immagine));
            }
            
            request.setAttribute("accessori", accessori);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/catalogo.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doGet(request, response);
    }

}
