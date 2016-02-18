package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tw.com.afw.entity.UserEntity;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager em;
	 
	public void persist(UserEntity user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public List<UserEntity> findAll() {
		return (List<UserEntity>) em.createQuery("SELECT e FROM User e", UserEntity.class).getResultList();
	}
	 
	public UserEntity findUserById(Integer userId) {
		UserEntity entity = null;
		try{
			entity = (UserEntity) em.find(UserEntity.class, userId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
	}
	
	public void update(UserEntity user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(UserEntity user) {
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(Integer userId) {
		try {
			em.getTransaction().begin();
			UserEntity user = this.findUserById(userId);
			em.remove(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
}
