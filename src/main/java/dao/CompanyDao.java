package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import entity.CompanyEntity;


@Repository
public class CompanyDao {
	



	@PersistenceContext
	private EntityManager em;

	 
	public void persist(CompanyEntity Company) {
        em.persist(Company);
    }
	

	public List<CompanyEntity> compAll(){
		return (List<CompanyEntity>) em.createQuery("SELECT * FROM company ", CompanyEntity.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyEntity> compByEin(int Ein) {
	    return (List<CompanyEntity>) em.createQuery("from company  where COMPANY_EIN = :Ein").getSingleResult();
		
	}
	

	@SuppressWarnings("unchecked")
	public List<CompanyEntity> compByPhome(int phome) {
		return (List<CompanyEntity>) em.createQuery("from company  where CONTACT_NUMBER = :phome").getSingleResult();
		
	}
	
	
	public int updateUserCoin(Long userId, Long coin) {
		Query query = getSession().createQuery("update UserEntity set COINS = :coin where ID = :userId");
		query.setLong("coin",coin);
        query.setLong("userId",userId);
        return query.executeUpdate();
	}



	
}
