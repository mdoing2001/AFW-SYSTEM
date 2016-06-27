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
	public  List<RentEntity>findAll() {
		return rentDao.findAll();
	}
	
	@Transactional
	public RentEntity findRentById(Integer id) {
		return rentDao.findRentById(id);
	}
	
	@Transactional
	public List<RentEntity> findRentByContractIdAndYear(Integer id, Integer year) {
		return rentDao.findRentByContractIdAndYear(id, year);
	}
	
	@Transactional
	public RentEntity findRentByYearAndMonthContractId(Integer id,Integer year, Integer month) {
		return rentDao.findRentByYearAndMonthContractId(id, year, month);
	}
	
	@Transactional
	public void ins(RentEntity rent) {
		rentDao.insert(rent);
	}
	
	@Transactional
	public void update(RentEntity rent) {
		rentDao.update(rent);
	}
	
	
	@Transactional
	public void del(RentEntity rent) {
		rentDao.delete(rent);
	}
	
	@Transactional
	public Double allRentPrice(Integer id, Integer year, Integer month) {
		Double result = 0.00;
		RentEntity entity = rentDao.findRentByYearAndMonthContractId(id, year, month);
		if(entity != null) {
			Double managenment = null != entity.getRentManagement() ? entity.getRentManagement() : 0.00;
			Double power = null != entity.getRentPower() ? entity.getRentPower() : 0.00;
			Double adsl = null != entity.getRentAdsl() ? entity.getRentAdsl() : 0.00;
			Double business = null != entity.getRentBusiness() ? entity.getRentBusiness() : 0.00;
			Double other = null != entity.getRentOther() ? entity.getRentOther() : 0.00;
			result = managenment + power + adsl + business + other;
		}
		return result;
	}

}
