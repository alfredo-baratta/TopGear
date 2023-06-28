package com.servlet;

import java.io.IOException;
import entities.Ordine;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SalvaOrdine")
public class SalvaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SalvaOrdine() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String cartData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        
        Ordine ordine_generico = new Ordine();
        
        //...salvo le info dell'ordine nell'oggetto ordine_generico e poi:
        
        ordine_generico.salvaOrdine(cartData); 

        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"success\"}");
	}

}
