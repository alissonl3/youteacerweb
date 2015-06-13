/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.youteacher.email;

/**
 *
 * @author ALISSON
 */
public class Menssagem {
    
    private String destinatario;
    private String assunto;
    private String mensa;
    
    
    public Menssagem(){ super();}
    
    public Menssagem( String d, String a, String m){
            
            this.destinatario = d;
            this.assunto = a;
            this.mensa = m;

    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensa() {
        return mensa;
    }

    public void setMensa(String mensa) {
        this.mensa = mensa;
    }
    
    
    
}
