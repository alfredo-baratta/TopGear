package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import Model.DriverManagerConnectionPool;

/**
 * Servlet implementation class salvaModifiche
 */
@WebServlet("/salvaModifiche")
public class salvaModifiche extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public salvaModifiche() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// prendo i valori dalla jsp
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String via = request.getParameter("via");
		String citta = request.getParameter("citta");
		//parametro date viene passato come stringa quindi lo passiamo ad un tipo date compatibile con sql
		String datanascitaParam = request.getParameter("datanascita");
		LocalDate datanascita = LocalDate.parse(datanascitaParam, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		String telefono = request.getParameter("telefono");
		String cap = request.getParameter("cap");
		String cf= request.getParameter("cf");
		
		// connessione al database
		try (Connection conn = DriverManagerConnectionPool.getConnection();
			     PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM utenti WHERE cf = ?");
			     PreparedStatement updateStatement = conn.prepareStatement("UPDATE utenti SET nome=?, cognome=?, datanascita=?, telefono=?, citta=?, via=?, cap=? WHERE cf=?")) {

			    // Esecuzione della query di selezione per ottenere i parametri attuali dell'utente
			    selectStatement.setString(1, cf);
			    ResultSet resultSet = selectStatement.executeQuery();

			    // Salvataggio dei parametri attuali in variabili
			    if (resultSet.next()) {
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
			        String pre_nome = resultSet.getString("nome");
			        String pre_cognome = resultSet.getString("cognome");
			        LocalDate pre_datanascita = LocalDate.parse(resultSet.getDate("datanascita").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			        String pre_telefono = resultSet.getString("telefono");
			        String pre_citta = resultSet.getString("citta");
			        String pre_via = resultSet.getString("via");
			        String pre_cap = resultSet.getString("cap");
			        String pre_cf = resultSet.getString("cf");

			      Date date1 = sdf.parse("2020-07-20");
			      Date date2 = sdf.parse("2020-06-18");

			        // Verifica delle modifiche
			        if (!nome.equals(pre_nome) ||
			                !cognome.equals(pre_cognome) ||
			                datanascita.compareTo(pre_datanascita) != 0 ||
			                !telefono.equals(pre_telefono) ||
			                !citta.equals(pre_citta) ||
			                !via.equals(pre_via) ||
			                !cap.equals(pre_cap) ||
			                !cf.equals(pre_cf)) {

			            // Impostazione dei parametri nella query di aggiornamento
			            updateStatement.setString(1, nome);
			            updateStatement.setString(2, cognome);
			            updateStatement.setDate(3, java.sql.Date.valueOf(datanascita));
			            updateStatement.setString(4, telefono);
			            updateStatement.setString(5, citta);
			            updateStatement.setString(6, via);
			            updateStatement.setString(7, cap);
			            updateStatement.setString(8, cf);
			        
			            // esegue l'aggiornamento e rimanda alla pagina di conferma
			            updateStatement.executeUpdate();
			            response.sendRedirect("conferma");

			            //altrimenti se sono uguali, quindi non ci sono state modifiche rimanda ad errore
			        } else {
			            response.sendRedirect("errore");
			        }
			        	//se la prima query non ha trovato risultati rimanda alla pagina di errore 
			    } else {response.sendRedirect("errore");}

			    DriverManagerConnectionPool.releaseConnection(conn);
			} catch (Exception e) {
			    System.out.println("Errore: " + e.getMessage());
			}
	}
}
