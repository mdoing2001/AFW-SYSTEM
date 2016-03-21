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
import tw.com.afw.entity.CompanyEntity;
import tw.com.afw.entity.ContractEntity;
import tw.com.afw.service.CompanyService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


@RestController
public class CompanyController {

	
	
	 @Autowired
	 private CompanyService CompanyService;
	 
	 public HttpServletRequest request;
	 
     
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/new/company/add", method = RequestMethod.POST, produces = "application/json")
	    public String createCompany(@RequestBody String newCompanyJson) {
	    	JSONObject result = new JSONObject();
	        //System.out.println("Creating Company " + company.getCompany_Name());

	        //CompanyService.ins(companyj);
	    	try {
	    		//抓取前端全部資料json格式
				JSONObject obj = (JSONObject) new JSONParser().parse(newCompanyJson);
				
				//有三個table (key :value) 轉字串
				String companyStr = obj.get("company").toString();
				String contractStr = obj.get("contract").toString();
				String accStr = obj.get("acc").toString();
				
				System.out.println(companyStr);
				System.out.println(contractStr);
				System.out.println(accStr);
				
				//用FromJson將字串塞入entity
				CompanyEntity companyEntity = new Gson().fromJson(companyStr, CompanyEntity.class);
				ContractEntity contractEntity = new Gson().fromJson(contractStr, ContractEntity.class);
				AccountancyEntity accountancyEntity = new Gson().fromJson(accStr, AccountancyEntity.class);

				//在entity 裡有FK必須要有對應的連結
				companyEntity.setAccId(accountancyEntity);
				contractEntity.setCompanyId(companyEntity);
				
				//contractEntity.setUser_id(UserEntity);
				//contractEntity.setUser_id2(UserEntity);
				
				//companyEntity.getAcc_Id().getAcc_name();
				
				//company 統編驗證
				String ein =companyEntity.getCompanyEin();
				int checkein=CompanyService.checkEin(ein);
				if(checkein == 1){
					//新增
					CompanyService.conins(companyEntity, contractEntity, accountancyEntity);
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
	    
	    //更新客戶資料
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/retrive/company/{update}", method = RequestMethod.GET, produces = "application/json")
	    public String UpdateCompany(@PathVariable("update") String update)  {
	    	
	    	JSONObject result = new JSONObject();
	    	try {
				JSONObject obj = (JSONObject) new JSONParser().parse(update);
				String companyStr = obj.get("company").toString();
				CompanyEntity companyEntity = new Gson().fromJson(companyStr, CompanyEntity.class);
		
				CompanyService.upd(companyEntity);
				
				result.put("status", "success");
		    	result.put("message", "更新成功");
				
			} catch (ParseException e) {
				
				e.printStackTrace();
				result.put("status", "error");
		    	result.put("message", e);
			}
	    	
	    	return result.toJSONString();
	    }
	    
	    
	    
	    
	    //取回全部客戶(company.html) 這個頁面的資料 不同分店取的分店資料不一樣 管理員取回全部資料, id:使用者id

	    @RequestMapping(value = "/retrive/company/{id}", method = RequestMethod.GET, produces = "application/json")
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
	    @RequestMapping(value = "/retrive/company/{id}/{type}", method = RequestMethod.GET, produces = "application/json")
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
	    @RequestMapping(value = "/retrive/companyProfile/{id}", method = RequestMethod.GET, produces = "application/json")
	    public String selectCompanyProfile(@PathVariable("id") int companyId) {
	    	
	    	Gson gson = new Gson();
	    	List<ContractEntity>contract=CompanyService.findContractByCompany(companyId);
	    	String contractjson = gson.toJson(contract);
	    	return contractjson;
	    }
}
