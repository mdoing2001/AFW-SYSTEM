package tw.com.afw.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tw.com.afw.entity.AccountancyEntity;
import tw.com.afw.entity.CompanyEntity;
import tw.com.afw.entity.ContractEntity;
import tw.com.afw.service.CompanyService;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


@RestController
public class CompanyController {

	
	
	 @Autowired
	 private CompanyService CompanyService;
	 
	 
	 
	    //-------------------Create a User--------------------------------------------------------
     
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/new/company/add", method = RequestMethod.POST, produces = "application/json")
	    public String createUser(@RequestBody String newCompanyJson) {
	    	JSONObject result = new JSONObject();
	        //System.out.println("Creating Company " + company.getCompany_Name());

	        //CompanyService.ins(companyj);
	    	try {
				JSONObject obj = (JSONObject) new JSONParser().parse(newCompanyJson);
				
				String companyStr = obj.get("company").toString();
				String contractStr = obj.get("contract").toString();
				String accStr = obj.get("acc").toString();
				
				System.out.println(companyStr);
				System.out.println(contractStr);
				System.out.println(accStr);
				
				CompanyEntity companyEntity = new Gson().fromJson(companyStr, CompanyEntity.class);
				ContractEntity contractEntity = new Gson().fromJson(contractStr, ContractEntity.class);
				AccountancyEntity accountancyEntity = new Gson().fromJson(accStr, AccountancyEntity.class);
				
				companyEntity.setAcc_Id(accountancyEntity);
				contractEntity.setCompany_id(companyEntity);
				
				//contractEntity.setUser_id(UserEntity);
				//contractEntity.setUser_id2(UserEntity);
				
				//companyEntity.getAcc_Id().getAcc_name();
			
				result.put("status", "success");
		    	result.put("message", "success");
		    	
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
				result.put("status", "error");
		    	result.put("message", e);
			} catch (ParseException e) {
				e.printStackTrace();
				result.put("status", "error");
		    	result.put("message", e);
			}
	    	
	        return result.toJSONString();
	    }
	    
	    //取回全部客戶(company.html) 這個頁面的資料 不同分店取的分店資料不一樣 管理員取回全部資料, id:使用者id
	    @RequestMapping(value = "/retrive/company/{id}", method = RequestMethod.GET, produces = "application/json")
	    public String retriveDataFromCompany(@PathVariable("id") long id) {
	    	
	    	return "";
	    }
	    
	    //取回不同contract type(租辦公室 個人座位....)的資料 一樣不同分店取的分店資料不一樣 管理員取回全部資料, id:使用者id
	    @RequestMapping(value = "/retrive/company/{id}/{type}", method = RequestMethod.GET, produces = "application/json")
	    public String retriveDataFromCompanyByType(@PathVariable("id") long id, @PathVariable("type") String type) {
	    	
	    	return "";
	    }
	    
	    //取回每間公司(客戶)的資料(companyProfile) ex:合約也要全部給我, id:公司id
	    @RequestMapping(value = "/retrive/companyProfile/{id}", method = RequestMethod.GET, produces = "application/json")
	    public String retriveDataForCompanyProfile(@PathVariable("id") long id) {
	    	
	    	return "";
	    }
}
