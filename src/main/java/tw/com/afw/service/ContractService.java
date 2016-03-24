package tw.com.afw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.afw.dao.ContractDao;
import tw.com.afw.entity.ContractEntity;
import tw.com.afw.entity.OfficeEntity;



@Service
public class ContractService {
	
	
	@Autowired
	private ContractDao contractDao;
	
	
	
	@Transactional
	public  List<ContractEntity>findAll(){
		return contractDao.findAll();
	}
	
	
	
	@Transactional
	public ContractEntity findOfficeById(Integer id){
		return contractDao.findContractById(id);
	}
	
	@Transactional
	public  void ins(ContractEntity contract){
		contractDao.ins(contract);
	}
	
	
	@Transactional
	public  void update(ContractEntity contract){
		contractDao.update(contract);
	}
	
	@Transactional
	public  void del(int contract){
		contractDao.delete(contract);
	}
	
	

}
