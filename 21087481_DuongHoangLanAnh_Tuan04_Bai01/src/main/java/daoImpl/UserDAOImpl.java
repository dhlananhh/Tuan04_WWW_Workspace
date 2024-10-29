package daoImpl;


import java.util.List;
import dao.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.User;


@SuppressWarnings("unchecked")
public class UserDAOImpl implements UserDAO {
	private EntityManager entityManager;
	
	
	/**
	 * Constructor
	 */
	public UserDAOImpl (EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	/**
	 * Insert a new user into database
	 */
	@Override
	public User insertUser (User user) {
		EntityTransaction transaction = null;
		
		try {
			transaction = this.entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(user);
			transaction.commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			
			if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}
		}
		
		return null;
	}

	
	/**
	 * Update an existing user in the database
	 */
	@Override
	public User updateUser (User user) {
		EntityTransaction transaction = null;
		
		try {
			transaction = this.entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(user);
			transaction.commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			
			if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}
		}
		
		return null;
	}

	
	/**
	 * Delete a user from database
	 */
	@Override
	public boolean deleteUser (int id) {
		EntityTransaction transaction = null;
		
		try {
			transaction = this.entityManager.getTransaction();
			transaction.begin();
			User user = entityManager.find(User.class, id);
			if (user != null) {
				entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
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
	 * Get all users from database
	 */
	@Override
	public List<User> getAllUsers() {
		try {
			return entityManager.createQuery("from User").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
     * Get user by id
	 */
	@Override
	public User getUserById(int id) {
		try {
			User user = entityManager.find(User.class, id);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
