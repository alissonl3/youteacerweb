/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.youteacher.banco;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author ALISSON
 */
public class Banco {
    
    EntityManager em;
    
    private static Banco instancia;
    
    
    public Banco(){
    
    em = Persistence.createEntityManagerFactory("youteacherweb").createEntityManager();
    
    }
    
    public synchronized static Banco getIstancia(){
    
        if(instancia == null){
        
            instancia = new Banco();
        }
        
        return instancia;
    
    
    }
    
    public EntityManager getEm(){
    
        return em;
    
    }
    
    
    
}
