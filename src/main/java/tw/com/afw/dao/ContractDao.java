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
		return (List<ContractEntity>) em.createQuery("SELECT e FROM ContractEntity e where e.contractDel !='*' ", ContractEntity.class).getResultList();
	}
	
	
	public List<ContractEntity> findContractByCompany(int companyId) {
		return em.createQuery("select e from ContractEntity e where e.companyId.companyId = :companyId",ContractEntity.class).setParameter("companyId", companyId).getResultList();
	
	}
	
	public List<ContractEntity> findContractByType(String contractType) {
		return em.createQuery("select e from contract e where e.contract_type:contractType",ContractEntity.class).setParameter("contractType", contractType).getResultList();
	
	}
	
	public List<ContractEntity> findContractByTypeId(String contractType,int id) {
		return em.createQuery("select e from contract e where e.contract_type:contractType and e.company_id:id",ContractEntity.class).setParameter("contractType", contractType).setParameter("id", id).getResultList();
	
	}
	
	public ContractEntity findContractById(Integer companyId) {
		ContractEntity entity = null;
		try{
			entity = (ContractEntity) em.find(ContractEntity.class, companyId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	
    public void ins(ContractEntity contract) {
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
	
	//搜尋合約by公司類型
	@SuppressWarnings("unchecked")
	public List<ContractEntity> findContractByCompanyIdAndCompanyType(Integer id, String type) {
		List<ContractEntity> entity = null;
		
		try {
			Query query = em.createQuery("select c from ContractEntity c where c.companyId.companyId = :ID AND c.contractType = :TYPE", ContractEntity.class);
			query.setParameter("ID", id);
			query.setParameter("TYPE", type);
			entity = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	

}
