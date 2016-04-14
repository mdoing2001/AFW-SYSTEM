package tw.com.afw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.afw.dao.RentDao;
import tw.com.afw.entity.RentEntity;

@Service
public class RentService {
	
	@Autowired
	private RentDao rentDao;
	
	@Transactional
	public  List<RentEntity>findAll(){
		return rentDao.findAll();
	}
	
	
	@Transactional
	public RentEntity findRentById(Integer id){
		return rentDao.findRentById(id);
	}
	
	@Transactional
	public void ins(RentEntity rent){
		rentDao.insert(rent);
	}
	
	@Transactional
	public void update(RentEntity rent){
		rentDao.update(rent);
	}
	
	
	@Transactional
	public void del(RentEntity rent){
		rentDao.delete(rent);
	}

}
