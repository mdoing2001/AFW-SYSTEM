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
	public List<CompanyEntity> findAll(){

		return dao.findAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	


}
