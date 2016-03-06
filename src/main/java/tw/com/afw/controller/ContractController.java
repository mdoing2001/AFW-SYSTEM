package tw.com.afw.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tw.com.afw.service.ContractService;

import com.google.gson.JsonSyntaxException;


@RestController
public class ContractController {

	
	
	 @Autowired
	 private ContractService ContractService;
	 
	 
	    
    //取回全部客戶(company.html) 這個頁面的資料 不同分店取的分店資料不一樣 管理員取回全部資料
    @RequestMapping(value = "/retrive/company/{id}", method = RequestMethod.GET, produces = "application/json")
    public String retriveDataFromCompany(@PathVariable("id") long id) {
    	
    	
    	
    	return "";
    }
    
    //取回不同contract type(租辦公室 個人座位....)的資料 一樣不同分店取的分店資料不一樣 管理員取回全部資料
    @RequestMapping(value = "/retrive/company/{id}/{type}", method = RequestMethod.GET, produces = "application/json")
    public String retriveDataFromCompanyByType(@PathVariable("id") long id, @PathVariable("type") String type) {
    	
    	return "";
    }
	 
	 
	 
	 
	 
}
