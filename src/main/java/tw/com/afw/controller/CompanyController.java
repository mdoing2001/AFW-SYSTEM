package tw.com.afw.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
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
import tw.com.afw.entity.OfficeEntity;
import tw.com.afw.entity.RemitEntity;
import tw.com.afw.entity.UserEntity;
import tw.com.afw.service.BranchService;
import tw.com.afw.service.CompanyService;
import tw.com.afw.service.ContractService;
import tw.com.afw.service.OfficeService;
import tw.com.afw.service.RemitService;
import tw.com.afw.service.UserService;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


@RestController
public class CompanyController {
	
	 @Autowired
	 private CompanyService companyService;
	 
	 @Autowired
	 private OfficeService officeService;
	 
	 @Autowired
	 private ContractService contractService;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private BranchService branchService;
	 
	 @Autowired
	 private RemitService remitService; 
	 
	
	 
     
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/new/company/add", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	    public String createCompany(@RequestBody String newCompanyJson  , HttpServletRequest request) {
	    	JSONObject result = new JSONObject();
	        //System.out.println("Creating Company " + company.getCompany_Name());
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
				companyEntity.setCompanyName(null != companyObj.get("companyName") ? companyObj.get("companyName").toString().trim() : null);
				companyEntity.setCompanyType(null != companyObj.get("companyType") ? companyObj.get("companyType").toString() : null);
				companyEntity.setCompanyMail(null != companyObj.get("companyMail") ? companyObj.get("companyMail").toString() : null);
				companyEntity.setCompanyCode(null != companyObj.get("companyCode") ? companyObj.get("companyCode").toString() : null);
				companyEntity.setCompanyEin(null != companyObj.get("companyEin") ? companyObj.get("companyEin").toString() : null);
				companyEntity.setCompanyExecutive(null != companyObj.get("companyExecutive") ? companyObj.get("companyExecutive").toString() : null);
				companyEntity.setCompanyBitrhday(!companyObj.get("companyBitrhday").toString().isEmpty() ? sdf.parse(companyObj.get("companyBitrhday").toString()) : null);
				companyEntity.setCompanyNumber(null != companyObj.get("companyNumber") ? companyObj.get("companyNumber").toString() : null);
				companyEntity.setCompanyFax(null != companyObj.get("companyFax") ? companyObj.get("companyFax").toString() : null);
				companyEntity.setCompanyAddress(null != companyObj.get("companyAddress") ? companyObj.get("companyAddress").toString() : null);
				companyEntity.setCompanyAddress2(null != companyObj.get("companyAddress2") ? companyObj.get("companyAddress2").toString() : null);
				companyEntity.setCompanyRemark(null != companyObj.get("companyRemark") ? companyObj.get("companyRemark").toString() : null);
				companyEntity.setCompanyStatus("on");
				
//				//company 統編驗證
//				String ein =companyEntity.getCompanyEin();
//				int checkein=companyService.checkEin(ein);
//				if(checkein == 1) {
					
					Integer companyId = companyService.ins(companyEntity);
					
					//TODO:塞contract("contract":{"userId":"","userId2":"-1","contractType":"-1","contractStart":"","contractEnd":"","contractRent":"","contractDeposit":"","contractRented":"","contractDeposited":"","contractRemarks":""})
					ContractEntity contractEntity = new ContractEntity();
					contractEntity.setUserId(!contractObj.get("userId").toString().isEmpty() ? userService.findUserById(Integer.parseInt(contractObj.get("userId").toString())) : null);
					contractEntity.setUserId2(!contractObj.get("userId2").toString().isEmpty() ? userService.findUserById(Integer.parseInt(contractObj.get("userId2").toString())) : null);
					contractEntity.setContractType("" != contractObj.get("contractType") ? contractObj.get("contractType").toString() : null);
					contractEntity.setContractDate(!contractObj.get("contractDate").toString().isEmpty() ? sdf.parse(contractObj.get("contractDate").toString()) : null);
					contractEntity.setContractStart(!contractObj.get("contractStart").toString().isEmpty() ? sdf.parse(contractObj.get("contractStart").toString()) : null);
					contractEntity.setContractEnd(!contractObj.get("contractEnd").toString().isEmpty() ? sdf.parse(contractObj.get("contractEnd").toString()) : null);
					contractEntity.setContractRent(!contractObj.get("contractRent").toString().isEmpty() ? Double.parseDouble(contractObj.get("contractRent").toString()): 0);
					contractEntity.setContractDeposit(!contractObj.get("contractDeposit").toString().isEmpty() ?  Double.parseDouble(contractObj.get("contractDeposit").toString()) : 0);
					contractEntity.setContractRented(!contractObj.get("contractRented").toString().isEmpty() ? Double.parseDouble(contractObj.get("contractRented").toString()) : 0);
					contractEntity.setContractDeposited(!contractObj.get("contractDeposited").toString().isEmpty() ? Double.parseDouble(contractObj.get("contractDeposited").toString()): 0);
					contractEntity.setContractRemarks(null != contractObj.get("contractRemarks") ? contractObj.get("contractRemarks").toString() : null);
					
