package tw.com.afw.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tw.com.afw.service.AccountancyService;
import tw.com.afw.service.ContractService;


@RestController
public class AccountancyController {

	
	
	@Autowired
	private AccountancyService accountancyService;
	 
	 
	
	@RequestMapping(value = "/new/accountancy/add/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createAccountancy(@PathVariable("id") long id, @RequestBody String newAccountancyStr) {
		//參考createCompany那隻
		
		
		return "";
	}
	
	//先用accountancy 的 id把accountancy撈出來 再去set(前端不確定是改哪一個 所以全部都要set)
	@RequestMapping(value = "/accountancy/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateAccountancy(@PathVariable("id") long id, @RequestBody String accountancyStr) {
		//step1 id撈accountancy
		
		//step2 accountancyStr 轉乘jsonobject
		
		//step3 jsonobject去get所有的值(參考createCompany那隻)在set進去step1的accountancy
		
		//step4 merge
		
		//.....
		return "";
	} 
	 
}
