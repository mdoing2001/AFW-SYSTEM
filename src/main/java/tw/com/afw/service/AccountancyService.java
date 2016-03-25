package tw.com.afw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.afw.dao.AccountancyDao;
import tw.com.afw.entity.AccountancyEntity;



@Service
public class AccountancyService {

	
	@Autowired
	private AccountancyDao accountancyDao;
	
	
	@Transactional
	public  List<AccountancyEntity>findAll(){
		return accountancyDao.findAll();
	}
	
	
	@Transactional
	public AccountancyEntity findAccountancyById(Integer id){
		return accountancyDao.findAccountancyById(id);
	}
	
	@Transactional
	public  void ins(AccountancyEntity rent){
		accountancyDao.ins(rent);
	}
	
	@Transactional
	public  void update(AccountancyEntity rent){
		accountancyDao.update(rent);
	}
	
	
	@Transactional
	public  void del(AccountancyEntity rent){
		accountancyDao.delete(rent);
	}
}
