package dao;

import java.util.List;

import entity.AccountEntity;

public interface AccountDao {
	public List<AccountEntity> findAll();
	
	public AccountEntity findById(Long id);
	
	public Boolean insert(AccountEntity accountEntity);
	
	public Boolean update(AccountEntity accountEntity);

}
