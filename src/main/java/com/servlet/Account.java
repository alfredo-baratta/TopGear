package com.servlet;
import Model.DriverManagerConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/Account")
public class Account extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verifica che l'utente sia autenticato
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // L'utente non è autenticato, reindirizza alla pagina di login o a un'altra pagina di errore
            session.invalidate();
            response.sendRedirect("login.jsp");
            return;
        }

        // Recupera la primary key dell'account dalla sessione
        String username = (String) session.getAttribute("username");

        // Recupera altri dati dal database tramite la primary key
        String email = "";
        String password = "";
        String nome = "";
        String cognome = "";
        String via = "";
        String citta = "";
        Date data = new Date();
        String datanascita = "";
        String telefono = "";
        String cap = "";
        String cf = "";

        try (Connection conn = DriverManagerConnectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM utenti WHERE username = ?")) {

            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("email");
                    password = rs.getString("password");
                    nome = rs.getString("nome");
                    cognome = rs.getString("cognome");
                    via = rs.getString("via");
                    citta = rs.getString("citta");
                    data = rs.getDate("datanascita");
                    datanascita = data.toString();
                    telefono = rs.getString("telefono");
                    cap = rs.getString("cap");
                    cf = rs.getString("cf");
                }
            }
            DriverManagerConnectionPool.releaseConnection(conn);
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // Imposta i dati dell'account come attributi della richiesta
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("nome", nome);
        request.setAttribute("cognome", cognome);
        request.setAttribute("via", via);
        request.setAttribute("citta", citta);
        request.setAttribute("datanascita", datanascita);
        request.setAttribute("telefono", telefono);
        request.setAttribute("cap", cap);
        request.setAttribute("cf", cf);

        // Reindirizza alla JSP per la modifica dei dati dell'account
        request.getRequestDispatcher("/modificaAccount.jsp").forward(request, response);
    }
}
