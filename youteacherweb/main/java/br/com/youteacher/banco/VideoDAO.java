package br.com.youteacher.banco;

import java.util.List;
import javax.persistence.*;
import br.com.youteacherweb.entidades.Video;

public class VideoDAO {

	EntityManager em;

	public boolean inserir(Video video) {

		try {
			em = Banco.getIstancia().getEm();
			em.getTransaction().begin();
			em.persist(video);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;

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
		Query query = em.createQuery("SELECT e FROM video e");
		List<Video> videos = query.getResultList();
		return videos;

	}

}
