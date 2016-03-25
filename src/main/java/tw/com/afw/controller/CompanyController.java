package tw.com.afw.controller;

import java.text.SimpleDateFormat;
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
import tw.com.afw.service.BranchService;
import tw.com.afw.service.CompanyService;
import tw.com.afw.service.ContractService;
import tw.com.afw.service.OfficeService;
import tw.com.afw.service.UserService;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


@RestController
public class CompanyController {

	
	
	 @Autowired
	 private CompanyService CompanyService;
	 private OfficeService officeService;
	 private ContractService contractService;
	 private UserService userService;
	 private BranchService branchService;
	 
	 public HttpServletRequest request;
	 
     
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/new/company/add", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	    public String createCompany(@RequestBody String newCompanyJson) {
	    	JSONObject result = new JSONObject();
	        //System.out.println("Creating Company " + company.getCompany_Name());
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
				companyEntity.setCompanyName(null != companyObj.get("companyName") ? companyObj.get("companyName").toString() : null);
				companyEntity.setCompanyType(null != companyObj.get("companyType") ? companyObj.get("companyType").toString() : null);
				companyEntity.setCompanyMail(null != companyObj.get("companyMail") ? companyObj.get("companyMail").toString() : null);
				companyEntity.setCompanyCode(null != companyObj.get("companyCode") ? companyObj.get("companyCode").toString() : null);
				companyEntity.setCompanyEin(null != companyObj.get("companyEin") ? companyObj.get("companyEin").toString() : null);
				companyEntity.setCompanyExecutive(null != companyObj.get("companyExecutive") ? companyObj.get("companyExecutive").toString() : null);
				companyEntity.setCompanyBitrhday(null != companyObj.get("companyBitrhday") ? sdf.parse(companyObj.get("companyBitrhday").toString()) : null);
				companyEntity.setCompanyNumber(null != companyObj.get("companyNumber") ? companyObj.get("companyNumber").toString() : null);
				companyEntity.setCompanyFax(null != companyObj.get("companyFax") ? companyObj.get("companyFax").toString() : null);
				companyEntity.setCompanyAddress2(null != companyObj.get("companyAddress2") ? companyObj.get("companyAddress2").toString() : null);
				companyEntity.setCompanyRemark(null != companyObj.get("companyRemark") ? companyObj.get("companyRemark").toString() : null);
				
				//TODO:塞contract("contract":{"userId":"","userId2":"-1","contractType":"-1","contractStart":"","contractEnd":"","contractRent":"","contractDeposit":"","contractRented":"","contractDeposited":"","contractRemarks":""})
				ContractEntity contractEntity = new ContractEntity();
				contractEntity.setUserId(null != companyObj.get("userId") ? userService.findUserById(Integer.parseInt(companyObj.get("userId").toString())) : null);
				contractEntity.setUserId2(null != companyObj.get("userId2") ? userService.findUserById(Integer.parseInt(companyObj.get("userId").toString())) : null);
				contractEntity.setContractType(null != contractObj.get("contractType") ? contractObj.get("contractType").toString() : null);
				contractEntity.setContractStart(null != contractObj.get("contractStart") ? sdf.parse(contractObj.get("contractStart").toString()) : null);
				contractEntity.setContractEnd(null != contractObj.get("contractEnd") ? sdf.parse(contractObj.get("contractEnd").toString()) : null);
				contractEntity.setContractRent(null != contractObj.get("contractRent") ? Double.parseDouble(contractObj.get("contractRent").toString()): null);
				contractEntity.setContractType(null != contractObj.get("contractDeposit") ? contractObj.get("contractDeposit").toString() : null);
				contractEntity.setContractType(null != contractObj.get("contractRented") ? contractObj.get("contractRented").toString() : null);
				contractEntity.setContractType(null != contractObj.get("contractDeposited") ? contractObj.get("contractDeposited").toString() : null);
				contractEntity.setContractType(null != contractObj.get("contractRemarks") ? contractObj.get("contractRemarks").toString() : null);
							
				
				//TODO:塞office(辦公室號碼 不一定每張合約都有)("office":{"officeNumber":""})
				OfficeEntity officeEntity = new OfficeEntity();
				officeEntity.setOfficeNumber(null != officeObj.get("officeNumber") ? officeObj.get("officeNumber").toString() :null );				
				
				
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
				
