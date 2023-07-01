package com.servlet;

import java.io.IOException;
import java.util.*;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String orderData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        
        /*
        HttpSession session = request.getSession();
        
        if (session == null ||  session.getAttribute("username") == null) {
	        session.invalidate();
	    	response.sendRedirect("login.jsp");
	    }
	    */
        
        Ordine ordine = new Ordine();
        
        //devo prendere i dati presenti in orderData, dividerli e passarli alla funzione salvaOrdine
        
        System.out.println(orderData);	//testing
        
        
        orderData = orderData.trim();
        orderData = orderData.substring(1, orderData.length() - 1);

        //prendo a parte tutti gli item del cart
    	int startIndex = orderData.indexOf("[");
    	int endIndex = orderData.indexOf("]");
        String items_string = orderData.substring(startIndex, endIndex);
        items_string = items_string.replace("\"", "").replace("[", "").replace("]", "").replace("{", "").replace("}", "");
        
    	String[] items = items_string.split(",");
        String[] elements = orderData.split(",");

        List<String> cartItems = new ArrayList<>();
        int totalProducts = 0;
        float totalPrice = 0.0f;

        
        for (String element : elements) {
            
            String[] keyValue = element.split(":");
            
            String first_key = keyValue[0].trim();
            String value = keyValue[1].trim();
            
            if(first_key.equals("\"cart\"")) {

            	for(int i = 0; i < items.length/5; i++) {
            		
            		System.out.println("\nnuovo item!");
            		for(int j = 0; j < 5; j++) {
            			int index = (j + 5*i);
            			String couple_temp = items[index];
            			cartItems.add(couple_temp);
            			
            			//testing
            			System.out.println("ind: " + index + " " + couple_temp);
            		}
            	}
            }
            
            value = value.replace("\"", "");
            if (first_key.equals("\"totalProducts\"")) {
                totalProducts = Integer.parseInt(value);
            } else if (first_key.equals("\"totalPrice\"")) {
                totalPrice = Float.parseFloat(value);
            }
        }
        
        //testing
        System.out.println("\n");
        System.out.println("Prodotti: " + totalProducts);
        System.out.println("Totale: " + totalPrice);
        
        //salvo proprio l'ordine nel database
        ordine.salvaOrdine(cartItems, totalProducts, totalPrice);

        response.setContentType("application/json");
        response.getWriter().write("Ordine effettuato correttamente!");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
