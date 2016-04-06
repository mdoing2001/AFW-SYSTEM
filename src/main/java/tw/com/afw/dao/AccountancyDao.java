package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tw.com.afw.entity.AccountancyEntity;




@Repository
public class AccountancyDao {

	@PersistenceContext
	private EntityManager em;   
	
	
	public List<AccountancyEntity> findAll() {
		return (List<AccountancyEntity>) em.createQuery("SELECT e FROM accountancy e", AccountancyEntity.class).getResultList();
	}
	
	
	public AccountancyEntity findAccountancyById(Integer accId) {
		AccountancyEntity entity = null;
		try{
			entity = (AccountancyEntity) em.find(AccountancyEntity.class, accId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	
	public void ins(AccountancyEntity accountancy) {
		try {
			em.getTransaction().begin();
			em.persist(accountancy);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
	public void update(AccountancyEntity accountancy) {
		try {
			//em.getTransaction().begin();
			em.merge(accountancy);
			//em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(AccountancyEntity accountancy) {
		try {
			em.getTransaction().begin();
			em.remove(accountancy);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
}
