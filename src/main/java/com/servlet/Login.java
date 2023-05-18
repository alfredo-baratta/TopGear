package com.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;
import entities.Utente; 
@WebServlet("/loginServlet")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/topgear")
	DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		response.setContentType("text/html");
        
		HttpSession session = request.getSession(true);
 
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Utente u = new Utente();
        
        if (email == null || password == null) {
        	request.setAttribute("error", Boolean.TRUE);
        	request.setAttribute("messgerr", "Parametri non presenti.");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        	dispatcher.forward(request, response);
        	return;
        }
        
        if(u.verificaCredenziali(email, password)) {
        	session.setAttribute("username", u.getCodicefiscale());
        	request.setAttribute("nome", u.getNome());
        	request.setAttribute("cognome", u.getCognome());
        	request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else {
        	request.setAttribute("error", Boolean.TRUE);
        	request.setAttribute("messgerr", "Credenziali non valide.");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        	dispatcher.forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
