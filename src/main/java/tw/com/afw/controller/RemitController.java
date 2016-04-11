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
	
	@Autowired
	private CompanyService companyService;
	 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new/remit/add", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createRemit(@RequestBody String newRemitStr) {
		
		JSONObject result = new JSONObject();
		
		try {
			
			JSONObject remitObj = (JSONObject) new JSONParser().parse(newRemitStr);
			
			RemitEntity remitEntity = 	new RemitEntity();
			
			remitEntity.setRemitAccount(null != remitObj.get("remitAccount") ? remitObj.get("remitAccount").toString() : null);
			//remitEntity.setRemitDeposit(null != remitObj.get("remitDeposit") ? remitObj.get("remitDeposit").toString() : null);
			remitEntity.setRemitMode(null != remitObj.get("remitMode") ? remitObj.get("remitMode").toString() : null);
			remitEntity.setCompanyId(null != remitObj.get("companyId") ? companyService.findCompanyById(Integer.parseInt(remitObj.get("companyId").toString())) : null);
			
			remitService.ins(remitEntity);
			result.put("status", "success");
			result.put("message", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
	    	result.put("message", "insert error");
		}
		
		return result.toJSONString();
	}
	
	//利用company id 去 撈 remitEntity 再去set全部的值
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/remit/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateRemit(@PathVariable("id") Integer id, @RequestBody String remitStr) {
		JSONObject result = new JSONObject();
		
		try {
			
			JSONObject remitObj = (JSONObject) new JSONParser().parse(remitStr);
			
			RemitEntity Remitentity = remitService.findRemitById(id);
			Remitentity.setRemitAccount(null != remitObj.get("remitAccount") ? remitObj.get("remitAccount").toString() : null);
			Remitentity.setRemitMode(null != remitObj.get("remitMode") ? remitObj.get("remitMode").toString() : null);
			Remitentity.setCompanyId(null != remitObj.get("companyId") ? companyService.findCompanyById(Integer.parseInt(remitObj.get("companyId").toString())) : null);
			
			remitService.update(Remitentity);
			
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
