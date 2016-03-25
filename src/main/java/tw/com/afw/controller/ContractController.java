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
	 
	 
	
	@RequestMapping(value = "/new/contract/add/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createContract(@PathVariable("id") long id, @RequestBody String newContractStr) {
		
		
		
		return "";
	}

	 
	 
	 
	 
	 
}
