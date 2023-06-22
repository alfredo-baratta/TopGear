import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DriverManagerConnectionPool;

public class EditAccountServlet extends HttpServlet {
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
        
        // recuperare altri dati dal database tramite la primary key
        
        try (Connection conn = DriverManagerConnectionPool.getConnection();
        	
            String email = "";
            String password = "";
            String nome = "";
            String cognome = "";
            String via = "";
            String citta = "";
            String datanascita = "";
            String telefono = "";
            String cap = "";	
        	String cf = "";
        		
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
                	datanascita = rs.getString("datanascita");
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
        request.getRequestDispatcher("/modificAccount.jsp").forward(request, response);
    }
}
