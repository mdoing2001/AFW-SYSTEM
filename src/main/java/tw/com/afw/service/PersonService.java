package tw.com.afw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.afw.dao.BranchDao;
import tw.com.afw.dao.PersonDao;
import tw.com.afw.entity.BranchEntity;
import tw.com.afw.entity.PersonEntity;

@Service
public class PersonService {
	
	
	@Autowired
	private PersonDao personDao;
	
	
	@Transactional
	public  List<PersonEntity>findAll(){
		return personDao.findAll();
	}
	
	@Transactional
	public PersonEntity findBranchById(Integer id){
		return personDao.findPersonById(id);
	}
	
	@Transactional
	public  void ins(PersonEntity person){
		personDao.ins(person);
	}
	
	@Transactional
	public  void update(PersonEntity person){
		personDao.update(person);
	}
	
	
	@Transactional
	public  void del(PersonEntity person){
		personDao.delete(person);
	}

}
