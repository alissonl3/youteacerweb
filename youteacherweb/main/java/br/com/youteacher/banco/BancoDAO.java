/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.youteacher.banco;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author ALISSON
 */
public class BancoDAO implements Serializable {
 
	private static final long serialVersionUID = 1L;
	

	
	EntityManager em;
    
    public List listaTodos(Class classe){
    
        em = Banco.getIstancia().getEm();
        em.getTransaction().begin();
        Query q = em.createQuery("from " + classe.getSimpleName());
        em.getTransaction().commit();
        return q.getResultList();
        
    
    }
    
    public List listarCondicao(Class classe, String condicao){
    
        em = Banco.getIstancia().getEm();
        em.getTransaction().begin();
        Query q = em.createQuery("from " + classe.getSimpleName() + " where " + condicao);
        em.getTransaction().commit();
        return q.getResultList();
    
    }
    
    public void inserir(Object obj){
    
        em = Banco.getIstancia().getEm();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();

    }
    
    public void salvar(Object obj){
        em = Banco.getIstancia().getEm();
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    
    
    }
    
    public void deletar(Object obj){
    
        em = Banco.getIstancia().getEm();
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
    
    }
    
   public Object recupera(Class classe, Long id){
        em = Banco.getIstancia().getEm();
        Object retornado = null;
        em.getTransaction().begin();
        retornado = em.find(classe, id);
        em.getTransaction().commit();
        return retornado;
        
        
   
   
   }
    
    
    
    
}