					//TODO:塞office(辦公室號碼 不一定每張合約都有)("office":{"officeNumber":""})
					OfficeEntity officeEntity = new OfficeEntity();
					officeEntity.setOfficeNumber(null != officeObj.get("officeNumber") ? officeObj.get("officeNumber").toString() :null );				
					
					//塞accountancy("acc":{"accName":"","accContact":"","accPhone":"","accAddress":""})
					AccountancyEntity accountancyEntity = new AccountancyEntity();
					accountancyEntity.setAccAddress(null != accountancyObj.get("accAddress") ? accountancyObj.get("accAddress").toString() : null);
					accountancyEntity.setAccContact(null != accountancyObj.get("accContact") ? accountancyObj.get("accContact").toString() : null);
					accountancyEntity.setAccName(null != accountancyObj.get("accName") ? accountancyObj.get("accName").toString() : null);
					accountancyEntity.setAccPhone(null != accountancyObj.get("accPhone") ? accountancyObj.get("accPhone").toString() : null);
					
					CompanyEntity nCompanyEntity = companyService.findCompanyById(companyId);
					//System.out.println(nCompanyEntity.toString());
					//(辦公室號碼 不一定每張合約都有)
					if(!contractObj.get("contractType").toString().isEmpty() && contractObj.get("contractType").toString().equalsIgnoreCase("o")) {
						nCompanyEntity.setAccId(accountancyEntity);
						companyService.upd(nCompanyEntity);
						contractEntity.setCompanyId(nCompanyEntity);
						officeEntity.setContractId(contractEntity);
						officeService.ins(officeEntity);
					} else {
						nCompanyEntity.setAccId(accountancyEntity);
						companyService.upd(nCompanyEntity);
						contractEntity.setCompanyId(nCompanyEntity);
						contractService.ins(contractEntity);
					}
					
					result.put("status", "success");
			    	result.put("message", "success");
			    	result.put("companyId", companyId);
//				}else{
//					result.put("status", "error");
//			    	result.put("message", "統一編號重複");
//				}
					
		    	
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
	    
	    
	    
	    
	    //取回全部客戶(company.html) 這個頁面的資料 不同分店取的分店資料不一樣 管理員取回全部資料
	    //TODO:174, 185行上線前需要調整
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/retrive/company", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	    public String selectCompany(HttpServletRequest request) {
	    	Gson gson = new Gson();
	    	JSONObject result = new JSONObject();
	   
