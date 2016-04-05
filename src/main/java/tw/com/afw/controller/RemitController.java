package tw.com.afw.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tw.com.afw.entity.CompanyEntity;
import tw.com.afw.entity.RemitEntity;
import tw.com.afw.service.AccountancyService;
import tw.com.afw.service.CompanyService;
import tw.com.afw.service.ContractService;
import tw.com.afw.service.RemitService;


@RestController
public class RemitController {

	
	
	@Autowired
	private RemitService remitService;
	private CompanyService companyService;
	 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new/remit/add/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createRemit(@PathVariable("id") long id, @RequestBody String newRemitStr) {
		
		JSONObject result = new JSONObject();
		
		try {
			
			JSONObject obj = (JSONObject) new JSONParser().parse(newRemitStr);
			JSONObject remitObj = (JSONObject) obj.get("remit");
			
			RemitEntity Remitentity = 	new RemitEntity();
			CompanyEntity Companyentity =  new CompanyEntity();
			
			Remitentity.setRemitAccount(null != remitObj.get("remitAccount") ? remitObj.get("remitAccount").toString() : null);
			Remitentity.setRemitDeposit(null != remitObj.get("remitDeposit") ? remitObj.get("remitDeposit").toString() : null);
			Remitentity.setRemitMode(null != remitObj.get("remitMode") ? remitObj.get("remitMode").toString() : null);
			Remitentity.setCompanyId(null != remitObj.get("companyId") ? companyService.findCompanyById(Integer.parseInt(remitObj.get("companyId").toString())) : null);
			
			Remitentity.setCompanyId(Companyentity);
			
			remitService.ins(Remitentity);
			result.put("status", "success");
			result.put("message", "success");
			
			
		} catch (Exception e) {
			result.put("status", "error");
	    	result.put("message", "insert error");
		}
		
		
		
		return result.toJSONString();
	}
	
	//利用company id 去 撈 remitEntity 再去set全部的值
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/remit/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateRemit(@PathVariable("id") long id, @RequestBody String remitStr) {
		//step1 id撈remitEntity
		
		//step2 remitStr 轉乘jsonobject
		
		//step3 jsonobject去get所有的值(參考createCompany那隻)在set進去step1的remitEntity
		
		//step4 merge
		
		//.....
		
		JSONObject result = new JSONObject();
		
		try {
			
			JSONObject obj = (JSONObject) new JSONParser().parse(remitStr);
			JSONObject remitObj = (JSONObject) obj.get("remit");
			
			RemitEntity  Remitentity = remitService.findRemitById(Integer.parseInt(remitObj.get("remitId").toString()));
			Remitentity.setRemitAccount(null != remitObj.get("remitAccount") ? remitObj.get("remitAccount").toString() : null);
			Remitentity.setRemitDeposit(null != remitObj.get("remitDeposit") ? remitObj.get("remitDeposit").toString() : null);
			Remitentity.setRemitMode(null != remitObj.get("remitMode") ? remitObj.get("remitMode").toString() : null);
			Remitentity.setCompanyId(null != remitObj.get("companyId") ? companyService.findCompanyById(Integer.parseInt(remitObj.get("companyId").toString())) : null);
			
			remitService.update(Remitentity);
			
			result.put("status", "success");
			result.put("message", "success");
		} catch (Exception e) {
			result.put("status", "error");
	    	result.put("message", "update error");
		}
		
		return result.toJSONString();
	} 
	 
}