				//company 統編驗證
				String ein =companyEntity.getCompanyEin();
				int checkein=CompanyService.checkEin(ein);
				if(checkein == 1){
					//新增(下面那行有錯 不用三個都insert 74行開始已經把entity塞進去了 只要insert最後那個就可)
					//CompanyService.conins(companyEntity, contractEntity, accountancyEntity);
					CompanyService.ins(companyEntity);
					
					if(null != officeObj.get("officeNumber")) {
						//TODO:需要officeService的insert(新增OfficeEntity)
						officeService.ins(officeEntity);
					} else {
						//TODO:需要contractService的insert(新增ContractEntity)
						contractService.ins(contractEntity);
					}
					
					result.put("status", "success");
			    	result.put("message", "success");
				}else{
					result.put("status", "error");
			    	result.put("message", "insert error");
				}
					
		    	
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
				result.put("status", "error");
		    	result.put("message", e);
			} catch (ParseException e) {
				e.printStackTrace();
				result.put("status", "error");
		    	result.put("message", e);
			} catch (java.text.ParseException e) {			
				e.printStackTrace();
				result.put("status", "error");
		    	result.put("message", e);
			}
	    	
	        return result.toJSONString();
	    }
	    
	    
	    
	    
	    //取回全部客戶(company.html) 這個頁面的資料 不同分店取的分店資料不一樣 管理員取回全部資料, id:使用者id
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/retrive/company", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	    public String selectCompany() {
	    	Gson gson = new Gson();
	    	JSONObject result = new JSONObject();
	    	String companyjson = null;
	    	try {
				String usercode = (String) request.getSession().getAttribute("usercode");
				System.err.println(usercode);
				
				if(usercode.equals("AA")){
					//總公司抓取全部顧客資料
					List<CompanyEntity> companyAll= CompanyService.findAll(); 
					//list to json
					companyjson = gson.toJson(companyAll);
				}else{
					List<CompanyEntity> companycode = CompanyService.findCompanyByCode(usercode);
					companyjson = gson.toJson(companycode);
				}
				
				result.put("status", "success");
				result.put("message", companyjson);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("status", "error");
				result.put("message", e);
			}
	    	
	    	return result.toJSONString();
	    }
	    
	    //取回不同contract type(租辦公室 個人座位....)的資料 一樣不同分店取的分店資料不一樣 管理員取回全部資料, id:使用者id
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/retrive/company/{id}/{type}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	    public String selectCompanyByType(@PathVariable("id") int companyId,@PathVariable("type") String type) {
	    	Gson gson = new Gson();
	    	JSONObject result = new JSONObject();
	     	try {
				String usercode = (String) request.getSession().getAttribute("usercode");
				String typejson = null;
				if(usercode.equals("AA")){
					List<ContractEntity> contracttype = CompanyService.findContractByType(type);
				    typejson = gson.toJson(contracttype);
					
				}else{
					List<ContractEntity> contracttype =CompanyService.findContractByTypeId(type, companyId);
					typejson = gson.toJson(contracttype);
				}
				
				result.put("status", "success");
		    	result.put("message", typejson);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("status", "error");
				result.put("message", e);
			}
	    	return result.toJSONString();
	    }
	    
	    //取回每間公司(客戶)的資料(companyProfile) ex:合約也要全部給我, id:公司id
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/retrive/companyProfile/{id}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	    public String selectCompanyProfile(@PathVariable("id") int companyId) {
	    	
	    	JSONObject result = new JSONObject();
	    	JSONObject obj = null;
	    	try {
	    		List<ContractEntity> contract = CompanyService.findContractByCompany(companyId);
	    		obj = new JSONObject();
	    		for(ContractEntity con: contract){
	    		  int bid =	 con.getBranchId().getBranchId();
	    		  int cid =	 con.getCompanyId().getCompanyId();
	    		  int uid1 =	 con.getBranchId().getBranchId();
	    		  int uid2 =	 con.getBranchId().getBranchId();
	    		  BranchEntity branchEntity= branchService.findBranchById(bid);
	    		}
	    		
	    		//TODO mark 起來的都有問題 看一下profile需要哪些資料
//	    		int bid = ((ContractEntity) contract).getBranchId().getBranchId();
//	    		int cid = contract.get(ContractEntity).getCompanyId().getCompanyId();
//	    		int uid1 = contract.get(ContractEntity).getUserId().getUserId();
//	    		int uid2 = contract.get(ContractEntity).getUserId2().getUserId();
//				BranchEntity branchEntity= branchService.findBranchById(bid);
//				CompanyEntity companyEntity =CompanyService.comById(cid);
//				UserEntity user1= userService.findUserById(uid1);
//				UserEntity user2= userService.findUserById(uid2);
				 
				 
				 
				//怎麼組起來 
	    		//下面是範例 上面改好後用下面方式組起來
			
//				obj.put("company", companyEntity);
//				obj.put("contract", contract);
//				obj.put("branchEntity", branchEntity);
//				obj.put("user1", user1);
//				obj.put("user2", user2);
				 
				result.put("status", "success");
				result.put("message", obj.toJSONString());
				 
			} catch (Exception e) {
				e.printStackTrace();
				result.put("status", "error");
				result.put("message", e);
			}
	    	
	    	return result.toJSONString();
	    }
	    
}
