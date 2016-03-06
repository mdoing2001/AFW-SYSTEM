package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.User;
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
	
	public UserEntity findUserByCount(String userCount) {
		UserEntity entity = null;
		try{
			entity = (UserEntity) em.find(UserEntity.class, userCount);
			 
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
	
	public UserEntity findUserByCoun_password(String account , String password){
		UserEntity entity = null;
		
		try {
			entity = (UserEntity) em.createQuery("select * from user where user_count =:user_count and user_password=:user_password",User.class);
			entity.setUser_Count(account);
			entity.setUser_Password(password);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return entity;
	}
	 
}
