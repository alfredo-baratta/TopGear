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
        
        System.out.println(orderData);	//test. Mi serve per vedere come i dati vengono stampati
        //successivamente estrapolo le info e le ripongo correttamente nell'oggetto ordine
        
        
        orderData = orderData.trim();
        orderData = orderData.substring(1, orderData.length() - 1);

        String[] elements = orderData.split(",");

        List<String> cartItems = new ArrayList<>();
        int totalProducts = 0;
        float totalPrice = 0.0f;

        
        for (String element : elements) {
            
            String[] keyValue = element.split(":");
            
            String first_key = keyValue[0].trim();
            String value = keyValue[1].trim();
            
            if(first_key.equals("\"cart\"")) {
            	/*
            	for(int i = 1; i < 5; i++) {
            			String value = keyValue[i].trim();
            			value = value.replace("\"", "");
            			value = value.replace("[", "");
            			value = value.replace("{", "");
            			value = value.replace("}", "");
            			value = value.replace("]", "");
            			System.out.println(value);
            			cartItems.add(value);
            	}
            	*/
            	
            	//no ma niente ho fatto tutto 'sto casino scervellandomi e comunque non funziona
            	//tempo 2 secondi e uso una libreria tipo Gson per ottenere tutti i dati dal file JSON
            	
            	value = value.replace("[", "");
                value = value.replace("]", "");

                String[] cartElements = value.split("\\},\\{");

                for (String cartElement : cartElements) {
                    cartElement = cartElement.replace("{", "").replace("}", "").trim();
                    String[] cartItemElements = cartElement.split(",");

                    for (String itemElement : cartItemElements) {
                        String[] itemKeyValue = itemElement.split(":");
                        String key = itemKeyValue[0].trim().replace("\"", "");
                        String itemValue = itemKeyValue[1].trim().replace("\"", "");

                        cartItems.add(key);
                        cartItems.add(itemValue);
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
        System.out.println("Prodotti:" + totalProducts);
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
