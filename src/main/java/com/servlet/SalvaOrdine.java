package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import entities.Ordine;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DriverManagerConnectionPool;

@WebServlet("/SalvaOrdine")
public class SalvaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SalvaOrdine() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String orderData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        
        HttpSession session = request.getSession();
        
        if (session == null ||  session.getAttribute("username") == null) {
	        session.invalidate();
	    	response.sendRedirect("login.jsp");
	    }
        
        String username = (String) session.getAttribute("username"); 
        String cf = "";
        try (Connection conn = DriverManagerConnectionPool.getConnection();
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM utenti WHERE cf = ?")) {

               statement.setString(1, username);
               try (ResultSet rs = statement.executeQuery()) {
                   if (rs.next()) {
                       cf = rs.getString("cf");
                   }
               }
               DriverManagerConnectionPool.releaseConnection(conn);
           } catch (Exception e) {
               System.out.println("Errore: " + e.getMessage());
           }
        
        Ordine ordine = new Ordine();
        
        
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
            		
            		for(int j = 0; j < 5; j++) {
            			int index = (j + 5*i);
            			String couple_temp = items[index];
            			cartItems.add(couple_temp);
            			
            		}
            	}
            }
            
            //ultime informazioni importanti:
            value = value.replace("\"", "");
            if (first_key.equals("\"totalProducts\"")) {
                totalProducts = Integer.parseInt(value);
            } else if (first_key.equals("\"totalPrice\"")) {
                totalPrice = Float.parseFloat(value);
            }
        }
        
        //salvo proprio l'ordine nel database
        int idOrdine = ordine.salvaOrdine(cartItems, totalProducts, totalPrice, cf);
        
        //Salvo la fattura nel DB
        ordine.salvaFattura(cartItems, totalProducts);

        response.setContentType("text/plain");
        response.getWriter().write("Ordine effettuato correttamente!");

        if (idOrdine != -1) {
            response.getWriter().write("\nIdOrdine: " + idOrdine);
        } else {
            response.getWriter().write("\nErrore durante l'elaborazione dell'ordine.");
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
