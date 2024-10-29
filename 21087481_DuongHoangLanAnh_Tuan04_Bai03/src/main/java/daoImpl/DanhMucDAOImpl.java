package daoImpl;


import java.util.List;

import dao.DanhMucDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.DanhMuc;


@SuppressWarnings("unchecked")
public class DanhMucDAOImpl implements DanhMucDAO {
	private EntityManager entityManager;
	
	
	/**
	 * Constructor
	 */
	public DanhMucDAOImpl (EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	/**
	 * Them danh muc
	 */
	@Override
	public DanhMuc themDanhMuc (DanhMuc dm) {
		EntityTransaction transaction = null;
		
		try {
			transaction = this.entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(dm);
			transaction.commit();
			return dm;
		} catch (Exception e) {
			e.printStackTrace();
			
			if (transaction != null && transaction.isActive()) {
                transaction.rollback();
			}
		}
		
		return null;
	}


	/**
	 * Cap nhat danh muc
	 */
	@Override
	public DanhMuc capNhatDanhMuc (DanhMuc dm) {
		EntityTransaction transaction = null;
		
		try {
			transaction = this.entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(dm);
			transaction.commit();
			return dm;
		} catch (Exception e) {
			e.printStackTrace();

			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
		
		return null;
	}


	@Override
	public boolean xoaDanhMuc (int ma) {
		EntityTransaction transaction = null;
		
		try {
            transaction = this.entityManager.getTransaction();
            transaction.begin();
            DanhMuc dm = this.entityManager.find(DanhMuc.class, ma);
			if (dm != null) {
				this.entityManager.remove(entityManager.contains(dm) ? dm : entityManager.merge(dm));
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


	@Override
	public List<DanhMuc> layDanhSachDanhMuc() {
		try {
			return this.entityManager.createQuery("FROM DanhMuc").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public DanhMuc timDanhMucTheoMa (int ma) {
		try {
            return this.entityManager.find(DanhMuc.class, ma);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return null;
	}
}
