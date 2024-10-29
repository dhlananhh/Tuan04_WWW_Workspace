package daoImpl;


import java.util.List;

import dao.ProductDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.Product;


@SuppressWarnings("unchecked")
public class ProductDAOImpl implements ProductDAO {
	private EntityManager entityManager;

	
	/**
	 * Constructor
	 */
	public ProductDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	/**
	 * Add a new product
	 */
	@Override
	public Product addProduct(Product product) {
		EntityTransaction transaction = null;
		
		try {
			transaction = this.entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(product);
			transaction.commit();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
		
		return null;
	}

	
	/**
	 * Update an existing product
	 */
	@Override
	public Product updateProduct(Product product) {
		EntityTransaction transaction = null;
		
		try {
			transaction = this.entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(product);
			transaction.commit();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			
			if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}
		}
		
		return null;
	}

	
	/**
	 * Delete a product
	 */
	@Override
	public boolean deleteProduct(int productId) {
		EntityTransaction transaction = null;
		
		try {
			transaction = this.entityManager.getTransaction();
			transaction.begin();
			Product product = entityManager.find(Product.class, productId);
			if (product != null) {
				entityManager.remove(entityManager.contains(product) ? product : entityManager.merge(product));
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}
		}
		
		return false;
	}

	
	/**
	 * Get a product
	 */
	@Override
	public Product getProductById(int productId) {
		try {
			Product product = entityManager.find(Product.class, productId);
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * Get list of all products
	 */
	@Override
	public List<Product> getAllProducts() {
		try {
			return entityManager.createQuery("FROM Product").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
