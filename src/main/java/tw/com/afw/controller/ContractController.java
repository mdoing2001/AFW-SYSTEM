package tw.com.afw.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tw.com.afw.service.ContractService;


@RestController
public class ContractController {

	
	
	@Autowired
	private ContractService ContractService;
	 
	 
	
	@RequestMapping(value = "/new/contract/add/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createContract(@PathVariable("id") long id, @RequestBody String newContractStr) {
		//參考createCompany那隻
		
		
		return "";
	}
	
	//先用contract 的 id把contract撈出來 再去set(前端不確定是改哪一個 所以全部都要set)
	@RequestMapping(value = "/contract/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateContract(@PathVariable("id") long id, @RequestBody String ContractStr) {
		//step1 id撈contract
		
		//step2 contractStr 轉乘jsonobject
		
		//step3 jsonobject去get所有的值(參考createCompany那隻)在set進去step1的contract
		
		//step4 merge
		
		//.....
		return "";
	}

	//annotation麻煩你在設定 這隻就用contractId去撈officeEntity 再去 set officeType就可以 不用全部set
	public String updateOfficeTypeByContractId() {
		
		return "";
	}
	
	//annotation麻煩你在設定 這隻就用contractId去撈contractEntity(介紹人) 再去 set user_id2就可以 因為我會直接給你user的id 所以就不用撈userEntity
	public String updateUserId2ByContractId() {
		return "";
	}
	 
	 
	 
	 
	 
}
