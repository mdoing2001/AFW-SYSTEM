package tw.com.afw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.afw.dao.OfficeDao;

import tw.com.afw.entity.OfficeEntity;

@Service
public class OfficeService {
	
	@Autowired
	private OfficeDao officeDao;
	
	
	
	@Transactional
	public  List<OfficeEntity>findAll(){
		return officeDao.findAll();
	}
	
	@Transactional
	public OfficeEntity findOfficeById(Integer id){
		return officeDao.findOfficeById(id);
	}
	
	
	@Transactional
	public  void ins(OfficeEntity office){
		officeDao.ins(office);
	}
	
	@Transactional
	public  void update(OfficeEntity office){
		officeDao.update(office);
	}
	
	@Transactional
	public  void del(OfficeEntity office){
		officeDao.delete(office);
	}
	
	@Transactional
	public OfficeEntity findOffByContract(int cid){
	  return officeDao.findOffByContractId(cid);
	}
	
}