	    	try {
				//String usercode = (String) request.getSession().getAttribute("usercode");
	    		String userCode = (String) request.getSession().getAttribute("usercode");
				//System.err.println(usercode);
	    		List<CompanyEntity> companyArr = null;
				JSONArray arr = new JSONArray();

				
				if(userCode != null) {
					if(userCode.equals("AA")) {
						//總公司抓取全部顧客資料
						System.out.println("AA");
						companyArr = companyService.findAll();
					} else {
						System.out.println(userCode);
						companyArr = companyService.findCompanyByCode(userCode);
					}
					
					for(CompanyEntity entity : companyArr) {
						String officeNum = null;
						JSONObject obj = new JSONObject();
						List<ContractEntity> contractArr = contractService.findContractByCompanyId(entity.getCompanyId());
						
						for(ContractEntity contract : contractArr) {
							Date date = new Date();
							if(null != contract.getContractType() && contract.getContractType().equalsIgnoreCase("o")) {
								if(contract.getContractEnd().after(date)) {
									System.out.println(officeService.findOffByContract(contract.getContractId()).toString());
									officeNum = officeService.findOffByContract(contract.getContractId()).getOfficeNumber();
									date = contract.getContractEnd();
								}
							}
						}
						obj.put("company", entity);
						obj.put("officeNumber", officeNum);
						arr.add(obj);
					}
					
					result.put("status", "success");
					result.put("message", gson.toJson(arr));
				} else {
					result.put("status", "error");
					result.put("message", "請重新登入");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				result.put("status", "error");
				result.put("message", e);
			}
	    	
	    	return result.toJSONString();
	    }
	    
