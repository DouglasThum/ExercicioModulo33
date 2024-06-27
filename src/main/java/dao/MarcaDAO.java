package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Marca;

public class MarcaDAO implements IMarcaDAO {
	
	@Override
	public Marca cadastrar(Marca marca) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(marca);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		return marca;
	}
	@Override
	public Marca buscar(Long id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		Marca marca = entityManager.find(Marca.class, id);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();;
		return marca;
	}
	@Override
	public List<Marca> buscarTodos() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		List<Marca> list = 
				entityManager.createQuery("SELECT m FROM Marca m", Marca.class).getResultList();      
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		
		return list;
	}
	@Override
	public void excluir(Marca marca) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		Marca marcaManged = entityManager.merge(marca);
		entityManager.remove(marcaManged);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();		
	}
	@Override
	public void excluirTodos(List<Marca> list) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		for (Marca marca : list) {
			entityManager.getTransaction().begin();	
			Marca marcaManged = entityManager.merge(marca);
			entityManager.remove(marcaManged);
			entityManager.getTransaction().commit();
		}
		
		entityManager.close();
		entityManagerFactory.close();
	}
}
