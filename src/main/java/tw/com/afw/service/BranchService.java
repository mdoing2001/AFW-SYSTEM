package tw.com.afw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.afw.dao.BranchDao;
import tw.com.afw.entity.BranchEntity;



@Service
public class BranchService {
	
	@Autowired
	private BranchDao branchDao;
	
	
	@Transactional
	public  List<BranchEntity>findAll(){
		return branchDao.findAll();
	}
	
	
	@Transactional
	public BranchEntity findBranchById(Integer id){
		return branchDao.findBranchById(id);
	}
	
	@Transactional
	public  void ins(BranchEntity branch){
		branchDao.ins(branch);
	}
	
	@Transactional
	public  void update(BranchEntity branch){
		branchDao.update(branch);
	}
	
	
	@Transactional
	public  void del(BranchEntity branch){
		branchDao.delete(branch);
	}
	
	

}
