package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import entities.*;

/**
 * Servlet implementation class ListaUtenti
 */
@WebServlet("/ListaUtenti")
public class ListaUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaUtenti() {
        super();
    }
    
	@Resource(name="jdbc/topgear")
	DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection conn = null;
        try {
        	conn = dataSource.getConnection();
            
            String query = "SELECT cf, nome, cognome, email FROM utenti";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            
            List<Utente> utenti = new ArrayList<Utente>();

            while (rs.next()) {
            	Utente utente = new Utente();
            	utente.setCodicefiscale(rs.getString("cf"));
            	utente.setNome(rs.getString("nome"));
            	utente.setCognome(rs.getString("cognome"));
            	utente.setEmail(rs.getString("email"));
            	utenti.add(utente);
            }
            
            request.setAttribute("utenti", utenti);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/utenti.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection conn = null;
        try {
        	conn = dataSource.getConnection();
        	
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            
            String query = "SELECT cf, nome, cognome, email FROM utenti";
            
            if(nome != null && !nome.isEmpty() && (cognome == null || cognome.isEmpty())) {
            	query = "SELECT cf, nome, cognome, email FROM utenti WHERE nome LIKE ?";
            } else if((nome == null || nome.isEmpty()) && cognome != null && !cognome.isEmpty()) {
            	query = "SELECT cf, nome, cognome, email FROM utenti WHERE cognome LIKE ?";
            } else if(nome != null && !nome.isEmpty() && cognome != null && !cognome.isEmpty()) {
            	query = "SELECT cf, nome, cognome, email FROM utenti WHERE nome LIKE ? AND cognome LIKE ?";
            }
            
            PreparedStatement stmt = conn.prepareStatement(query);
            
            if(nome != null && !nome.isEmpty() && (cognome == null || cognome.isEmpty())) {
            	stmt.setString(1, "%" + nome + "%");
            } else if((nome == null || nome.isEmpty()) && cognome != null && !cognome.isEmpty()) {
            	stmt.setString(1, "%" + cognome + "%");
            } else if(nome != null && !nome.isEmpty() && cognome != null && !cognome.isEmpty()) {
            	stmt.setString(1, "%" + nome + "%");
            	stmt.setString(2, "%" + cognome + "%");
            }
            
            ResultSet rs = stmt.executeQuery();
            
            List<Utente> utenti = new ArrayList<Utente>();

            while (rs.next()) {
            	Utente utente = new Utente();
            	utente.setCodicefiscale(rs.getString("cf"));
            	utente.setNome(rs.getString("nome"));
            	utente.setCognome(rs.getString("cognome"));
            	utente.setEmail(rs.getString("email"));
            	utenti.add(utente);
            }
            
            request.setAttribute("utenti", utenti);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/utenti.jsp");
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
