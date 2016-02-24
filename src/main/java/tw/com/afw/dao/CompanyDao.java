package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tw.com.afw.entity.CompanyEntity;
import tw.com.afw.entity.UserEntity;





@Repository
public class CompanyDao {

	
	@PersistenceContext
	private EntityManager em;
	
	public void persist(CompanyEntity company) {
		try {
			em.getTransaction().begin();
			em.persist(company);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public List<CompanyEntity> findAll() {
		return (List<CompanyEntity>) em.createQuery("SELECT * FROM company ", CompanyEntity.class).getResultList();
	}
	
	
	public CompanyEntity findCompanyById(Integer companyId) {
		CompanyEntity entity = null;
		try{
			entity = (CompanyEntity) em.find(CompanyEntity.class, companyId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	public CompanyEntity findCompanyByEin(String ein) {
		CompanyEntity entity = null;
		try{
			entity = (CompanyEntity) em.find(CompanyEntity.class, ein);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	public CompanyEntity findCompanyByNumber(String number) {
		CompanyEntity entity = null;
		try{
			entity = (CompanyEntity) em.find(CompanyEntity.class, number);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
		
		
	public void update(CompanyEntity company) {
		try {
			em.getTransaction().begin();
			em.merge(company);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(CompanyEntity company) {
		try {
			em.getTransaction().begin();
			em.remove(company);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(Integer companyId) {
		try {
			em.getTransaction().begin();
			CompanyEntity company = this.findCompanyById(companyId);
			em.remove(company);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
