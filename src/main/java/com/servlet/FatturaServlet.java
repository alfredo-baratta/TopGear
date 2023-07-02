package com.servlet;

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
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

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //imposto il font
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

        //Scrittura dati nel PDF
        contentStream.beginText();
        
        contentStream.newLineAtOffset(25, 700);
        contentStream.showText("Dati dell'ordine:");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText(orderData);		//temp, in realtà copio del codice da SalvaOrdine
        										//TODO: scrivi correttamente i dati nel PDF
        
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
