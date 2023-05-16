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


@WebServlet("/servletDettaglio")
public class ProdottoServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/topgear")
	DataSource dataSource;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //int IdAccessorio = Integer.parseInt(request.getParameter("IdAccessorio"));
        int idAccessorio = 1;

        try {
        	Connection conn = dataSource.getConnection();
            
            String query = "SELECT * FROM accessorio WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, idAccessorio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String descrizione = rs.getString("descrizione");
                float prezzo = (float) rs.getDouble("prezzo");
                int disponibilita = rs.getInt("disponibilita");
                
                String q2 = "SELECT link_immagine FROM immagine WHERE idAccessorio = ?";
                PreparedStatement stmt2 = conn.prepareStatement(q2);
                stmt2.setInt(1, idAccessorio);
                ResultSet rs2 = stmt2.executeQuery();
                
                List<String> link_immagini = new ArrayList<String>();
                
                while(rs2.next()) {
                    String linkImmagine = rs2.getString("link_immagine");
                    link_immagini.add(linkImmagine);
                }
               
                request.setAttribute("nome", nome);
                request.setAttribute("descrizione", descrizione);
                request.setAttribute("prezzo", prezzo);
                request.setAttribute("disponibilita", disponibilita);
                request.setAttribute("immagini", link_immagini);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/prodotto.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/emulatoreCatalogo.html");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}