	    //取回不同contract type(租辦公室 個人座位....)的資料 一樣不同分店取的分店資料不一樣 管理員取回全部資料, id:使用者id
	    //TODO:224, 236行上線前需要調整
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/retrive/company/{type}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	    public String selectCompanyByType(@PathVariable("type") String type  , HttpServletRequest request) {
	    	Gson gson = new Gson();
	    	JSONObject result = new JSONObject();
	     	try {
	     		String usercode = (String) request.getSession().getAttribute("usercode");
				
				List<CompanyEntity> companyArr = null;
				JSONArray arr = new JSONArray();
				
				if(usercode.equals("AA")) {
					System.out.println(usercode);			
					companyArr = companyService.findAll();
				} else {
					System.out.println(usercode);
					companyArr = companyService.findCompanyByCode(usercode);
				}
					
				for(CompanyEntity entity : companyArr) {
					JSONObject obj = new JSONObject();
					String officeNum = "";
					List<ContractEntity> contractArr = contractService.findContractByCompanyId(entity.getCompanyId());
					for(ContractEntity contract : contractArr) {
						Date date = new Date();
						if(null != contract.getContractType() && contract.getContractType().equalsIgnoreCase(type)) {
							if(type.equalsIgnoreCase("o")) {
								if(contract.getContractEnd().after(date)) {
									officeNum = officeService.findOffByContract(contract.getContractId()).getOfficeNumber();
									date = contract.getContractEnd();
								}
							}
							
							obj.put("company", entity);
							obj.put("officeNumber", officeNum);
							arr.add(obj);
						}
					}
				}
				
				result.put("status", "success");
				result.put("message", gson.toJson(arr));
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
	    public String selectCompanyProfile(@PathVariable("id") int companyId  , HttpServletRequest request) {
	    	
	    	JSONObject result = new JSONObject();
	    	JSONObject obj = new JSONObject();
	    	
	    	try {
	    		CompanyEntity  companyEntity = companyService.findCompanyById(companyId);
	    		List<ContractEntity> contract = companyService.findContractByCompany(companyId);
	    		JSONArray arr = new JSONArray();
	    		
	    		for(ContractEntity entity : contract) {
	    			JSONObject subObj = new JSONObject();
	    			//Integer bId = null != entity.getBranchId() ? entity.getBranchId().getBranchId() : null;
	    			Integer uId1 = null != entity.getUserId() ? entity.getUserId().getUserId() : null;
	    			Integer uId2 = null != entity.getUserId2() ? entity.getUserId2().getUserId() : null;
	    			
	    			//BranchEntity branchEntity = branchService.findBranchById(bId);
  
	    			UserEntity  userEntity = null;
	    			UserEntity  userEntity2 = null;
	    			if(uId1 != null) {
	    				userEntity = userService.findUserById(uId1);
	    			}
	    			if(uId2 != null) {
	    				userEntity2 = userService.findUserById(uId2);
	    			}
	    			
	    			
	    			OfficeEntity officeEntity = officeService.findOffByContract(entity.getContractId());
	    			
	    			//subObj.put("branch", branchEntity);
	    			entity.setCompanyId(null);
	    			subObj.put("contract", entity);
	    			subObj.put("user", userEntity);
	    			subObj.put("user2", userEntity2);
	    			subObj.put("office", officeEntity);
	    			arr.add(subObj);
	    		}
			
	    		List<RemitEntity> rArr = remitService.findRemitByCompanyId(companyId);
	    		
				obj.put("company", companyEntity);
				obj.put("combine", arr);
				obj.put("remit", rArr);
//				obj.put("branchEntity", branchEntity);
				 
				result.put("status", "success");
				result.put("message", new Gson().toJson(obj));
				 
			} catch (Exception e) {
				e.printStackTrace();
				result.put("status", "error");
				result.put("message", e);
			}
	    	
	    	return result.toString();
	    }
	    
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/company/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	    public String updateCompany(@RequestBody String companyJson, @PathVariable("id") int companyId  , HttpServletRequest request) {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	JSONObject result = new JSONObject();
	    	
			try {
				JSONObject companyObj = (JSONObject) new JSONParser().parse(companyJson);
		    	CompanyEntity companyEntity = companyService.findCompanyById(companyId);
		    	if(companyEntity != null) {
		    		companyEntity.setCompanyName(null != companyObj.get("companyName") ? companyObj.get("companyName").toString() : null);
					companyEntity.setCompanyType(null != companyObj.get("companyType") ? companyObj.get("companyType").toString() : null);
					companyEntity.setCompanyMail(null != companyObj.get("companyMail") ? companyObj.get("companyMail").toString() : null);
					companyEntity.setCompanyCode(null != companyObj.get("companyCode") ? companyObj.get("companyCode").toString() : null);
					companyEntity.setCompanyEin(null != companyObj.get("companyEin") ? companyObj.get("companyEin").toString() : null);
					companyEntity.setCompanyExecutive(null != companyObj.get("companyExecutive") ? companyObj.get("companyExecutive").toString() : null);
					companyEntity.setCompanyBitrhday(!companyObj.get("companyBitrhday").toString().isEmpty() ? sdf.parse(companyObj.get("companyBitrhday").toString()) : null);
					companyEntity.setCompanyNumber(null != companyObj.get("companyNumber") ? companyObj.get("companyNumber").toString() : null);
					companyEntity.setCompanyFax(null != companyObj.get("companyFax") ? companyObj.get("companyFax").toString() : null);
					companyEntity.setCompanyAddress(null != companyObj.get("companyAddress") ? companyObj.get("companyAddress").toString() : null);
					companyEntity.setCompanyAddress2(null != companyObj.get("companyAddress2") ? companyObj.get("companyAddress2").toString() : null);
					companyEntity.setCompanyLetterStatus(null != companyObj.get("companyLetterStatus") ? companyObj.get("companyLetterStatus").toString() : null);
					companyEntity.setCompanyRemark(null != companyObj.get("companyRemark") ? companyObj.get("companyRemark").toString() : null);
					
					companyService.upd(companyEntity);
					
					result.put("status", "success");
			    	result.put("message", "success");
		    	} else {
		    		result.put("status", "error");
			    	result.put("message", "更新有誤 請重新操作");
		    	}
		    	
				
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
	    
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/company/status/update/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=UTF-8"})
	    public String updateCompanyStatus(@RequestBody String companyJson, @PathVariable("id") int companyId, HttpServletRequest request) {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	JSONObject result = new JSONObject();
	    	
	    	try {
				JSONObject companyObj = (JSONObject) new JSONParser().parse(companyJson);
		    	CompanyEntity companyEntity = companyService.findCompanyById(companyId);
		    	
		    	if(companyEntity != null) {
		    		companyEntity.setCompanyStatus(!companyObj.get("companyStatus").toString().isEmpty() ? companyObj.get("companyStatus").toString() : null);
			    	companyEntity.setCompanyStatusChangeDate(!companyObj.get("companyStatusChangeDate").toString().isEmpty() ? sdf.parse(companyObj.get("companyStatusChangeDate").toString()) : null);
			    	
			    	companyService.upd(companyEntity);
			    	
			    	result.put("status", "success");
			    	result.put("message", "success");
		    	} else {
		    		result.put("status", "error");
			    	result.put("message", "更新有誤 請重新操作");
		    	}
		    	
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
	    
}
