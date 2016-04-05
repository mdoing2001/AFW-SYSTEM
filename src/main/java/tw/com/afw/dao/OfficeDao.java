package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tw.com.afw.entity.CompanyEntity;
import tw.com.afw.entity.OfficeEntity;

@Repository
public class OfficeDao {
	
	
	@PersistenceContext
	private EntityManager em;
	
	public List<OfficeEntity> findAll() {
		return (List<OfficeEntity>) em.createQuery("select e from OfficeEntity e", OfficeEntity.class).getResultList();
	}
	
	
	public OfficeEntity findOffByContractId(int cid) {
		OfficeEntity result = null;
		try {
			result = em.createQuery("select e from OfficeEntity e where e.contractId.contractId = :cid ", OfficeEntity.class).setParameter("cid", cid).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public OfficeEntity findOfficeById(Integer fid) {
			OfficeEntity entity = null;
			try{
				entity = (OfficeEntity) em.find(OfficeEntity.class, fid);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return entity;
	}
	
	
	public void ins(OfficeEntity office) {
		try {
			//em.getTransaction().begin();
			em.persist(office);
			//em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
		
	
	public void update(OfficeEntity office) {
		try {
			em.getTransaction().begin();
			em.merge(office);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(OfficeEntity office) {
		try {
			em.getTransaction().begin();
			em.remove(office);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	

}
