package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import entity.UserEntity;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager em;
	 
	public void persist(UserEntity user) {
        em.persist(user);
    }
	
	public List<UserEntity> findAll(){
		return (List<UserEntity>) em.createQuery("SELECT e FROM User e", UserEntity.class).getResultList();
	}
	 
	 
}
