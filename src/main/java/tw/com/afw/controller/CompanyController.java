package tw.com.afw.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import tw.com.afw.entity.AccountancyEntity;
import tw.com.afw.entity.CompanyEntity;
import tw.com.afw.entity.ContractEntity;
import tw.com.afw.service.CompanyService;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


@Controller
public class CompanyController {

	
	
	 @Autowired
	 private CompanyService CompanyService;
	 
	 
	 
	    //-------------------Create a User--------------------------------------------------------
     
	    @RequestMapping(value = "/new/company/add", method = RequestMethod.POST)
	    public String createUser(@RequestBody String newCompanyJson) {
	        //System.out.println("Creating Company " + company.getCompany_Name());

	        //CompanyService.ins(companyj);
	    	try {
				JSONObject obj = (JSONObject) new JSONParser().parse(newCompanyJson);
				
				String companyStr = obj.get("company").toString();
				String contractStr = obj.get("contract").toString();
				String accStr = obj.get("acc").toString();
				
				CompanyEntity companyEntity = new Gson().fromJson(companyStr, CompanyEntity.class);
				ContractEntity contractEntity = new Gson().fromJson(contractStr, ContractEntity.class);
				AccountancyEntity accountancyEntity = new Gson().fromJson(accStr, AccountancyEntity.class);
				
				companyEntity.setAcc_Id(accountancyEntity);
				contractEntity.setCompany_id(companyEntity);
				
				//companyEntity.getAcc_Id().getAcc_name();
				
				
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
				//{"status":"fail","message":e.printStackTrace();}
				return null;
			} catch (ParseException e) {
				e.printStackTrace();
				//{"status":"fail","message":e.printStackTrace();}
				return null;
			}

	    	
	       // HttpHeaders headers = new HttpHeaders();
	        //headers.setLocation(ucBuilder.path("/xxx").buildAndExpand(company.getCompany_Id()).toUri());
	        return "/xxxx";
	    }
	 
	 
	 
	 
	 
}
