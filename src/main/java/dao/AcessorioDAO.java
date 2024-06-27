package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Acessorio;

public class AcessorioDAO implements IAcessorioDAO {

	@Override
	public Acessorio cadastrar(Acessorio acessorio) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(acessorio);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		return acessorio;
	}

	@Override
	public Acessorio buscar(Long id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		Acessorio acessorio = entityManager.find(Acessorio.class, id);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		return acessorio;
	}

	@Override
	public List<Acessorio> buscarTodos() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		List<Acessorio> list = 
				entityManager.createQuery("SELECT a FROM Acessorio a", Acessorio.class).getResultList();      
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		
		return list;
	}

	@Override
	public void excluir(Acessorio acessorio) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		Acessorio acessorioManged = entityManager.merge(acessorio);
		entityManager.remove(acessorioManged);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}

	@Override
	public void excluirTodos(List<Acessorio> list) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		for (Acessorio acessorio : list) {
			entityManager.getTransaction().begin();
			Acessorio acessorioManged = entityManager.merge(acessorio);
			entityManager.remove(acessorioManged);
			entityManager.getTransaction().commit();
		}
		
		entityManager.close();
		entityManagerFactory.close();
		
	}
	
}
