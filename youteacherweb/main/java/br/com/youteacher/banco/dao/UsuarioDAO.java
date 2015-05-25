package br.com.youteacher.banco.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.youteacher.banco.Banco;
import br.com.youteacherweb.entidades.Usuario;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	EntityManager em;

	public List<Usuario> listarTodos() {
		
		em = Banco.getIstancia().getEm();
		em.getTransaction().begin();
		Query q = em.createQuery("from Usuario u");
		em.getTransaction().commit();
		return q.getResultList();

	}

	public void inserir(Usuario usuario) {
		try {

			em = Banco.getIstancia().getEm();
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} 
	}

	public boolean remover(Usuario usuario) {
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

	public List<Usuario> listarCondicaoUsuario(String condicao) {

		em = Banco.getIstancia().getEm();
		em.getTransaction().begin();
		Query q = em.createQuery("from " + Usuario.class.getSimpleName()
				+ " where " + condicao);
		em.getTransaction().commit();
		return q.getResultList();

	}


	public void alterar(Usuario usuario) {
		em = Banco.getIstancia().getEm();
		em.getTransaction().begin();
		em.merge(usuario); 
		em.getTransaction().commit();

	}

	public Usuario pesquisarUsuarioPorId(Long id) {
		em = Banco.getIstancia().getEm();
		Usuario retornado = null;
		em.getTransaction().begin();
		retornado = em.find(Usuario.class, id);
		em.getTransaction().commit();
		return retornado;

	}
}
