package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import tw.com.afw.entity.RentEntity;


@Repository
public class RentDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<RentEntity> findAll() {
		return (List<RentEntity>) em.createQuery("select c from rent c ", RentEntity.class).getResultList();
	}
	
	
	public RentEntity findRentById(Integer id) {
		RentEntity entity = null;
		try{
			entity = (RentEntity) em.find(RentEntity.class, id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	
    public void insert(RentEntity rent) {
		try {
			em.getTransaction().begin();
			em.persist(rent);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(RentEntity rent) {
		try {
			em.getTransaction().begin();
			em.merge(rent);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(RentEntity rent) {
		try {
			em.getTransaction().begin();
			em.remove(rent);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
