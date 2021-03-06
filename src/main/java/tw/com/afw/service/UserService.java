package tw.com.afw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.afw.dao.UserDao;
import tw.com.afw.entity.BranchEntity;
import tw.com.afw.entity.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserDao userdao;
	
	@Transactional
	public List<UserEntity> findAll(){
		return userdao.findAll();
	}
	
	@Transactional
	public UserEntity checkAccount(String account){
	  UserEntity entityaccount=userdao.findUserByCount(account);
	    return entityaccount;
	 }
	  
	@Transactional
	public UserEntity checkpassword(String account , String password){
		 UserEntity entitypassword=userdao.findUserByCount(account);
         return entitypassword;
	
	}
	
	@Transactional
	public  void ins(UserEntity user){
		userdao.persist(user);;
	}
	
	
	@Transactional
	public  void update(UserEntity user){
		userdao.update(user);;
	}
	
	
	@Transactional
	public  void del(UserEntity user){
		userdao.delete(user);
	}
	
	@Transactional
	public  UserEntity findUserById(Integer userId){
		return userdao.findUserById(userId);
	}
	
	
}
