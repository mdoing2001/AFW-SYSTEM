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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/accountancy/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateAccountancy(@PathVariable("id") int id, @RequestBody String accountancyStr) {
		
		JSONObject result = new JSONObject();
		
		try {
			JSONObject accountancyObj = (JSONObject) new JSONParser().parse(accountancyStr);
			
			AccountancyEntity Accountancyentity  = accountancyService.findAccountancyById(id);
			
			Accountancyentity.setAccName(null != accountancyObj.get("accName") ?  accountancyObj.get("accName").toString() : null);
			Accountancyentity.setAccContact(null != accountancyObj.get("accContact") ?  accountancyObj.get("accContact").toString() : null);
			Accountancyentity.setAccPhone(null != accountancyObj.get("accPhone") ?  accountancyObj.get("accPhone").toString() : null);
			Accountancyentity.setAccAddress(null != accountancyObj.get("accAddress") ?  accountancyObj.get("accAddress").toString() : null);
			
			
			accountancyService.update(Accountancyentity);
			
			result.put("status", "success");
			result.put("message", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
	    	result.put("message", "update error");
		}
		
		
		return result.toJSONString();
	} 
	 
}
