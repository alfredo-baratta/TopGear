package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
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
		response.setContentType("text/html");
		
		String id_tmp1 = request.getParameter("id_remove");
		String id_tmp2 = request.getParameter("id_change");
		String id_tmp3 = request.getParameter("id_reset");
		
		
		if(id_tmp1 != null){
			
			int qta_tmp = Integer.parseInt(request.getParameter("qta"));
			
			if(qta_tmp >= 1){
				try{
					cart.remove(id_tmp1);
				}
				catch(Exception e){
					System.out.println("Errore: " + e.getMessage());
				}
			}
			
		}
		
		if(id_tmp2 != null){
			int qta_tmp = Integer.parseInt(request.getParameter("qta_change"));
			cart.changeQta(id_tmp2, qta_tmp);
		}
		
		if(id_tmp3 != null){
			cart.resetEverything();
		}
		
		session.setAttribute("cart", cart);
		response.sendRedirect("NewCart.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
