package tw.com.afw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import tw.com.afw.entity.ContractEntity;
import tw.com.afw.entity.RentEntity;
import tw.com.afw.service.CompanyService;
import tw.com.afw.service.ContractService;
import tw.com.afw.service.OfficeService;
import tw.com.afw.service.RentService;


@RestController
public class RentController {
	
	@Autowired
	private RentService rentService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private OfficeService officeService;
	 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rent/merge/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createRent(@PathVariable("id") Integer id, @RequestBody String rentStr) {
		JSONObject result = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			JSONObject rentObj = (JSONObject) new JSONParser().parse(rentStr);
			Integer year = Integer.valueOf(rentObj.get("rentYear").toString());
			Integer month = Integer.valueOf(rentObj.get("rentMonth").toString());
			Integer contractId = id;
			
			
			RentEntity rentEntity = rentService.findRentByYearAndMonthContractId(contractId, year, month);
			if(rentEntity == null) {
				RentEntity newRent = new RentEntity();
				
				//目前只存年份月份發票號碼備註
				newRent.setRentYear(year);
				newRent.setRentMonth(month);
				newRent.setRentReceipt(!rentObj.get("rentReceipt").toString().isEmpty() ? rentObj.get("rentReceipt").toString() : null);
				newRent.setRentRemark(!rentObj.get("rentRemark").toString().isEmpty() ? rentObj.get("rentRemark").toString() : null);
				newRent.setRentShortage(!rentObj.get("rentShortage").toString().isEmpty() ? Double.valueOf(rentObj.get("rentShortage").toString()) : 0);
				newRent.setRentDate(!rentObj.get("rentDate").toString().isEmpty() ? sdf.parse(rentObj.get("rentDate").toString()) : null);
				newRent.setContractId(contractService.findContracById(contractId));
				
				rentService.ins(newRent);
			} else {
				rentEntity.setRentYear(year);
				rentEntity.setRentMonth(month);
				rentEntity.setRentReceipt(!rentObj.get("rentReceipt").toString().isEmpty() ? rentObj.get("rentReceipt").toString() : null);
				rentEntity.setRentRemark(!rentObj.get("rentRemark").toString().isEmpty() ? rentObj.get("rentRemark").toString() : null);
				rentEntity.setRentShortage(!rentObj.get("rentShortage").toString().isEmpty() ? Double.valueOf(rentObj.get("rentShortage").toString()) : 0);
				rentEntity.setRentDate(!rentObj.get("rentDate").toString().isEmpty() ? sdf.parse(rentObj.get("rentDate").toString()) : null);
				rentEntity.setContractId(contractService.findContracById(contractId));
				
				rentService.update(rentEntity);
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/retrive/rent/{type}/{year}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    public String selectRentByContractTypeAndYear(@PathVariable("type") String type, @PathVariable("year") Integer year, HttpServletRequest request) {
		Gson gson = new Gson();
		JSONObject result = new JSONObject();
		
		try {
			String userCode = (String) request.getSession().getAttribute("usercode");
			
			JSONArray arr = new JSONArray();
			List<ContractEntity> contractArr = companyService.findContractByType(type);
			for(ContractEntity contractEntity : contractArr) {
				JSONObject obj2 = new JSONObject();
				System.err.println(contractEntity.toString());
				if(userCode.equals("AA")) {
					if(contractEntity.getContractEnd().after(Calendar.getInstance().getTime())) {
						System.out.println(contractEntity.getContractEnd());
						long endTimeStamp = contractEntity.getContractEnd().getTime();
						Calendar calendar = Calendar.getInstance();
						calendar.setTimeInMillis(endTimeStamp);

						int eYear = calendar.get(Calendar.YEAR);
						System.out.println(":"+eYear);
						if(year <= eYear) {
							obj2.put("companyId", contractEntity.getCompanyId().getCompanyId());
							obj2.put("companyName", contractEntity.getCompanyId().getCompanyName());
							obj2.put("ein", contractEntity.getCompanyId().getCompanyEin());
							obj2.put("start", contractEntity.getContractStart());
							obj2.put("end", contractEntity.getContractEnd());
							obj2.put("rent", contractEntity.getContractRent());
							obj2.put("deposit", contractEntity.getContractDeposit());
							obj2.put("rentArr", rentService.findRentByContractIdAndYear(contractEntity.getContractId(), year));
							obj2.put("contractId", contractEntity.getContractId());
							if(contractEntity.getContractType().equalsIgnoreCase("o") || contractEntity.getContractType().equalsIgnoreCase("p")) {
								obj2.put("officeNum", officeService.findOffByContract(contractEntity.getContractId()).getOfficeNumber());
							} else {
								obj2.put("officeNum", "");
							}
							
							arr.add(obj2);
						}
					}
						
						
				} else {
					if(contractEntity.getCompanyId().getCompanyCode().equalsIgnoreCase(userCode)) {
						if(contractEntity.getContractEnd().after(Calendar.getInstance().getTime())) {
							System.out.println(contractEntity.getContractEnd());
							long endTimeStamp = contractEntity.getContractEnd().getTime();
							Calendar calendar = Calendar.getInstance();
							calendar.setTimeInMillis(endTimeStamp);

							int eYear = calendar.get(Calendar.YEAR);
							if(year <= eYear) {
								obj2.put("companyId", contractEntity.getCompanyId().getCompanyId());
								obj2.put("companyName", contractEntity.getCompanyId().getCompanyName());
								obj2.put("ein", contractEntity.getCompanyId().getCompanyEin());
								obj2.put("start", contractEntity.getContractStart());
								obj2.put("end", contractEntity.getContractEnd());
								obj2.put("rent", contractEntity.getContractRent());
								obj2.put("deposit", contractEntity.getContractDeposit());
								obj2.put("rentArr", rentService.findRentByContractIdAndYear(contractEntity.getContractId(), year));
								obj2.put("contractId", contractEntity.getContractId());
								if(contractEntity.getContractType().equalsIgnoreCase("o") || contractEntity.getContractType().equalsIgnoreCase("p")) {
									obj2.put("officeNum", officeService.findOffByContract(contractEntity.getContractId()).getOfficeNumber());
								} else {
									obj2.put("officeNum", "");
								}
								arr.add(obj2);
							}
						}
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
	 
}
