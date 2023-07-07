package com.servlet;

import java.io.IOException;
import java.sql.*;
import java.io.*;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import Model.DriverManagerConnectionPool;


@WebServlet("/FatturaServlet")
public class FatturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FatturaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idOrdineString = request.getReader().lines().findFirst().orElse("");;
        int idOrdine = Integer.parseInt(idOrdineString);
        
        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        PreparedStatement stmt4 = null;
        PreparedStatement stmt5 = null;
        PreparedStatement stmt6 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        ResultSet rs6 = null;
        
        //Creazione del documento PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        
        //Impostazioni del documento
        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setTitle("Fattura");
        
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
       
        //Impostazione del font
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        
        //Definisco le dimensioni della tabella degli elementi
        int pageWidth = (int) page.getTrimBox().getWidth();
        int pageHeight = (int) page.getTrimBox().getHeight();
        int initX = 50;
        int initY = pageHeight - 200;
        int cellHeight = 30;
        int cellWidth = 100;
        int colCount = 3;
        
        //Linea divisoria tra gli elementi
        contentStream.setLineWidth(1);
        
        int totalProducts = 0;
        float totalPrice = 0.0f;
    	Date data_pagamento = new Date();
        
        try {
        	conn = DriverManagerConnectionPool.getConnection();
        	
        	String fk_utente = ""; //sarebbe il codice fiscale
        	String nome = "";
        	String cognome = "";
        	String citta = "";
        	String via  = "";
        	String cap = "";
        	
        	//prendo i dati dell'ordine dato l'idOrdine
        	String ordineQuery = "SELECT * FROM ordini WHERE id = ?";
        	stmt1 = conn.prepareStatement(ordineQuery);
        	stmt1.setInt(1, idOrdine);
        	rs1 = stmt1.executeQuery();
        	
        	if(rs1.next()) {
        		totalPrice = rs1.getFloat("totale");
        		data_pagamento = rs1.getDate("data_pagamento");
        		fk_utente = rs1.getString("fk_utente");
        		
        	}
        	
        	//prendo alcune info dell'utente che ha fatto l'ordine:
        	String utenteQuery = "SELECT * FROM utenti WHERE cf = ?";
        	stmt2 = conn.prepareStatement(utenteQuery);
        	stmt2.setString(1, fk_utente);
        	rs2 = stmt2.executeQuery();
        	
        	if(rs2.next()) {
        		nome = rs2.getString("nome");
        		cognome = rs2.getString("cognome");
        		citta = rs2.getString("citta");
        		via = rs2.getString("via");
        		cap = rs2.getString("cap");
        	}
        	
        	//prendo un singolo ordineAccessorio, e da quello risalgo alla fattura_accessorio
        	String ordineAccessorioGenericoQuery = "SELECT * FROM ordini_accessorio WHERE fk_ordine = ?";
        	stmt3 = conn.prepareStatement(ordineAccessorioGenericoQuery);
        	stmt3.setInt(1, idOrdine);
        	rs3 = stmt3.executeQuery();
        	
        	int idOrdineAccessorioGenerico = -1;
        	
        	if(rs3.next()) {
        		idOrdineAccessorioGenerico = rs3.getInt("id");
        	}
        	
        	//risalgo alla fattura accessorio da cui prendo l'iva
        	String FatturaAccessorio = "SELECT * FROM fattura_accessori WHERE fk_ordine_accessorio = ?";
        	stmt4 = conn.prepareStatement(FatturaAccessorio);
        	stmt4.setInt(1, idOrdineAccessorioGenerico);
        	rs4 = stmt4.executeQuery();
        	
        	int iva = 0;
        	
        	if(rs4.next()) {
        		iva = rs4.getInt("iva");
        	}
        	
        	//prendo tutti i dati di tutti gli accessori dell'ordine
        	String OrdineAccessorioQuery = "SELECT * FROM ordini_accessorio WHERE fk_ordine = ?";
        	stmt5 = conn.prepareStatement(OrdineAccessorioQuery);
        	stmt5.setInt(1, idOrdine);
        	rs5 = stmt5.executeQuery();
        	
        	
        	//informazioni preliminari:
        	contentStream.beginText();
            contentStream.newLineAtOffset(50, pageHeight - 50);
            contentStream.showText("Informazioni utente:");
            contentStream.newLineAtOffset(0, - 30);
            contentStream.showText("Nome: " + nome);
            contentStream.newLineAtOffset(0, - 20);
            contentStream.showText("Cognome: " + cognome);
            contentStream.newLineAtOffset(0, - 20);
            contentStream.showText("CF: " + fk_utente);
            
            contentStream.newLineAtOffset(260, +40);
            contentStream.showText("Città: " + citta);
            contentStream.newLineAtOffset(0, - 20);
            contentStream.showText("Via: " + via);
            contentStream.newLineAtOffset(0, - 20);
            contentStream.showText("Cap: " + cap);
            contentStream.endText();
        	
        	
        	contentStream.beginText();
            contentStream.newLineAtOffset(50, initY + 20);
            contentStream.showText("Informazioni ordine:");
            contentStream.endText();
			
            //disegna la prima riga della tabella
			for (int j = 1; j <= colCount; j++) {
                 if (j == 1) {
                     contentStream.addRect(initX, initY, cellWidth + 30, -cellHeight);
                     contentStream.beginText();
                     contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 10);
                     contentStream.showText("Nome");
                     contentStream.endText();
                     initX += cellWidth + 30;
                 } else if (j == 2) {
                     contentStream.addRect(initX, initY, cellWidth + 30, -cellHeight);
                     contentStream.beginText();
                     contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 10);
                     contentStream.showText("Quantità");
                     contentStream.endText();
                     initX += cellWidth + 30;
                 } else {
                     contentStream.addRect(initX, initY, cellWidth + 30, -cellHeight);
                     contentStream.beginText();
                     contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 10);
                     contentStream.showText("Prezzo");
                     contentStream.endText();
                     initX += cellWidth;
                 }
			 }
			 
			 //disegna i riquadri e passa alla riga successiva
			 contentStream.stroke();
 			 initX = 50;
 			 initY -= cellHeight;
        	
        	while(rs5.next()) {
        		
        		int quantita_accessorio = rs5.getInt("quantita");
        		int fk_accessorio = rs5.getInt("fk_accessorio");
        		
        		totalProducts += quantita_accessorio;
        		
        		String AccessorioQuery = "SELECT * FROM accessori WHERE id = ?";
        		stmt6 = conn.prepareStatement(AccessorioQuery);
        		stmt6.setInt(1, fk_accessorio);
        		rs6 = stmt6.executeQuery();
        		
        		if(rs6.next()) {
        			
        			String nome_accessorio = rs6.getString("nome");
        			int prezzo_accessorio = rs6.getInt("prezzo");
        			
        			//disegna la riga di info dell'accessorio
        			for (int j = 1; j <= colCount; j++) {
                		if (j == 1) {
                            contentStream.addRect(initX, initY, cellWidth + 30, -cellHeight);
                            contentStream.beginText();
                            contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 10);
                            contentStream.showText(nome_accessorio);
                            contentStream.endText();
                            initX += cellWidth + 30;
                        } else if (j == 2) {
                            contentStream.addRect(initX, initY, cellWidth + 30, -cellHeight);
                            contentStream.beginText();
                            contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 10);
                            contentStream.showText(Integer.toString(quantita_accessorio));
                            contentStream.endText();
                            initX += cellWidth + 30;
                        } else {
                            contentStream.addRect(initX, initY, cellWidth + 30, -cellHeight);
                            contentStream.beginText();
                            contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 10);
                            contentStream.showText("€ " + Float.toString(prezzo_accessorio));
                            contentStream.endText();
                            initX += cellWidth;
                        }
                    }
        			
        			//disegna i riquadri e passa alla riga successiva
        			contentStream.stroke();
        			initX = 50;
        			initY -= cellHeight;
        		}
        	}
        	
            //Scrivi le ultime informazioni prese dal database
            contentStream.stroke();
            contentStream.beginText();
            contentStream.newLineAtOffset(pageWidth - 200, 400);
            contentStream.showText("Elementi totali: " + totalProducts);
            contentStream.newLineAtOffset(0, 20);
            contentStream.showText("Costo totale: € " + totalPrice);
            contentStream.newLineAtOffset(0, 20);
            contentStream.showText("IVA: " + iva + "%");
            contentStream.newLineAtOffset(0, 20);
            contentStream.showText("Data: " + data_pagamento );
            contentStream.endText();

            contentStream.close();

            //Salva il documento PDF in un file temporaneo
            File tempFile = File.createTempFile("fattura", ".pdf");
            document.save(tempFile);
            document.close();

            //Imposta la risposta come PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"fattura.pdf\"");

            //Invia il file come risposta
            FileInputStream fileInputStream = new FileInputStream(tempFile);
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            fileInputStream.close();
            outputStream.flush();
            outputStream.close();
            tempFile.delete();
            
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (stmt1 != null) {
                try {
                    stmt1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt2 != null) {
            	try {
                    stmt2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt3 != null) {
            	try {
                    stmt3.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt4 != null) {
            	try {
                    stmt4.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt5 != null) {
            	try {
                    stmt5.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt6 != null) {
            	try {
                    stmt6.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
