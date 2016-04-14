package tw.com.afw.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tw.com.afw.entity.RentEntity;
import tw.com.afw.service.CompanyService;
import tw.com.afw.service.RentService;


@RestController
public class RentController {
	
	@Autowired
	private RentService rentService;
	
	@Autowired
	private CompanyService companyService;
	 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new/rent/add", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createRent(@RequestBody String newRentStr) {
		JSONObject result = new JSONObject();
		
		try {
			JSONObject rentObj = (JSONObject) new JSONParser().parse(newRentStr);
			
			RentEntity rentEntity = new RentEntity();
			
			//目前只存年份月份發票號碼備註
			rentEntity.setRentYear(!rentObj.get("rentYear").toString().isEmpty() ? Integer.valueOf(rentObj.get("rentYear").toString()) : null);
			rentEntity.setRentMonth(!rentObj.get("rentMonth").toString().isEmpty() ? Integer.valueOf(rentObj.get("rentMonth").toString()) : null);
			rentEntity.setRentReceipt(!rentObj.get("rentMonth").toString().isEmpty() ? rentObj.get("rentMonth").toString() : null);
			rentEntity.setRentRemark(!rentObj.get("rentRemark").toString().isEmpty() ? rentObj.get("rentRemark").toString() : null);
			rentEntity.setCompanyId(null != rentObj.get("companyId") ? companyService.findCompanyById(Integer.parseInt(rentObj.get("companyId").toString())) : null);
			
			
//			remitEntity.setRemitAccount(null != remitObj.get("remitAccount") ? remitObj.get("remitAccount").toString() : null);
//			//remitEntity.setRemitDeposit(null != remitObj.get("remitDeposit") ? remitObj.get("remitDeposit").toString() : null);
//			remitEntity.setRemitMode(null != remitObj.get("remitMode") ? remitObj.get("remitMode").toString() : null);
//			remitEntity.setCompanyId(null != remitObj.get("companyId") ? companyService.findCompanyById(Integer.parseInt(remitObj.get("companyId").toString())) : null);
//			
			rentService.ins(rentEntity);
			result.put("status", "success");
			result.put("message", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
	    	result.put("message", "insert error");
		}
		
		return result.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rent/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateRent(@PathVariable("id") Integer id, @RequestBody String rentStr) {
		JSONObject result = new JSONObject();
		
		try {
			JSONObject rentObj = (JSONObject) new JSONParser().parse(rentStr);
			
			RentEntity rentEntity = rentService.findRentById(id);
			
			if(rentEntity != null) {
				//目前只能修改年份月份發票號碼備註
				rentEntity.setRentYear(!rentObj.get("rentYear").toString().isEmpty() ? Integer.valueOf(rentObj.get("rentYear").toString()) : null);
				rentEntity.setRentMonth(!rentObj.get("rentMonth").toString().isEmpty() ? Integer.valueOf(rentObj.get("rentMonth").toString()) : null);
				rentEntity.setRentReceipt(!rentObj.get("rentReceipt").toString().isEmpty() ? rentObj.get("rentReceipt").toString() : null);
				rentEntity.setRentRemark(!rentObj.get("rentRemark").toString().isEmpty() ? rentObj.get("rentRemark").toString() : null);
						
				rentService.update(rentEntity);
				result.put("status", "success");
				result.put("message", "success");
			} else {
				result.put("status", "error");
		    	result.put("message", "更新有誤 請重新操作");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
	    	result.put("message", "update error");
		}
		
		return result.toJSONString();
	} 
	 
}
