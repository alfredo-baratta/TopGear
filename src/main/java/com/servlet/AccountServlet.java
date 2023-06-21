import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditAccountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verifica che l'utente sia autenticato
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // L'utente non è autenticato, reindirizza alla pagina di login o a un'altra pagina di errore
            response.sendRedirect("login.jsp");
            return;
        }

        // Recupera i dati dell'account dalla sessione
        String username = (String) session.getAttribute("username");
        // recuperare altri dati dal database tramite l'username

        // Imposta i dati dell'account come attributi della richiesta
        request.setAttribute("username", username);
        request.setAttribute("codicefiscale", cod);
        request.setAttribute("email", email);
        request.setAttribute("password", pwd);
        request.setAttribute("nome", nome);
        request.setAttribute("cognome", cognome);
        request.setAttribute("indirizzo", indirizzo);
        request.setAttribute("citta", citta);
        request.setAttribute("datanascita", datanascita);
        request.setAttribute("cellulare", cellulare);
        request.setAttribute("cap", cap);
        

        // Reindirizza alla JSP per la modifica dei dati dell'account
        request.getRequestDispatcher("/modificAccount.jsp").forward(request, response);
    }
}
