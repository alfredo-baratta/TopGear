package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.*;


@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Carrello cart = (Carrello)session.getAttribute("cart");
		//response.setContentType("text/html");
		
		/*
		ArrayList<Prodotto> lista_prodotti = cart.getListaProdottiAsArrayList();
		if(!lista_prodotti.isEmpty()) {
			int i = 0;
			for(Prodotto p : lista_prodotti) {
				String name = "test" + i;
				String value = p.toString();
				Cookie cookie = new Cookie(name, value);
				cookie.setHttpOnly(false);
				cookie.setMaxAge(-1);
				response.addCookie(cookie);
			}
		}
		*/
		
		session.setAttribute("cart", cart);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
		dispatcher.forward(request,  response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
