package tw.com.afw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.afw.dao.RemitDao;
import tw.com.afw.entity.RemitEntity;


@Service
public class RemitService {
	
	
	@Autowired
	private RemitDao remitDao;
	
	@Transactional
	public  List<RemitEntity>findAll(){
		return remitDao.findAll();
	}
	
	@Transactional
	public RemitEntity findRemitById(Integer id){
		return remitDao.findRemitById(id);
	}
	
	@Transactional
	public  void ins(RemitEntity remit){
		remitDao.ins(remit);
	}
	
	@Transactional
	public  void update(RemitEntity remit){
		remitDao.update(remit);
	}
	
	@Transactional
	public  void del(RemitEntity remit){
		remitDao.delete(remit);
	}

	@Transactional
	public List<RemitEntity> findRemitByCompanyId(Integer companyId) {
		return remitDao.findRemitByCompanyId(companyId);
	}
	
	@Transactional
	public RemitEntity findRemitByAccount5(Integer account5) {
		return remitDao.findRemitByAccount5(account5);
	}
	
}
