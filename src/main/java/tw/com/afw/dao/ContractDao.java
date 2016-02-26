package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


import tw.com.afw.entity.ContractEntity;

@Repository
public class ContractDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<ContractEntity> findAll() {
		return (List<ContractEntity>) em.createQuery("SELECT * FROM contract where contract is not null ", ContractEntity.class).getResultList();
	}
	
	
	public ContractEntity findCompanyById(Integer companyId) {
		ContractEntity entity = null;
		try{
			entity = (ContractEntity) em.find(ContractEntity.class, companyId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	
    public void insert(ContractEntity contract) {
		try {
			em.getTransaction().begin();
			em.persist(contract);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	public void update(ContractEntity contract) {
		try {
			em.getTransaction().begin();
			em.merge(contract);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			//em.getTransaction().begin();
					
			Query query = em.createQuery("update contract set contract_del ='*' where ID = :id");
	        query.setHint("id",id);
	        query.executeUpdate();
			
			//em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
