/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.youteacher.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author ALISSON
 */
public class Carteiro {
    
    private String hostName = "smtp.gmail.com";
	private String usuario = "youteacher2015"; // Seu login do Gmail
	private String senha = "tccyouteacer2015"; // Sua senha do Gmail
	private String email = "youteacher2015@gmail.com"; 
        
        private SimpleEmail simple;
        
        public Carteiro(){
        
            this.simple = new SimpleEmail();
        
        }
        
        public boolean enviarMensagem(Menssagem men){
        
        try{
        	
        simple.setHostName(hostName);
        simple.setAuthentication(usuario, senha);
        simple.setSmtpPort(465);
        simple.setSSL(true);
 
 
		simple.setFrom(email);
		simple.addTo(men.getDestinatario());
		simple.setSubject(men.getAssunto());
		simple.setMsg(men.getMensa());
 
		simple.send();
		
		return true;
        
        }catch(Exception erro){
            
                
        	System.out.println("Erro email 1: " + erro);
        
        	return false; 
        }
        
        
        
        }
    
}
