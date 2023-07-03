package com.servlet;

import java.io.IOException;
import java.util.List;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;   
import java.text.SimpleDateFormat; 
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


@WebServlet("/FatturaServlet")
public class FatturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FatturaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String orderData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

        //Creo il documento PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        
        //Data di quando è stata fatta la fattura
        PDDocumentInformation pdd = document.getDocumentInformation();
        long millis = System.currentTimeMillis(); 
        DateFormat dateStyle = new SimpleDateFormat("dd MMM yyyy HH:mm:ss"); 
        Date date = new Date(millis);
        
        pdd.setTitle("Fattura");

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //imposto il font
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

        //Scrivo la prima riga di informazioni
        contentStream.beginText();
        
        contentStream.newLineAtOffset(50, 720);
        contentStream.showText("Fattura dell'ordine effettuato il " + dateStyle.format(date));
        
        contentStream.endText();
        
        
        //prendo a parte tutti gli item del cart
        orderData = orderData.trim();
        orderData = orderData.substring(1, orderData.length() - 1);

    	int startIndex = orderData.indexOf("[");
    	int endIndex = orderData.indexOf("]");
        String items_string = orderData.substring(startIndex, endIndex);
        items_string = items_string.replace("\"", "").replace("[", "").replace("]", "").replace("{", "").replace("}", "");
        
    	String[] items = items_string.split(",");
        String[] elements = orderData.split(",");

        int totalProducts = 0;
        float totalPrice = 0.0f;
        
        
        //Definisco una serie di misure per costruire la tabella degli items
        int pageWidth = (int)page.getTrimBox().getWidth(); 
        int pageHeight = (int)page.getTrimBox().getHeight(); 
        
        int initX = 50;
        int initY = pageHeight-150;
        int cellHeight = 30;
        int cellWidth = 100;

        int colCount = 3;
        int rowCount = 1 + (items.length/5);
        
        //width della linea che dividerà gli elementi
        contentStream.setLineWidth(1);
        
        for (String element : elements) {
            
            String[] keyValue = element.split(":");
            
            String first_key = keyValue[0].trim();
            String value = keyValue[1].trim();
            
            if(first_key.equals("\"cart\"")) {
            	//Costruisco la tabella
            	

                for(int i = 1; i<=rowCount;i++){ //righe, una per elemento
                	
                	String nome = "", quantity = "", prezzo = "";
                	
                	//per prendere gli elementi del cart parte dalla seconda riga
                	if(i >= 2) {
                		int m = i-2;
                		for(int l = 0; l < 5; l++) {
                		
            				int index = (l + 5*m);
            				String couple_temp = items[index];
            			
            				String[] couple = couple_temp.split(":");
            				if(l == 1) {
            					quantity = couple[1];
            				}
            				else if (l == 2) {
            					nome = couple[1];
            				}
            				else if (l == 3) {
            					prezzo = couple[1];
            				}
            			}
                	}
                	
                    for(int j = 1; j<=colCount;j++){ 	//colonne
                    	
                    	//riga di informazioni della tabella
                    	if(i == 1) {
                    		
                    		if(j == 1) {
                    			contentStream.addRect(initX,initY,cellWidth+10,-cellHeight);

                                contentStream.beginText();
                                contentStream.newLineAtOffset(initX+30,initY-cellHeight+10);
                                contentStream.showText("Nome");
                                contentStream.endText();
                                
                                initX+=cellWidth+30;
                    		}
                    		
                    		else if(j == 2) {
                    			contentStream.addRect(initX,initY,cellWidth+10,-cellHeight);

                                contentStream.beginText();
                                contentStream.newLineAtOffset(initX+30,initY-cellHeight+10);
                                contentStream.showText("Quantità");
                                contentStream.endText();
                                
                                initX+=cellWidth+30;
                    		}
                    		
                    		else {
                    			contentStream.addRect(initX,initY,cellWidth+10,-cellHeight);

                                contentStream.beginText();
                                contentStream.newLineAtOffset(initX+30,initY-cellHeight+10);
                                contentStream.showText("Prezzo");
                                contentStream.endText();
                                
                                initX+=cellWidth;
                    		}
                 
                    	}
                    	//elementi della tabella
                    	else {
                    		if(j == 1) {
                    			contentStream.addRect(initX,initY,cellWidth+30,-cellHeight);

                                contentStream.beginText();
                                contentStream.newLineAtOffset(initX+10,initY-cellHeight+10);
                                contentStream.showText(nome);
                                contentStream.endText();
                                
                                initX+=cellWidth+30;
                    		}
                    		
                    		else if(j == 2) {
                    			contentStream.addRect(initX,initY,cellWidth+30,-cellHeight);

                                contentStream.beginText();
                                contentStream.newLineAtOffset(initX+10,initY-cellHeight+10);
                                contentStream.showText(quantity);
                                contentStream.endText();
                                
                                initX+=cellWidth+30;
                    		}
                    		
                    		else {
                    			contentStream.addRect(initX,initY,cellWidth+30,-cellHeight);

                                contentStream.beginText();
                                contentStream.newLineAtOffset(initX+10,initY-cellHeight+10);
                                contentStream.showText(prezzo);
                                contentStream.endText();
                                
                                initX+=cellWidth;
                    		}
                    	}
                    }
                    //disegno i contorni e passo alla riga successiva
                    contentStream.stroke();
                    initX = 50;
                    initY -=cellHeight;
                }
            }
            
            //prendo gli altri valori presenti nell'orderData
            value = value.replace("\"", "");
            if (first_key.equals("\"totalProducts\"")) {
                totalProducts = Integer.parseInt(value);
            } else if (first_key.equals("\"totalPrice\"")) {
                totalPrice = Float.parseFloat(value);
            }
        }
        
        //Scrivo le ultime informazioni prese da orderData
        contentStream.stroke();
        contentStream.beginText();

        contentStream.newLineAtOffset(pageWidth-200, 400);
        contentStream.showText("Elementi totali: " + totalProducts);
        contentStream.newLineAtOffset(0, 20);
        contentStream.showText("Costo totale: " + totalPrice + "€");
        
        contentStream.endText();

        contentStream.close();
        
        //File temp su cui salvare il PDF
        File tempFile = File.createTempFile("fattura", ".pdf");
        document.save(tempFile);
        document.close();

        //Imposto la risposta di tipo PDF (non sapevo esistesse!?)
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"fattura.pdf\"");

        //Inviamo il tutto come risposta
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

        //lol immagina lasciare il file temporaneo 
        tempFile.delete();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
