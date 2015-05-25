package br.com.youteacher.banco.dao;

import java.util.List;

import javax.persistence.*;

import br.com.youteacher.banco.Banco;
import br.com.youteacherweb.entidades.Formulario;
import br.com.youteacherweb.entidades.Usuario;
import br.com.youteacherweb.entidades.Video;

public class VideoDAO {

	EntityManager em;

	public void inserir(Video video) {

		try {
			em = Banco.getIstancia().getEm();
			em.getTransaction().begin();
			em.persist(video);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();

		}

	}

	public boolean remover(Video video) {
		try {
			em.getTransaction().begin();
			em.remove(video);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		}
	}

	public List<Video> listarTodos() {
		em = Banco.getIstancia().getEm();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT c FROM Video c");
		em.getTransaction().commit();
		return query.getResultList();

	}
	
	public List<Video> listarCondicaoVideo(String condicao) {

		em = Banco.getIstancia().getEm();
		em.getTransaction().begin();
		Query q = em.createQuery("from " + Video.class.getSimpleName()
				+ " where " + condicao);
		em.getTransaction().commit();
		return q.getResultList();

	}
	
	public Video pesquisarPorID(Integer id){
		em = Banco.getIstancia().getEm();
		em.getTransaction().begin();
		Query q = em.createQuery("from " + Video.class.getSimpleName()
				+ " where id = " + id);
		em.getTransaction().commit();
		return (Video)q.getSingleResult();
	}
	public List<Video> pesquisarPorUsuario(Integer id){
		  em = Banco.getIstancia().getEm();
		  em.getTransaction().begin();
		  Query q = em.createQuery(" select v from Video v where v.usuario = "+ id);
		  em.getTransaction().commit();
		  return q.getResultList();
		 }
	

}
