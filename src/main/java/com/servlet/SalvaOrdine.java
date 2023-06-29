package com.servlet;

import java.io.IOException;
import entities.Ordine;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SalvaOrdine")
public class SalvaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SalvaOrdine() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String orderData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        
        HttpSession session = request.getSession();
        
        if (session == null ||  session.getAttribute("username") == null) {
	        session.invalidate();
	    	response.sendRedirect("login.jsp");
	    }
        
        Ordine ordine = new Ordine();
        
        //devo prendere i dati presenti in orderData, dividerli e passarli alla funzione salvaOrdine
        int totalProducts = 0;
        float totalPrice = 0;
        
        System.out.println(orderData);	//test. Mi serve per vedere come i dati vengono stampati
        //successivamente estrapolo le info e le ripongo correttamente nell'oggetto ordine
        
        //salvo proprio l'ordine nel database
        ordine.salvaOrdine(orderData, totalProducts, totalPrice); 

        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"success\"}");
	}

}