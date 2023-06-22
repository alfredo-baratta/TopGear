package com.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.Blob;

import java.io.OutputStream;

@WebServlet("/immagini_a")
public class Gigino extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/topgear")
	DataSource dataSource;

    public Gigino() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idImmagine = request.getParameter("id");
	    
	    if(idImmagine == null || idImmagine.isEmpty()) {
	    	return;
	    }
	    
	    Connection conn = null;
	    try {
	    	conn = dataSource.getConnection();
    	
	    	int id = Integer.parseInt(idImmagine);
        
	    	String query = "SELECT immagine FROM immagini_accessorio WHERE id = ?";
	    	PreparedStatement stmt = conn.prepareStatement(query);
	    	stmt.setInt(1, id);
	    	ResultSet rs = stmt.executeQuery();
	    	byte[] immagine = new byte[0];


	    	if (rs.next()) {
	    		//byte[] immagine = rs.getBytes("immagine");
	    		 Blob blob = (Blob) rs.getBlob("immagine");

	    		    if (blob != null) {
	    		        immagine = blob.getBytes(1, (int) blob.length());
	    		        blob.free(); // Libera le risorse del Blob
	    		    }
	    		
	    		response.setContentType("image/jpeg");
	    		
	    		OutputStream outputStream = response.getOutputStream();

	    		outputStream.write(immagine);
	    		outputStream.close();
	    	}
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Errore durante la chiusura della connessione: " + e.getMessage());
                }
            }
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
