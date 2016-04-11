package tw.com.afw.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.afw.dao.CompanyDao;
import tw.com.afw.dao.ContractDao;
import tw.com.afw.dao.UserDao;
import tw.com.afw.entity.CompanyEntity;
import tw.com.afw.entity.ContractEntity;
import tw.com.afw.entity.UserEntity;





@Service
public class CompanyService {
	
	@Autowired
	private CompanyDao dao;
	
	@Autowired
	private ContractDao contractDao;
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public  List<CompanyEntity>findAll(){
		return dao.findAll();
	}
	
	@Transactional
	public  List<ContractEntity>findContractByCompany(int companyId){
		return contractDao.findContractByCompany(companyId);
	}
	
	@Transactional
	public  List<ContractEntity>findContractByType(String companyType){
		return contractDao.findContractByType(companyType);
	}

	@Transactional
	public  List<ContractEntity>findContractByTypeId(String companyType,int id){
		return contractDao.findContractByTypeId(companyType,id);
	}
	
	@Transactional
	public  List<CompanyEntity>findCompanyByCode(String code){
		return dao.findCompanyByCode(code);
	}
	
	@Transactional
	public  CompanyEntity findCompanyById(Integer id){
		return dao.findCompanyById(id);
	}
	
	
	@Transactional
	public CompanyEntity findCompanyByEin(String ein){
		return dao.findCompanyByEin(ein);
	}
	
	@Transactional
	public CompanyEntity comByNumber(String number){
		return dao.findCompanyByEin(number);
	}
	
	@Transactional
	public Integer ins(CompanyEntity company) {
		return dao.insert(company);
	}
	

	@Transactional
	public void upd(CompanyEntity company){
		dao.update(company);
	}
	
	
	@Transactional
	public void del(CompanyEntity company){
		dao.delete(company);
	}
	
	
	@Transactional
	public void deleteId(int del_id){
		dao.deleteById(del_id);
	}
	

	
	@Transactional
	public int userId (String usercount){
		
		UserEntity user  = userDao.findUserByCount(usercount);
		int userid= user.getUserId();		
		return userid;	
		
	}
	
	@Transactional
	public int checkEin(String ein){
		
		CompanyEntity entity  = dao.findCompanyByEin(ein);
		
		if(entity == null){
			return 1;
		}else{
			return 0;
		}
	}
	

}
