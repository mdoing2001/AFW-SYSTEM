package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tw.com.afw.entity.PersonEntity;


@Repository
public class PersonDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<PersonEntity> findAll() {
		return (List<PersonEntity>) em.createQuery("select e from person e", PersonEntity.class).getResultList();
	}
	
	
	
	public PersonEntity findPersonById(Integer bid) {
		PersonEntity entity = null;
		try{
			entity = (PersonEntity) em.find(PersonEntity.class, bid);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	
	public void ins(PersonEntity person) {
		try {
			em.getTransaction().begin();
			em.persist(person);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
	public void update(PersonEntity person) {
		try {
			em.getTransaction().begin();
			em.merge(person);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(PersonEntity person) {
		try {
			em.getTransaction().begin();
			em.remove(person);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
