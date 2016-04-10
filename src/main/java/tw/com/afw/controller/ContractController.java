package tw.com.afw.controller;

import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tw.com.afw.entity.ContractEntity;
import tw.com.afw.entity.OfficeEntity;
import tw.com.afw.service.BranchService;
import tw.com.afw.service.CompanyService;
import tw.com.afw.service.ContractService;
import tw.com.afw.service.OfficeService;
import tw.com.afw.service.UserService;


@RestController
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private OfficeService officeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new/contract/add", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createContract(@RequestBody String newContractStr) {
		//
		JSONObject result = new JSONObject();
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			JSONObject contractObj = (JSONObject) new JSONParser().parse(newContractStr);
			
			ContractEntity contractEntity = new ContractEntity();
			//BranchEntity Branchentity= new BranchEntity();
			OfficeEntity officeEntity = new OfficeEntity();
			
			contractEntity.setContractStart(null != contractObj.get("contractStart") ? sdf.parse(contractObj.get("contractStart").toString()) : null);
			contractEntity.setContractEnd(null != contractObj.get("contractEnd") ? sdf.parse(contractObj.get("contractEnd").toString()) : null);
			contractEntity.setContractDeposit(null != contractObj.get("contractDeposit") ?  Double.parseDouble(contractObj.get("contractDeposit").toString()) : null);
			contractEntity.setContractRent(null != contractObj.get("contractRent") ? Double.parseDouble(contractObj.get("contractRent").toString()): null);
			contractEntity.setContractDeposited(null != contractObj.get("contractDeposited") ? Double.parseDouble(contractObj.get("contractDeposited").toString()): null);
			contractEntity.setContractRented(null != contractObj.get("contractRented") ? Double.parseDouble(contractObj.get("contractRented").toString()): null);
			//contractEntity.setBranchId(branchService.findBranchById(Integer.parseInt(contractObj.get("branchId").toString())));
			contractEntity.setUserId(userService.findUserById(Integer.parseInt(contractObj.get("userId").toString())));
			contractEntity.setUserId2(userService.findUserById(Integer.parseInt(contractObj.get("userId2").toString())));
			contractEntity.setCompanyId(companyService.findCompanyById(Integer.parseInt(contractObj.get("companyId").toString())));
			contractEntity.setContractDate(null != contractObj.get("contractDate") ? sdf.parse(contractObj.get("contractDate").toString()) : null);
			contractEntity.setContractType(null != contractObj.get("contractType") ? contractObj.get("contractType").toString() : null);
			contractEntity.setContractRemarks(null != contractObj.get("contractRemarks") ? contractObj.get("contractRemarks").toString() : null);
			contractEntity.setContractDel(null != contractObj.get("contractDel") ? contractObj.get("contractDel").toString() : null);
			
			if(!contractObj.get("officeNumber").toString().isEmpty() && contractObj.get("contractType").toString().equalsIgnoreCase("o")) {
				officeEntity.setOfficeNumber(contractObj.get("officeNumber").toString());
				officeEntity.setContractId(contractEntity);
				officeService.ins(officeEntity);
			} else {
				contractService.ins(contractEntity);
			}
			
			result.put("status", "success");
			result.put("message", "success");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
	    	result.put("message", "insert error");
		}
		return result.toJSONString();
	}
	
	//先用contract 的 id把contract撈出來 再去set(前端不確定是改哪一個 所以全部都要set)
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/contract/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateContract(@PathVariable("id") int id, @RequestBody String ContractStr) {
		
		JSONObject result = new JSONObject();
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  	
	  	try {
	  		
	  		JSONObject contractObj = (JSONObject) new JSONParser().parse(ContractStr);
			ContractEntity contractEntity = contractService.findContracById(id);
			
			contractEntity.setContractStart(null != contractObj.get("contractStart") ? sdf.parse(contractObj.get("contractStart").toString()) : null);
			contractEntity.setContractEnd(null != contractObj.get("contractEnd") ? sdf.parse(contractObj.get("contractEnd").toString()) : null);
			contractEntity.setContractDeposit(null != contractObj.get("contractDeposit") ?  Double.parseDouble(contractObj.get("contractDeposit").toString()) : null);
			contractEntity.setContractRent(null != contractObj.get("contractRent") ? Double.parseDouble(contractObj.get("contractRent").toString()): null);
			contractEntity.setContractDeposited(null != contractObj.get("contractDeposited") ? Double.parseDouble(contractObj.get("contractRented").toString()): null);
			contractEntity.setContractRented(null != contractObj.get("contractRented") ? Double.parseDouble(contractObj.get("contractRented").toString()): null);
			contractEntity.setContractDate(!contractObj.get("contractDate").toString().isEmpty() ? sdf.parse(contractObj.get("contractDate").toString()) : null);
			contractEntity.setContractType(null != contractObj.get("contractType") ? contractObj.get("contractType").toString() : null);
			contractEntity.setContractRemarks(null != contractObj.get("contractRemarks") ? contractObj.get("contractRemarks").toString() : null);
			contractEntity.setContractDel(null != contractObj.get("contractDel") ? contractObj.get("contractDel").toString() : null);
			
			contractService.update(contractEntity);
			
			OfficeEntity officeEntity = officeService.findOffByContract(id);
			officeEntity.setOfficeNumber(null != contractObj.get("officeNumber") ? contractObj.get("officeNumber").toString() : null);
			officeService.update(officeEntity);
			
			result.put("status", "success");
			result.put("message", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
	    	result.put("message", "update error");
		}
	  	
		
		
		return result.toJSONString();
	}

	//annotation麻煩你在設定 這隻就用contractId去撈officeEntity 再去 set officeType就可以 不用全部set
	@RequestMapping(value = "/new/contractOffice/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateOfficeTypeByContractId(@PathVariable("id") long id, @RequestBody String ContractOfficeStr) {
		
		JSONObject result = new JSONObject();
		
		
		try {
	  		JSONObject obj = (JSONObject) new JSONParser().parse(ContractOfficeStr);
		    JSONObject contractObj = (JSONObject) obj.get("contract");
		    
		    ContractEntity Contractentity=   contractService.findContracById( Integer.parseInt(contractObj.get("contractId").toString()));
		    int cid= Contractentity.getContractId();
		    
		    OfficeEntity  Officeentity =   officeService.findOffByContract(cid);
		    
		    Officeentity.setOfficeType(null != contractObj.get("officeType") ? contractObj.get("officeType").toString() : null);
	
		    officeService.update(Officeentity);
		    
			result.put("status", "success");
			result.put("message", "success");
			
		} catch (Exception e) {
			result.put("status", "error");
	    	result.put("message", "update error");
		}
		
		
		return result.toJSONString();
	}
	
	//annotation麻煩你在設定 這隻就用contractId去撈contractEntity(介紹人) 再去 set user_id2就可以 因為我會直接給你user的id 所以就不用撈userEntity
	@RequestMapping(value = "/new/contractUser/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	public String updateUserId2ByContractId(@PathVariable("id") long id, @RequestBody String userOfficeStr) {
		
		JSONObject result = new JSONObject();
		
		try {
			
			JSONObject obj = (JSONObject) new JSONParser().parse(userOfficeStr);
		    JSONObject contractObj = (JSONObject) obj.get("contract");
			
		    ContractEntity Contractentity = contractService.findContracById( Integer.parseInt(contractObj.get("contractId").toString()));
		    Contractentity.setUserId2(userService.findUserById(Integer.parseInt(contractObj.get("userId2").toString())));
		   
		    contractService.update(Contractentity);
			
			result.put("status", "success");
			result.put("message", "success");
		    
		} catch (Exception e) {
			result.put("status", "error");
	    	result.put("message", "update error");
		}
		
		
		return result.toJSONString();
	}
	 
	 
	 
	 
	 
}
