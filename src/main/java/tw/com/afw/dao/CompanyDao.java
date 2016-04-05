package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import tw.com.afw.entity.CompanyEntity;






@Repository
public class CompanyDao {

	
	@PersistenceContext
	private EntityManager em;
	

	
	public List<CompanyEntity> findAll() {
		return (List<CompanyEntity>) em.createQuery("select c from CompanyEntity c ", CompanyEntity.class).getResultList();
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
	

	public List<CompanyEntity> findCompanyByCode(String code) {
		List<CompanyEntity> results = null;
		try {
			results = em.createQuery("select c from CompanyEntity c where c.companyCode = :code",CompanyEntity.class)
					.setParameter("code", code).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public CompanyEntity findCompanyByEin(String ein) {
		CompanyEntity entity = null;
		try{
			Query query = em.createQuery("select c from CompanyEntity c where c.companyEin = :ein", CompanyEntity.class);
			query.setParameter("ein", ein);
            entity = (CompanyEntity) query.getSingleResult();
        } catch (NoResultException e) {
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
		
		
    public void insert(CompanyEntity company) {
		try {
			em.getTransaction().begin();
			em.persist(company);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	//@Query("SELECT u FROM User u WHERE u.userName=:userName")
	//  public User findUserByName(@Param("userName") String userName);
	
}
