package tw.com.afw.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;














import tw.com.afw.dao.CompanyDao;
import tw.com.afw.entity.CompanyEntity;





@Service
public class CompanyService {
	
	
	
	@Autowired
	private CompanyDao dao;
	
	
	@Transactional
	public  List<CompanyEntity>comAll(){
		return dao.findAll();
	}
	
	
	@Transactional
	public  CompanyEntity comById(Integer id){
		return   dao.findCompanyById(id);
	}
	
	
	@Transactional
	public  CompanyEntity comByEin(String ein){
		return   dao.findCompanyByEin(ein);
	}
	
	@Transactional
	public  CompanyEntity comByNumber(String number){
		return   dao.findCompanyByEin(number);
	}
	
	@Transactional
	public  void ins(CompanyEntity company){
		dao.insert(company);
	}
	

	@Transactional
	public  void upd(CompanyEntity company){
		dao.update(company);
	}
	
	
	@Transactional
	public  void del(CompanyEntity company){
		dao.delete(company);
	}
	
	
	@Transactional
	public  void deleteId(int del_id){
		dao.deleteById(del_id);
	}
	


}
