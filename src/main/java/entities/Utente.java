package entities;

import java.sql.*;
import org.apache.commons.codec.digest.DigestUtils;

import Model.DriverManagerConnectionPool;
public class Utente {
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
    
    public Risposta salvaUtente(String codiceFiscale, String nome, String cognome, String dataDiNascita, String email, String password, String cellulare, String citta, String indirizzo, String cap) {
        Risposta r = new Risposta();
        try (Connection conn = DriverManagerConnectionPool.getConnection()) {
        	String query = "INSERT INTO Utente (CF, nome, cognome, datanascita, email, pass, cellulare, citta, via, cap) "
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
        	String query = "SELECT COUNT(*) FROM utente WHERE email = ?";
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
            String query = "SELECT * FROM utente WHERE email = ? AND pass = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, DigestUtils.md5Hex(password));
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                setCodicefiscale(rs.getString("cf"));
                setEmail(rs.getString("email"));
                setPassword(rs.getString("pass"));
                setNome(rs.getString("nome"));
                setCognome(rs.getString("cognome"));
                setCellulare(rs.getString("cellulare"));
                setDatanascita(rs.getString("datanascita"));
                setCitta(rs.getString("citta"));
                setCap(rs.getString("cap"));
                setIndirizzo(rs.getString("indirizzo"));
            }
            DriverManagerConnectionPool.releaseConnection(conn);
            return true;
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
        
        return false;
    }
    
}

