package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import tw.com.afw.entity.BranchEntity;


@Repository
public class BranchDao {

	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<BranchEntity> findAll() {
		return (List<BranchEntity>) em.createQuery("select e from branch e", BranchEntity.class).getResultList();
	}
	
	
	public BranchEntity findBranchById(Integer bid) {
		BranchEntity entity = null;
		try{
			entity = (BranchEntity) em.find(BranchEntity.class, bid);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	
	public void ins(BranchEntity branch) {
		try {
			em.getTransaction().begin();
			em.persist(branch);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void update(BranchEntity branch) {
		try {
			em.getTransaction().begin();
			em.merge(branch);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(BranchEntity branch) {
		try {
			em.getTransaction().begin();
			em.remove(branch);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
