package tw.com.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	//user > userEntity 這是jpql的語法 所以不能用table的名稱 要用entity
	public List<UserEntity> findAll() {
		return (List<UserEntity>) em.createQuery("SELECT e FROM UserEntity e", UserEntity.class).getResultList();
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
	
	// 下面是有問題的code 這個寫法只能適用在findBy primary key
//	public UserEntity findUserByCount(String userCount) {
//		UserEntity entity = null;
//		try{
//			entity = (UserEntity) em.find(UserEntity.class, userCount);
//			 
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		return entity;
//	}
	public UserEntity findUserByCount(String userCount) {
		UserEntity entity = null;
		try{
			Query query = em.createQuery("select u from UserEntity u where u.user_Count =:user_count", UserEntity.class);
			query.setParameter("user_count", userCount);
			entity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
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
	//TODO need to fix the code
	public UserEntity findUserByCoun_password(String account , String password){
		UserEntity entity = null;
		
		try {
			entity = (UserEntity) em.createQuery("select * from user where user_count =:user_count and user_password=:user_password",User.class);
			entity.setUserCount(account);
			entity.setUserPassword(password);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return entity;
	}
	 
}
