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
	
	
	
	
}
