package br.com.youteacher.banco.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.youteacher.banco.Banco;
import br.com.youteacherweb.entidades.Usuario;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	EntityManager em;

	
	public List<Usuario> listaTodos() {

		Query q = em.createQuery("from Usuario u");
		return q.getResultList();

	}

	public void inserirUsuario(Usuario usuario) {
		try {
			em = Banco.getIstancia().getEm();
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally{
			em.close();
		}
	}

	public boolean deletarUsuario(Usuario usuario) {
		try {
			em.getTransaction().begin();
			em.remove(usuario);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	 public List listarCondicaoUsuario(String condicao){
		    
	        em = Banco.getIstancia().getEm();
	        em.getTransaction().begin();
	        Query q = em.createQuery("from " + Usuario.class.getSimpleName() + " where " + condicao);
	        em.getTransaction().commit();
	        return q.getResultList();
	    
	    }
}
