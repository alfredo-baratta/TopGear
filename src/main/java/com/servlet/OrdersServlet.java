package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrdersServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
	    if (session == null ||  session.getAttribute("username") == null) {
	        session.invalidate();
	    	response.sendRedirect("login.jsp");
	    }
	    else {
	    	
	    	//Prende le info degli ordini dal DataBase
			RequestDispatcher dispatcher = request.getRequestDispatcher("/orders.jsp");
			dispatcher.forward(request,  response);
	    }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
