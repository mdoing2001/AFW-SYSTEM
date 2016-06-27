package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tw.com.afw.entity.BranchEntity;


@Repository
public class BranchDao {

	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<BranchEntity> findAll() {
		return (List<BranchEntity>) em.createQuery("select e from BranchEntity e", BranchEntity.class).getResultList();
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
	
	public BranchEntity findBranchByBranchCode(String code) {
		if(code == null) {
			return null;
		}
		BranchEntity entity = null;
		try{
			entity = (BranchEntity) em.createQuery("select b from BranchEntity b where b.branchCode = :branchCode", BranchEntity.class)
					.setParameter("branchCode", code).getSingleResult();
		} catch (NoResultException e) {
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
