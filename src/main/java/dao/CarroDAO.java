package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Carro;

public class CarroDAO implements ICarroDAO {

	@Override
	public Carro cadastrar(Carro carro) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(carro);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		return carro;
	}

	@Override
	public Carro buscar(Long id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		Carro carro = entityManager.find(Carro.class, id);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		return carro;
	}

	@Override
	public List<Carro> buscarTodos() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		List<Carro> list = 
				entityManager.createQuery("SELECT c FROM Carro c", Carro.class).getResultList();      
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		
		return list;
	}

	@Override
	public void excluir(Carro carro) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		Carro carroManged = entityManager.merge(carro);
		entityManager.remove(carroManged);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}

	@Override
	public void excluirTodos(List<Carro> list) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		for (Carro carro : list) {
			entityManager.getTransaction().begin();
			Carro carroManged = entityManager.merge(carro);
			entityManager.remove(carroManged);
			entityManager.getTransaction().commit();
		}
		
		entityManager.close();
		entityManagerFactory.close();
		
	}
	
}
