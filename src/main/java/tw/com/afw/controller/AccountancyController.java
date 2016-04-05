package tw.com.afw.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tw.com.afw.entity.AccountancyEntity;
import tw.com.afw.service.AccountancyService;
import tw.com.afw.service.ContractService;


@RestController
public class AccountancyController {

	
	
	@Autowired
	private AccountancyService accountancyService;
	 
	 //test
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new/accountancy/add/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createAccountancy(@PathVariable("id") long id, @RequestBody String newAccountancyStr) {
		//參考createCompany那隻
		JSONObject result = new JSONObject();
		
		try {
			JSONObject obj = (JSONObject) new JSONParser().parse(newAccountancyStr);
			JSONObject accountancyObj = (JSONObject) obj.get("accountancy");
			
			AccountancyEntity Accountancyentity = new AccountancyEntity();
			
			Accountancyentity.setAccName(null != accountancyObj.get("accName") ?  accountancyObj.get("accName").toString() : null);
			Accountancyentity.setAccContact(null != accountancyObj.get("accContact") ?  accountancyObj.get("accContact").toString() : null);
			Accountancyentity.setAccPhone(null != accountancyObj.get("accPhone") ?  accountancyObj.get("accPhone").toString() : null);
			Accountancyentity.setAccAddress(null != accountancyObj.get("accAddress") ?  accountancyObj.get("accAddress").toString() : null);
			
			accountancyService.ins(Accountancyentity);
			
			result.put("status", "success");
			result.put("message", "success");
		} catch (Exception e) {
			result.put("status", "error");
	    	result.put("message", "insert error");
		}
		
		
		
		return result.toJSONString();
	}
	
	//先用accountancy 的 id把accountancy撈出來 再去set(前端不確定是改哪一個 所以全部都要set)
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/accountancy/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateAccountancy(@PathVariable("id") long id, @RequestBody String accountancyStr) {
		//step1 id撈accountancy
		
		//step2 accountancyStr 轉乘jsonobject
		
		//step3 jsonobject去get所有的值(參考createCompany那隻)在set進去step1的accountancy
		
		//step4 merge
		
		//.....
		
		JSONObject result = new JSONObject();
		
		try {
			JSONObject obj = (JSONObject) new JSONParser().parse(accountancyStr);
			JSONObject accountancyObj = (JSONObject) obj.get("accountancy");
			
			AccountancyEntity Accountancyentity  = accountancyService.findAccountancyById(Integer.parseInt(accountancyObj.get("accId").toString()));
			
			Accountancyentity.setAccName(null != accountancyObj.get("accName") ?  accountancyObj.get("accName").toString() : null);
			Accountancyentity.setAccContact(null != accountancyObj.get("accContact") ?  accountancyObj.get("accContact").toString() : null);
			Accountancyentity.setAccPhone(null != accountancyObj.get("accPhone") ?  accountancyObj.get("accPhone").toString() : null);
			Accountancyentity.setAccAddress(null != accountancyObj.get("accAddress") ?  accountancyObj.get("accAddress").toString() : null);
			
			
			accountancyService.update(Accountancyentity);
			
			result.put("status", "success");
			result.put("message", "success");
			
		} catch (Exception e) {
			result.put("status", "error");
	    	result.put("message", "update error");
		}
		
		
		return result.toJSONString();
	} 
	 
}
