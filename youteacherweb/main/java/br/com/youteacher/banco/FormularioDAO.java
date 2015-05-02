package br.com.youteacher.banco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.youteacherweb.entidades.Formulario;

public class FormularioDAO {

	EntityManager em;

	public void inserir(Formulario formulario) {

		try {
			em = Banco.getIstancia().getEm();
			em.getTransaction().begin();
			em.persist(formulario);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

	}

	public void remover(Formulario formulario) {

		try {
			em = Banco.getIstancia().getEm();
			em.getTransaction().begin();
			em.remove(formulario);
			em.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			em.getTransaction().rollback();
		}

	}

	public List<Formulario> listarTodos() {

		Query query = em.createQuery("SELECT f FROM formulario e ");
		List<Formulario> formularios = query.getResultList();
		return formularios;
	}

}
