package tw.com.afw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import tw.com.afw.entity.BranchEntity;
import tw.com.afw.entity.CompanyEntity;
import tw.com.afw.entity.ContractEntity;
import tw.com.afw.entity.OfficeEntity;
import tw.com.afw.entity.UserEntity;
import tw.com.afw.service.BranchService;
import tw.com.afw.service.CompanyService;
import tw.com.afw.service.UserService;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


@RestController
public class CompanyController {

	
	
	 @Autowired
	 private CompanyService CompanyService;
	 private BranchService branchService;
	 private UserService userService;
	 
	 public HttpServletRequest request;
	 
     
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/new/company/add", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	    public String createCompany(@RequestBody String newCompanyJson) {
	    	JSONObject result = new JSONObject();
	        //System.out.println("Creating Company " + company.getCompany_Name());

	        //CompanyService.ins(companyj);
	    	try {
	    		//抓取前端全部資料json格式
				JSONObject obj = (JSONObject) new JSONParser().parse(newCompanyJson);
				
				//四個table 分別取出來
				JSONObject companyObj = (JSONObject) obj.get("company");
				JSONObject contractObj = (JSONObject) obj.get("contract");
				JSONObject accountancyObj = (JSONObject) obj.get("acc");
				JSONObject officeObj = (JSONObject) obj.get("office");
				
				//TODO:塞company("company":{"companyName":"","companyType":"","companyMail":"","companyCode":"-1","companyEin":"","companyExecutive":"","companyBitrhday":"","companyNumber":"","companyFax":"","companyAddress2":"","companyRemark":""})
				CompanyEntity companyEntity = new CompanyEntity();
				
				
				
				
				//TODO:塞contract("contract":{"userId":"","userId2":"-1","contractType":"-1","contractStart":"","contractEnd":"","contractRent":"","contractDeposit":"","contractRented":"","contractDeposited":"","contractRemarks":""})
				ContractEntity contractEntity = new ContractEntity();
				
				
				
				
				//TODO:塞office(辦公室號碼 不一定每張合約都有)("office":{"officeNumber":""})
				OfficeEntity officeEntity = new OfficeEntity();
				
				
				
				
				//塞accountancy("acc":{"accName":"","accContact":"","accPhone":"","accAddress":""})
				AccountancyEntity accountancyEntity = new AccountancyEntity();
				accountancyEntity.setAccAddress(null != accountancyObj.get("accAdress") ? accountancyObj.get("accAdress").toString() : null);
				accountancyEntity.setAccContact(null != accountancyObj.get("accContact") ? accountancyObj.get("accContact").toString() : null);
				accountancyEntity.setAccName(null != accountancyObj.get("accName") ? accountancyObj.get("accName").toString() : null);
				accountancyEntity.setAccPhone(null != accountancyObj.get("accPhone") ? accountancyObj.get("accPhone").toString() : null);
				
				//(辦公室號碼 不一定每張合約都有)
				if(null != officeObj.get("officeNumber")) {
					companyEntity.setAccId(accountancyEntity);
					contractEntity.setCompanyId(companyEntity);
					officeEntity.setContractId(contractEntity);
				} else {
					companyEntity.setAccId(accountancyEntity);
					contractEntity.setCompanyId(companyEntity);
				}
				
				//contractEntity.setUser_id(UserEntity);
				//contractEntity.setUser_id2(UserEntity);
				
				//companyEntity.getAcc_Id().getAcc_name();
				
				//company 統編驗證
				String ein =companyEntity.getCompanyEin();
				int checkein=CompanyService.checkEin(ein);
				if(checkein == 1){
					//新增(下面那行有錯 不用三個都insert 74行開始已經把entity塞進去了 只要insert最後那個就可)
					//CompanyService.conins(companyEntity, contractEntity, accountancyEntity);
					if(null != officeObj.get("officeNumber")) {
						//TODO:需要officeService的insert(新增OfficeEntity)
					} else {
						//TODO:需要contractService的insert(新增ContractEntity)
					}
					
					result.put("status", "success");
			    	result.put("message", "success");
				}else{
					result.put("status", "error");
			    	result.put("message", "新增失敗");
				}
					
		    	
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

	    @RequestMapping(value = "/retrive/company/{id}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	    public String selectCompany(@PathVariable("id") long id) {
	    	Gson gson = new Gson();

	    	String usercode = (String) request.getSession().getAttribute("usercode");
	    	if(usercode.equals("AA")){
	    		//總公司抓取全部顧客資料
	    		List<CompanyEntity> companyAll= CompanyService.findAll(); 
	    		//list to json
	    		String companyjson = gson.toJson(companyAll);	    		
	    		return companyjson;	
	    	}else{
	    		List<CompanyEntity> companycode = CompanyService.findCompanyByCode(usercode);
	    		String companyjson = gson.toJson(companycode);	
	    	 	return companyjson;
	    	}
	   
	    }
	    
	    //取回不同contract type(租辦公室 個人座位....)的資料 一樣不同分店取的分店資料不一樣 管理員取回全部資料, id:使用者id
	    @RequestMapping(value = "/retrive/company/{id}/{type}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	    public String selectCompanyByType(@PathVariable("id") int companyId,@PathVariable("type") String type) {
	    	Gson gson = new Gson();
	     	String usercode = (String) request.getSession().getAttribute("usercode");
	    	if(usercode.equals("AA")){
	    		List<ContractEntity> contracttype = CompanyService.findContractByType(type);
	    	    String typejson=gson.toJson(contracttype);
	    		return typejson;
	    		
	    	}else{
	    		
	    		List<ContractEntity> contracttype =CompanyService.findContractByTypeId(type, companyId);
	    		String typejson=gson.toJson(contracttype);
		    	return typejson;
	    	}
	    	
	    }
	    
	    //取回每間公司(客戶)的資料(companyProfile) ex:合約也要全部給我, id:公司id
	    @RequestMapping(value = "/retrive/companyProfile/{id}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	    public String selectCompanyProfile(@PathVariable("id") int companyId) {
	    	int ContractEntity = 0;
	    	Gson gson = new Gson();
	    	JSONObject result = new JSONObject();
	    	 String profilejson = null;
	    	try {
				List<ContractEntity>contract=CompanyService.findContractByCompany(companyId);
				
				 int  bid=  contract.get(ContractEntity).getBranchId().getBranchId();
				 int  cid=  contract.get(ContractEntity).getCompanyId().getCompanyId();
				 int   uid1=contract.get(ContractEntity).getUserId().getUserId();
				 int   uid2=contract.get(ContractEntity).getUserId2().getUserId();
				 BranchEntity branchEntity= branchService.findBranchById(bid);
				 CompanyEntity companyEntity =CompanyService.comById(cid);
				 UserEntity user1= userService.findUserById(uid1);
				 UserEntity user2= userService.findUserById(uid2);
				 
			     //怎麼組起來
				 
			} catch (Exception e) {
				e.printStackTrace();
				result.put("status", "error");
				result.put("message", e);
			}
	        	 result.put("status", "success");
		         result.put("message", profilejson);
		         
		         
	    	return result.toJSONString();
	    }
}
