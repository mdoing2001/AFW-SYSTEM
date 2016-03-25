package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tw.com.afw.entity.RemitEntity;


@Repository
public class RemitDao {
	
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<RemitEntity> findAll() {
		return (List<RemitEntity>) em.createQuery("select e from remit e", RemitEntity.class).getResultList();
	}
	
	
	
	public RemitEntity findPersonById(Integer id) {
		RemitEntity entity = null;
		try{
			entity = (RemitEntity) em.find(RemitEntity.class, id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	
	
	public void ins(RemitEntity remit) {
		try {
			em.getTransaction().begin();
			em.persist(remit);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
	public void update(RemitEntity remit) {
		try {
			em.getTransaction().begin();
			em.merge(remit);
			em.getTransaction().commit();
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
	
	

}
