package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tw.com.afw.entity.RemitEntity;


@Repository
public class RemitDao {
	
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<RemitEntity> findAll() {
		return (List<RemitEntity>) em.createQuery("select e from RemitEntity e", RemitEntity.class).getResultList();
	}
	
	public RemitEntity findRemitById(Integer id) {
		RemitEntity entity = null;
		try{
			entity = (RemitEntity) em.find(RemitEntity.class, id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	public List<RemitEntity> findRemitByCompanyId(Integer companyId) {
		List<RemitEntity> results = null;
		try{
			results = em.createQuery("select r from RemitEntity r where r.companyId.companyId = :companyId", RemitEntity.class)
					.setParameter("companyId", companyId).getResultList();
            
        } catch (NoResultException e) {
            e.printStackTrace();
        }
		return results;
	}
	
	public void ins(RemitEntity remit) {
		try {
			//em.getTransaction().begin();
			em.persist(remit);
			//em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
	public void update(RemitEntity remit) {
		try {
			//em.getTransaction().begin();
			em.merge(remit);
			//em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(RemitEntity remit) {
		try {
			em.getTransaction().begin();
			em.remove(remit);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RemitEntity findRemitByAccount5(Integer account5) {
		RemitEntity results = null;
		
		try{
			results = em.createQuery("select r from RemitEntity r where r.remitAccount LIKE :account5", RemitEntity.class)
					.setParameter("account5", "%"+account5).getSingleResult();
        } catch (NoResultException ne) {
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		return results;
	}
	
	

}
