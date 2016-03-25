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
import tw.com.afw.service.RemitService;


@RestController
public class RemitController {

	
	
	@Autowired
	private RemitService remitService;
	 
	 
	
	@RequestMapping(value = "/new/remit/add/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createRemit(@PathVariable("id") long id, @RequestBody String newRemitStr) {
		//參考createCompany那隻
		
		
		return "";
	}
	
	//利用company id 去 撈 remitEntity 再去set全部的值
	@RequestMapping(value = "/remit/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateRemit(@PathVariable("id") long id, @RequestBody String remitStr) {
		//step1 id撈remitEntity
		
		//step2 remitStr 轉乘jsonobject
		
		//step3 jsonobject去get所有的值(參考createCompany那隻)在set進去step1的remitEntity
		
		//step4 merge
		
		//.....
		return "";
	} 
	 
}
