package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import entity.CompanyEntity;
import entity.UserEntity;

@Repository
public class CompanyDao {
	
	@PersistenceContext
	private EntityManager em;
	 
	public void persist(CompanyEntity Company) {
        em.persist(Company);
    }
	

	public List<CompanyEntity> findAll(){
		return (List<CompanyEntity>) em.createQuery("SELECT * FROM company ", CompanyEntity.class).getResultList();
	}
	
	
	
}
