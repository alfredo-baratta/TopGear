package entities;

import java.sql.*;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.DriverManagerConnectionPool;
public class Utente {
    private static String formatoemail = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    private static String formatonumtel = "^\\d{10}$";
    private static String formatoindirizzo = "^[a-zA-Z\\s\\d]+,\\s(\\d+[A-Za-z]?|SNC|snc)$\n";
    private static String formatostringaalfabetica = "^[a-zA-Z\\s]+$";
    private static String formatocap = "[0-9]{5}";
    private static String formatopw = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$\n";
    private static String formatoCF = "[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}";
    private static String formatodata = "^\\d{4}-\\d{2}-\\d{2}$\n";
	private String codicefiscale;
	private String email;
    private String password;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String citta;
    private String datanascita;
    private String cellulare;
    private String cap;
     
    public Utente() {
    }
    
    public String getCodicefiscale() {
    	return codicefiscale;
    }
    
    private void setCodicefiscale(String codicefiscale) {
    	this.codicefiscale = codicefiscale;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password);
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCognome() {
        return cognome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    public String getIndirizzo() {
        return indirizzo;
    }
    
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    
    public String getCitta() {
        return citta;
    }
    
    public void setCitta(String citta) {
        this.citta = citta;
    }
    
    public String getDatanascita() {
        return datanascita;
    }
    
    public void setDatanascita(String datanascita) {
        this.datanascita = datanascita;
    }
    
    public String getCellulare() {
        return cellulare;
    }
    
    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }
    
    public String getCap() {
        return cap;
    }
    
    public void setCap(String cap) {
        this.cap = cap;
    }
    
    public boolean validaCF(String CF) {
    	Pattern pattern = Pattern.compile(formatoCF);
    	Matcher matcher = pattern.matcher(CF);
        return matcher.matches();
    }
    
