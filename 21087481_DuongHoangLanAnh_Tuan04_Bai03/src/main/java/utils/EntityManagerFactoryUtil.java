package utils;


import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EntityManagerFactoryUtil {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	
	public EntityManagerFactoryUtil() {
		entityManagerFactory = Persistence.createEntityManagerFactory("quanlytintuc");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
}
