package tw.com.afw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

import com.google.gson.Gson;

import tw.com.afw.entity.BranchEntity;
import tw.com.afw.entity.CompanyEntity;
import tw.com.afw.entity.ContractEntity;
import tw.com.afw.entity.RemitEntity;
import tw.com.afw.entity.RentEntity;
import tw.com.afw.service.BranchService;
import tw.com.afw.service.CompanyService;
import tw.com.afw.service.ContractService;
import tw.com.afw.service.OfficeService;
import tw.com.afw.service.RemitService;
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
	
	@Autowired
	private RemitService remitService;
	
	@Autowired
	private BranchService branchService;
	 
	
	//rentTable.html
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rent/merge/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String mergeRent(@PathVariable("id") Integer id, @RequestBody String rentStr) {
		JSONObject result = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			JSONObject rentObj = (JSONObject) new JSONParser().parse(rentStr);
			Integer year = Integer.valueOf(rentObj.get("rentYear").toString());
			Integer month = Integer.valueOf(rentObj.get("rentMonth").toString());
			boolean addReRent = Boolean.valueOf(rentObj.get("addReRent").toString());
			Integer contractId = id;
			
			RentEntity rentEntity2 = rentService.findRentByYearAndMonthContractId(contractId, year, month);
			
			if(rentEntity2 == null) {
				RentEntity newRent = new RentEntity();
				
				//目前只存年份月份發票號碼備註
				newRent.setRentYear(year);
				newRent.setRentMonth(month);
				newRent.setRentReceipt(!rentObj.get("rentReceipt").toString().isEmpty() ? rentObj.get("rentReceipt").toString() : null);
				newRent.setRentRemark(!rentObj.get("rentRemark").toString().isEmpty() ? rentObj.get("rentRemark").toString() : null);
				newRent.setRentShortage(!rentObj.get("rentShortage").toString().isEmpty() ? Double.valueOf(rentObj.get("rentShortage").toString()) : 0);
				newRent.setRentDate(!rentObj.get("rentDate").toString().isEmpty() ? sdf.parse(rentObj.get("rentDate").toString()) : null);
				newRent.setRentAddDate(!rentObj.get("rentDate").toString().isEmpty() ? sdf.parse(rentObj.get("rentDate").toString()) : null);
				newRent.setRentMoney(!rentObj.get("rentMoney").toString().isEmpty() ? Double.valueOf(rentObj.get("rentMoney").toString()) : null);
				newRent.setContractId(contractService.findContracById(contractId));
				
				rentService.ins(newRent);
				result.put("status", "error");
		    	result.put("message", "請確認此月份是否完成對帳");
		    	return result.toJSONString();
			} else {
				rentEntity2.setRentYear(year);
				rentEntity2.setRentMonth(month);
				rentEntity2.setRentReceipt(!rentObj.get("rentReceipt").toString().isEmpty() ? rentObj.get("rentReceipt").toString() : null);
				rentEntity2.setRentRemark(!rentObj.get("rentRemark").toString().isEmpty() ? rentObj.get("rentRemark").toString() : null);
				rentEntity2.setRentShortage(!rentObj.get("rentShortage").toString().isEmpty() ? Double.valueOf(rentObj.get("rentShortage").toString()) : 0);
				rentEntity2.setRentDate(!rentObj.get("rentDate").toString().isEmpty() ? sdf.parse(rentObj.get("rentDate").toString()) : null);
				rentEntity2.setContractId(contractService.findContracById(contractId));
				
				rentService.update(rentEntity2);
			}
			
			//新增reRent 一次只新增下個月份
			RentEntity rentEntity = null;
			if(addReRent) {
				if(month == 12) {
					rentEntity = rentService.findRentByYearAndMonthContractId(contractId, year+1, 1);
				} else {
					rentEntity = rentService.findRentByYearAndMonthContractId(contractId, year, month+1);
				}
					
				if(rentEntity == null) {
					
					RentEntity newRent = new RentEntity();
					
					newRent.setRentYear(year);
					newRent.setRentMonth(month + 1);
					newRent.setRentReceipt(!rentObj.get("rentReceipt").toString().isEmpty() ? rentObj.get("rentReceipt").toString() : null);
					newRent.setRentRemark(!rentObj.get("rentRemark").toString().isEmpty() ? rentObj.get("rentRemark").toString() : null);
					newRent.setRentShortage(!rentObj.get("rentShortage").toString().isEmpty() ? Double.valueOf(rentObj.get("rentShortage").toString()) : 0);
					newRent.setRentDate(!rentObj.get("rentDate").toString().isEmpty() ? sdf.parse(rentObj.get("rentDate").toString()) : null);
					newRent.setRentAddDate(rentEntity2.getRentAddDate());
					newRent.setRentAdsl(rentEntity2.getRentAdsl());
					newRent.setRentBusiness(rentEntity2.getRentAdsl());
					newRent.setRentManagement(rentEntity2.getRentManagement());
					newRent.setRentMoney(rentEntity2.getRentMoney());
					newRent.setRentPower(rentEntity2.getRentPower());
					newRent.setRentPayType(rentEntity2.getRentPayType());
					newRent.setRentOther(rentEntity2.getRentOther());
					newRent.setContractId(contractService.findContracById(contractId));
					
					rentService.ins(newRent);
					
				} else {
					result.put("status", "error");
			    	result.put("message", "請檢查新增月份有無對帳資料");
			    	return result.toJSONString();
				}
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
	@RequestMapping(value = "/reRent/merge/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String mergeReRent(@PathVariable("id") Integer id, @RequestBody String rentStr) {
		JSONObject result = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			JSONObject rentObj = (JSONObject) new JSONParser().parse(rentStr);
			Date rentDate = sdf.parse(rentObj.get("rentDateO").toString());
			Date rentDateOAdd = sdf.parse(rentObj.get("rentDateOAdd").toString());
			String rentPayType = rentObj.get("rentPayTypeO").toString();
			String rentRemark = rentObj.get("rentRemarkO").toString();
			Double money = Double.valueOf(rentObj.get("rentMoneyO").toString());
			
			
			RentEntity rentEntity = rentService.findRentById(id);
			if(rentEntity != null) {
				rentEntity.setRentAddDate(rentDateOAdd);
				rentEntity.setRentDate(rentDate);
				rentEntity.setRentPayType(rentPayType);
				rentEntity.setRentMoney(money);
				rentEntity.setRentRemark(rentRemark);
				
				rentService.update(rentEntity);
			}
			
			result.put("status", "success");
			result.put("message", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
	    	result.put("message", e);
		}
		
		return result.toJSONString();
	}
	
	
	//rentTable.html
	//TODO 合約end date必須調整為到時候 收租表不在顯示按鈕
	//TODO 有可能出現一間公司的合約會出現在兩間分店上
	//TODO A,B:一個月繳一次 不一定開發票
		 //C,D,E:(rule1:兩個月收一次 開一張 rule2:兩個月收一次 開兩張 rule3:一個月收一次 開一張 rule4:收租金 不開發票)需要討論
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
				if(userCode.equals("AA")) {
					if(contractEntity.getContractEnd().after(Calendar.getInstance().getTime()) && contractEntity.getContractStart().before(Calendar.getInstance().getTime())) {
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
	
	//收費清單
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rent/list/merge/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String mergeRentListForOfficeAndPersonal(@PathVariable("id") Integer id, @RequestBody String rentStr) {
		JSONObject result = new JSONObject();
		
		try {
			JSONObject rentObj = (JSONObject) new JSONParser().parse(rentStr);
			Integer year = Integer.valueOf(rentObj.get("rentYear").toString());
			Integer month = Integer.valueOf(rentObj.get("rentMonth").toString());
			Integer contractId = id;
			
			RentEntity rentEntity = rentService.findRentByYearAndMonthContractId(contractId, year, month);
			if(rentEntity == null) {
				RentEntity newRent = new RentEntity();
				
				//收費清單
				newRent.setRentYear(year);
				newRent.setRentMonth(month);
				newRent.setRentManagement(!rentObj.get("rentManagement").toString().isEmpty() ? Double.valueOf(rentObj.get("rentManagement").toString()) : 0);
				newRent.setRentPower(!rentObj.get("rentPower").toString().isEmpty() ? Double.valueOf(rentObj.get("rentPower").toString()) : 0);
				newRent.setRentAdsl(!rentObj.get("rentAdsl").toString().isEmpty() ? Double.valueOf(rentObj.get("rentAdsl").toString()) : 0);
				newRent.setRentBusiness(!rentObj.get("rentBusiness").toString().isEmpty() ? Double.valueOf(rentObj.get("rentBusiness").toString()) : 0);
				newRent.setRentOther(!rentObj.get("rentOther").toString().isEmpty() ? Double.valueOf(rentObj.get("rentOther").toString()) : 0);
				newRent.setContractId(contractService.findContracById(contractId));
				
				rentService.ins(newRent);
			} else {
				rentEntity.setRentYear(year);
				rentEntity.setRentMonth(month);
				rentEntity.setRentManagement(!rentObj.get("rentManagement").toString().isEmpty() ? Double.valueOf(rentObj.get("rentManagement").toString()) : 0);
				rentEntity.setRentPower(!rentObj.get("rentPower").toString().isEmpty() ? Double.valueOf(rentObj.get("rentPower").toString()) : 0);
				rentEntity.setRentAdsl(!rentObj.get("rentAdsl").toString().isEmpty() ? Double.valueOf(rentObj.get("rentAdsl").toString()) : 0);
				rentEntity.setRentBusiness(!rentObj.get("rentBusiness").toString().isEmpty() ? Double.valueOf(rentObj.get("rentBusiness").toString()) : 0);
				rentEntity.setRentOther(!rentObj.get("rentOther").toString().isEmpty() ? Double.valueOf(rentObj.get("rentOther").toString()) : 0);
				rentEntity.setContractId(contractService.findContracById(contractId));
				
				rentService.update(rentEntity);
			}
			
			result.put("status", "success");
			result.put("message", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
	    	result.put("message", e);
		}
		return result.toJSONString();
	}
	
	//對帳 新增
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rent/list/create/{year}/{id}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String createReconciliation(@PathVariable("year") Integer year, @PathVariable("id") Integer contractId, @RequestBody String rentStr) {
		JSONObject result = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			JSONObject rentObj = (JSONObject) new JSONParser().parse(rentStr);
			if(rentObj != null) {
				Integer money = Integer.valueOf(rentObj.get("rentMoney").toString());
				Date date = sdf.parse(rentObj.get("rentDate").toString());
				Date rentDateAdd = sdf.parse(rentObj.get("rentDateAdd").toString());
				String payType = null != rentObj.get("rentPayType") ? rentObj.get("rentPayType").toString() : null;
				String reMark = null != rentObj.get("rentRemark") ? rentObj.get("rentRemark").toString() : null;
				
				
				ContractEntity cEntity = contractService.findContracById(contractId);
				if(cEntity != null) {
					int maxMonth = 0;
					int maxMonthLast = 0;
					RentEntity maxNonePayMonthRentEntity = null;
					RentEntity maxNonePayMonthRentLastEntity = null;
					//今年
					List<RentEntity> rentArr = rentService.findRentByContractIdAndYear(contractId, year);
					if(rentArr != null && rentArr.size() != 0) {
						//抓取exist最後一筆rent的月份
						for(RentEntity entity : rentArr) {
							if(entity.getRentDate() != null && entity.getRentDate().equals(date)) {
								result.put("status", "error");
								result.put("message", "請檢查是否有重複輸入");
								return result.toJSONString();
							} else {
								if(maxMonth == 0) {
									maxMonth = entity.getRentMonth();
								} else if(maxMonth != 0 && maxMonth < entity.getRentMonth()) {
									maxMonth = entity.getRentMonth();
								}
								
								if(maxMonth >= entity.getRentMonth() && (entity.getRentMoney() == null || entity.getRentMoney() == 0)) {
									maxNonePayMonthRentEntity = entity;
								}
							}
						}
					}
					System.out.println("maxMonth:"+maxMonth);
					
					//去年
					List<RentEntity> rentLastArr = rentService.findRentByContractIdAndYear(contractId, year-1);
					if(rentLastArr != null && rentLastArr.size() != 0) {
						//抓取exist最後一筆rent的月份
						for(RentEntity entity : rentLastArr) {
							if(entity.getRentDate().equals(date)) {
								result.put("status", "error");
								result.put("message", "請檢查是否有重複輸入");
								return result.toJSONString();
							} else {
								if(maxMonthLast == 0) {
									maxMonthLast = entity.getRentMonth();
								} else if(maxMonthLast != 0 && maxMonthLast < entity.getRentMonth()) {
									maxMonthLast = entity.getRentMonth();
								}
								
								if(maxMonth >= entity.getRentMonth() && (entity.getRentMoney() == null || entity.getRentMoney() == 0)) {
									maxNonePayMonthRentLastEntity = entity;
								}
							}
						}
					}
					System.out.println("maxMonthLast:"+maxMonthLast);
					
					//if(maxMonthLast != 0) {
						RentEntity newEntity = new RentEntity();
						RentEntity newEntityTwo = new RentEntity();
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						int month = cal.get(Calendar.MONTH)+1;
						int rentMoney = (int) cEntity.getContractRent();
						
						//針對辦公室跟座位做處理
						if(cEntity.getContractType().equalsIgnoreCase("o") || cEntity.getContractType().equalsIgnoreCase("p")) {
							if(maxNonePayMonthRentEntity == null) {
								result.put("status", "error");
								result.put("message", "請檢查收費清單是否有填寫");
								return result.toJSONString();
							}
							
							if(maxMonth != 0) {
								maxNonePayMonthRentEntity.setRentMoney(Double.valueOf(String.valueOf(money)));
								maxNonePayMonthRentEntity.setRentDate(date);
								maxNonePayMonthRentEntity.setRentPayType(payType);
								maxNonePayMonthRentEntity.setRentRemark(reMark);
								maxNonePayMonthRentEntity.setRentAddDate(rentDateAdd);
								maxNonePayMonthRentEntity.setRentShortage(Double.valueOf(money-(rentMoney + rentService.allRentPriceByEntity(maxNonePayMonthRentEntity))));
								rentService.update(maxNonePayMonthRentEntity);
							//TODO 跨年度
							} else {
								
							}
							
						} else {
							
							boolean checkRentTwoMonth = (money == rentMoney + rentMoney) ? true : false;
							boolean checkRentOneMonth = (rentMoney == money) ? true : false;
							//當年度
							if(maxMonth != 0) {
								boolean checkMonth = (maxMonth == month || maxMonth == month-1) ? true : false;
								//兩個月繳一次
								if(checkRentTwoMonth && checkMonth) {
									newEntity.setContractId(cEntity);
									newEntity.setRentPayType(payType);
									newEntity.setRentDate(date);
									newEntity.setRentAddDate(rentDateAdd);
									newEntity.setRentYear(year);
									newEntity.setRentRemark(reMark);
									newEntity.setRentMonth(maxMonth+1);
									newEntity.setRentMoney(cEntity.getContractRent());
									newEntity.setRentShortage(0.00);
									
									newEntityTwo.setContractId(cEntity);
									newEntityTwo.setRentPayType(payType);
									newEntityTwo.setRentDate(date);
									newEntityTwo.setRentAddDate(rentDateAdd);
									newEntityTwo.setRentYear(year);
									newEntityTwo.setRentRemark(reMark);
									newEntityTwo.setRentMonth(maxMonth+2);
									newEntityTwo.setRentMoney(cEntity.getContractRent());
									newEntityTwo.setRentShortage(0.00);
									
									rentService.ins(newEntity);
									rentService.ins(newEntityTwo);
								} else if(checkRentOneMonth && checkMonth) {
									newEntity.setContractId(cEntity);
									newEntity.setRentPayType(payType);
									newEntity.setRentDate(date);
									newEntity.setRentAddDate(rentDateAdd);
									newEntity.setRentYear(year);
									newEntity.setRentRemark(reMark);
									newEntity.setRentMonth(maxMonth+1);
									newEntity.setRentMoney(cEntity.getContractRent());
									newEntity.setRentShortage(0.00);
									
									rentService.ins(newEntity);
								} else {
									newEntity.setContractId(cEntity);
									newEntity.setRentPayType(payType);
									newEntity.setRentDate(date);
									newEntity.setRentAddDate(rentDateAdd);
									newEntity.setRentYear(year);
									newEntity.setRentRemark(reMark);
									newEntity.setRentMonth(maxMonth+1);
									newEntity.setRentMoney(Double.valueOf(String.valueOf(money)));
									newEntity.setRentShortage(Double.valueOf(String.valueOf(money-rentMoney)));
									
									rentService.ins(newEntity);
								}
							//跨年度
							} else if(maxMonth == 0 && maxMonthLast == 12) {
								
								//兩個月繳一次
								if(checkRentTwoMonth) {
									newEntity.setContractId(cEntity);
									newEntity.setRentPayType(payType);
									newEntity.setRentDate(date);
									newEntity.setRentAddDate(rentDateAdd);
									newEntity.setRentYear(year);
									newEntity.setRentRemark(reMark);
									newEntity.setRentMonth(maxMonth+1);
									newEntity.setRentMoney(cEntity.getContractRent());
									newEntity.setRentShortage(0.00);
									
									newEntityTwo.setContractId(cEntity);
									newEntityTwo.setRentPayType(payType);
									newEntityTwo.setRentDate(date);
									newEntityTwo.setRentAddDate(rentDateAdd);
									newEntityTwo.setRentYear(year);
									newEntityTwo.setRentRemark(reMark);
									newEntityTwo.setRentMonth(maxMonth+2);
									newEntityTwo.setRentMoney(cEntity.getContractRent());
									newEntityTwo.setRentShortage(0.00);
									
									rentService.ins(newEntity);
									rentService.ins(newEntityTwo);
								} else if(checkRentOneMonth) {
									newEntity.setContractId(cEntity);
									newEntity.setRentPayType(payType);
									newEntity.setRentDate(date);
									newEntity.setRentAddDate(rentDateAdd);
									newEntity.setRentYear(year);
									newEntity.setRentRemark(reMark);
									newEntity.setRentMonth(maxMonth+1);
									newEntity.setRentMoney(cEntity.getContractRent());
									newEntity.setRentShortage(0.00);
									
									rentService.ins(newEntity);
								} else {
									newEntity.setContractId(cEntity);
									newEntity.setRentPayType(payType);
									newEntity.setRentDate(date);
									newEntity.setRentAddDate(rentDateAdd);
									newEntity.setRentYear(year);
									newEntity.setRentRemark(reMark);
									newEntity.setRentMonth(maxMonth+1);
									newEntity.setRentMoney(Double.valueOf(String.valueOf(money)));
									newEntity.setRentShortage(Double.valueOf(String.valueOf(money-rentMoney)));
									
									rentService.ins(newEntity);
								}
							} else if(maxMonth == 0 && maxMonthLast != 12) {
								newEntity.setContractId(cEntity);
								newEntity.setRentPayType(payType);
								newEntity.setRentDate(date);
								newEntity.setRentAddDate(rentDateAdd);
								newEntity.setRentYear(year-1);
								newEntity.setRentRemark(reMark);
								newEntity.setRentMonth(maxMonth+1);
								newEntity.setRentMoney(Double.valueOf(String.valueOf(money)));
								newEntity.setRentShortage(Double.valueOf(String.valueOf(money-rentMoney)));
								
								rentService.ins(newEntity);
							}
						}
						
						result.put("status", "success");
						result.put("message", "success");
					}
				}
			//}
			
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
	
	//根據帳號末五碼或公司名稱找出company
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/retrive/rent/company/{account}/{year}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	public String selectRentByAccount5(@PathVariable("account") String account, @PathVariable("year") Integer year, HttpServletRequest request) {
		Gson gson = new Gson();
		JSONObject result = new JSONObject();
		
		try {
			String userCode = (String) request.getSession().getAttribute("usercode");
			if(null == userCode) {
				result.put("status", "error");
				result.put("message", false);
		    	return result.toJSONString();
			}
			System.out.println("selectRentByAccount5:"+userCode);
			System.out.println("selectRentByAccount5:"+account);
			System.out.println("selectRentByAccount5:"+year);
			if(userCode.equals("AA")) {
				JSONArray arr = new JSONArray();
				RemitEntity rEntity = null;
				CompanyEntity cEntity = null;
				//根據帳號末五碼或公司名稱搜尋
				if(!StringUtils.isNumeric(account)) {
					List<CompanyEntity> companyArr = companyService.findCompanyByCompanyName(account.trim());
					if(companyArr != null && companyArr.size() == 1) {
						cEntity = companyArr.get(0);
					} else {
						result.put("status", "error");
				    	result.put("message", "請確認有無輸入錯誤或請輸入公司全名");
				    	return result.toJSONString();
					}
				} else {
					rEntity = remitService.findRemitByAccount5(Integer.valueOf(account));
					cEntity = rEntity.getCompanyId();
				}
				
				if(rEntity != null || cEntity != null) {
					List<ContractEntity> contractArr = contractService.findContractByCompanyId(cEntity.getCompanyId());
					for(ContractEntity contractEntity : contractArr) {
						//TODO 需要加一個起始日期的判斷
						if(contractEntity.getContractEnd().after(Calendar.getInstance().getTime()) && contractEntity.getContractStart().before(Calendar.getInstance().getTime())) {
							JSONObject obj = new JSONObject();
							obj.put("companyId", cEntity.getCompanyId());
							obj.put("companyName", cEntity.getCompanyName());
							obj.put("contractType", contractEntity.getContractType());
							obj.put("contractId", contractEntity.getContractId());
							obj.put("rent", contractEntity.getContractRent());
							obj.put("rentArr", rentService.findRentByContractIdAndYear(contractEntity.getContractId(), year));
							arr.add(obj);
						}
					}
					
					result.put("status", "success");
					result.put("message", gson.toJson(arr));
				} else {
					result.put("status", "error");
			    	result.put("message", "This accont is not exist");
				}
				
			} else {
				result.put("status", "error");
		    	result.put("message", "You do not have permission");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
	    	result.put("message", e);
		}
		return result.toJSONString();
		
	}
	
	//select 已經對帳的rent
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/retrive/reRent/{branch}/{year}/{month}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	public String selectReRentByBranchAndYearAndMonth(@PathVariable("branch") String branch, @PathVariable("year") Integer year, 
		@PathVariable("month") Integer month, HttpServletRequest request) {
		Gson gson = new Gson();
		JSONObject result = new JSONObject();
		
		try {
			
			//找出branch的的現在合約
			BranchEntity bEntity = branchService.findBranchByBranchCode(branch);
			JSONArray arr = new JSONArray();
			if(bEntity != null) {
				List<ContractEntity> contractArr = contractService.findContractByBranchId(bEntity.getBranchId());
				//找出每張合約的租金表
				for(ContractEntity contractEntity : contractArr) {
					if(contractEntity.getContractEnd().after(Calendar.getInstance().getTime()) && contractEntity.getContractStart().before(Calendar.getInstance().getTime())) {
						//System.out.println(contractEntity.toString());
						String contractType = contractEntity.getContractType();
						String companyName = contractEntity.getCompanyId().getCompanyName();
						
						//TODO 跨年分必須處理
						List<RentEntity> rentArr = rentService.findRentByContractIdAndYear(contractEntity.getContractId(), year);
						for(RentEntity entity : rentArr) {
							JSONObject obj = new JSONObject();
							
							Calendar cal = Calendar.getInstance();
							cal.setTime(entity.getRentAddDate());
							int month2 = cal.get(Calendar.MONTH) + 1;
							
							//收費清單的date 為 null 但還為對帳
							if(null != entity.getRentDate() && month2 == month) {
								obj.put("rentId", entity.getRentId());
								obj.put("rentPayType", entity.getRentPayType());
								obj.put("money", entity.getRentMoney());
								obj.put("date", entity.getRentDate());
								obj.put("addDate", entity.getRentAddDate());
								obj.put("type", contractType);
								obj.put("companyName", companyName);
								obj.put("month", entity.getRentMonth());
								obj.put("remark", entity.getRentRemark());
								obj.put("rentShortage", entity.getRentShortage());
								
								arr.add(obj);
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
	
	private String payRuleLogic() {
		return "";
	}
	
}