    public boolean validaEmail(String email) {
    	Pattern pattern = Pattern.compile(formatoemail);
    	Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public boolean validaNumeroTelefonico(String numero) {
    	Pattern pattern = Pattern.compile(formatonumtel);
    	Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }
    
    public boolean validaPassword(String password) {
    	Pattern pattern = Pattern.compile(formatopw);
    	Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }
    
    public boolean validaIndirizzo(String indirizzo) {
    	Pattern pattern = Pattern.compile(formatoindirizzo);
    	Matcher matcher = pattern.matcher(indirizzo);
        return matcher.matches();
    }
    
    public boolean validaAlfabetica(String stringa) {
    	Pattern pattern = Pattern.compile(formatostringaalfabetica);
    	Matcher matcher = pattern.matcher(stringa);
        return matcher.matches();
    }
    
    public boolean validaCAP(String cap) {
    	Pattern pattern = Pattern.compile(formatocap);
    	Matcher matcher = pattern.matcher(cap);
        return matcher.matches();
    }
    
    public boolean validaData(String data) {
    	Pattern pattern = Pattern.compile(formatodata);
    	Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }
    
    public Risposta salvaUtente(String codiceFiscale, String nome, String cognome, String dataDiNascita, String email, String password, String cellulare, String citta, String indirizzo, String cap) {
        Risposta r = new Risposta();
        if(!validaCF(codiceFiscale)) {
        	r.setEsito(false);
        	r.setMessage("Errore! Codice fiscale non valido!");
        	return r;
        }
        if(!validaEmail(email)) {
        	r.setEsito(false);
        	r.setMessage("Errore! Email non valida!");
        	return r;
        }
        if(!validaNumeroTelefonico(cellulare)) {
        	r.setEsito(false);
        	r.setMessage("Errore! Numero telefonico non valido!");
        	return r;
        }
        if(!validaPassword(password)) {
        	r.setEsito(false);
        	r.setMessage("Errore! Password non valida!");
        	return r;
        }
        if(!validaIndirizzo(indirizzo)) {
        	r.setEsito(false);
        	r.setMessage("Errore! Indirizzo non valido!");
        	return r;
        }
        if(!validaAlfabetica(nome)) {
        	r.setEsito(false);
        	r.setMessage("Errore! Nome non valido!");
        	return r;
        }
        if(!validaAlfabetica(cognome)) {
        	r.setEsito(false);
        	r.setMessage("Errore! Cognome non valido!");
        	return r;
        }
        if(!validaAlfabetica(citta)) {
        	r.setEsito(false);
        	r.setMessage("Errore! Città non valida!");
        	return r;
        }
        if(!validaCAP(cap)) {
        	r.setEsito(false);
        	r.setMessage("Errore! Cap non valido!");
        	return r;
        }
        if(!validaData(dataDiNascita)) {
        	r.setEsito(false);
        	r.setMessage("Errore! Data di nascita non valida!");
        	return r;
        }
        try (Connection conn = DriverManagerConnectionPool.getConnection()) {
        	String query = "INSERT INTO utenti (cf, nome, cognome, datanascita, email, password, telefono, citta, via, cap) "
        			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        	PreparedStatement ps = conn.prepareStatement(query);
        	ps.setString(1, codiceFiscale);
	        ps.setString(2, nome);
	        ps.setString(3, cognome);
	        ps.setString(4, dataDiNascita);
	        ps.setString(5, email);
	        ps.setString(6, DigestUtils.md5Hex(password));
	        ps.setString(7, cellulare);
	        ps.setString(8, citta);
	        ps.setString(9, indirizzo);
	        ps.setString(10, cap);
            ps.executeUpdate();
            
            setCodicefiscale(codiceFiscale);
            setNome(nome);
            setCognome(cognome);
            setEmail(email);
            setIndirizzo(indirizzo);
            setCitta(citta);
            setCellulare(cellulare);
            setDatanascita(dataDiNascita);
            setCap(cap);
            setPassword(password);
            
            DriverManagerConnectionPool.releaseConnection(conn);
            r.setEsito(true);
            r.setMessage("OK");
        }catch (SQLException e) {
        	r.setEsito(false); 
        	if (e.getMessage().toLowerCase().contains("duplicate")) {
        		if(e.getMessage().contains("utente.PRIMARY"))
        			r.setMessage("Errore, il codice fiscale da lei inserito risulta già presente nei nostri sistemi");
        		else if(e.getMessage().contains("utente.email"))
        			r.setMessage("Errore, l'indirizzo email da lei inserito risulta già presente nei nostri sistemi");
        		else if(e.getMessage().contains("utente.cellulare"))
        			r.setMessage("Errore, il numero di telefono da lei inserito risulta già presente nei nostri sistemi");
        		else {
        			r.setMessage("Errore, ricontrollare i dati inseriti");
				}
        	}
		}
        return r;
    }
    
    public boolean verificaEmail(String email) {
    	Boolean find = false;
        
    	 try (Connection conn = DriverManagerConnectionPool.getConnection()) {
        	String query = "SELECT COUNT(*) FROM utenti WHERE email = ?";
        	PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
            	if(rs.getInt(1)>0) {
            		System.out.println(rs.getInt(1));
            		find = true;
            	}
            }
            DriverManagerConnectionPool.releaseConnection(conn);
        }catch (Exception e) {
        	System.out.println("Errore: " + e.getMessage());
		}
        return find;
    }
    
    public boolean verificaCredenziali(String email, String password) {
    	
        try (Connection conn = DriverManagerConnectionPool.getConnection()) {
            String query = "SELECT * FROM utenti WHERE email = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, DigestUtils.md5Hex(password));
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                setCodicefiscale(rs.getString("cf"));
                setEmail(rs.getString("email"));
                setPassword(rs.getString("password"));
                setNome(rs.getString("nome"));
                setCognome(rs.getString("cognome"));
                setCellulare(rs.getString("telefono"));
                setDatanascita(rs.getString("datanascita"));
                setCitta(rs.getString("citta"));
                setCap(rs.getString("cap"));
                setIndirizzo(rs.getString("via"));
            }
            DriverManagerConnectionPool.releaseConnection(conn);
            return true;
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
        
        return false;
    }
    
}

