package daoImpl;


import java.util.List;

import dao.TinTucDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.TinTuc;


@SuppressWarnings("unchecked")
public class TinTucDAOImpl implements TinTucDAO {
	private EntityManager entityManager;
	
	
	/**
	 * Constructor
	 */
	public TinTucDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	/**
	 * Them tin tuc
	 */
	@Override
	public TinTuc themTinTuc (TinTuc tt) {
		EntityTransaction transaction = null;
		
		try {
            transaction = this.entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(tt);
            transaction.commit();
            return tt;
        } catch (Exception e) {
            e.printStackTrace();
            
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
		
		return null;
	}


	/*
	 * Cap nhat tin tuc
	 */
	@Override
	public TinTuc capNhatTinTuc (TinTuc tt) {
		EntityTransaction transaction = null;
		
		try {
			transaction = this.entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(tt);
			transaction.commit();
			return tt;
		} catch (Exception e) {
			e.printStackTrace();

			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
		
		return null;
	}


	/**
	 * Xoa tin tuc
	 */
	@Override
	public boolean xoaTinTuc(int ma) {
		EntityTransaction transaction = null;
		
		try {
            transaction = this.entityManager.getTransaction();
            transaction.begin();
            TinTuc tt = entityManager.find(TinTuc.class, ma);
            entityManager.remove(tt);
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
	 * Tim tin tuc theo ma
	 */
	@Override
	public TinTuc timTinTucTheoMa (int ma) {
		try {
            TinTuc tt = this.entityManager.find(TinTuc.class, ma);
            return tt;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return null;
	}


	/**
	 * Lay danh sach tin tuc
	 */
	@Override
	public List<TinTuc> layDanhSachTinTuc() {
		try {
            return this.entityManager.createQuery("FROM TinTuc", TinTuc.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return null;
	}
	
}
