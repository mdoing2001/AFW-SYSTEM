package tw.com.afw.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tw.com.afw.entity.UserEntity;
import tw.com.afw.service.UserService;

//Controller > RestController
@RestController
public class LoginController {
	
	 @Autowired
	 private UserService UserService;

	
	 @SuppressWarnings("unchecked")
	 @RequestMapping(value = "/new/user/login", method = RequestMethod.POST, produces = "application/json")
	 public String checkLogin(@RequestBody String newUserJson , HttpServletRequest request)  {
		 JSONObject results = new JSONObject();
		
		 try {
			//抓取前端帳密
			JSONObject obj = (JSONObject) new JSONParser().parse(newUserJson);
			String account  = obj.get("account").toString();
			String password = obj.get("password").toString();
			String branch   = obj.get("branch").toString();
			//驗證帳密
			UserEntity useraccount = UserService.checkAccount(account);
			
			//!= null > == null
			if(useraccount == null){
				results.put("status", "error");
				results.put("message", "account error");
			}else{
				String userid = Integer.toString(useraccount.getUserId());
				String code =useraccount.getBranchId().getBranchCode();
				if(useraccount.getUserPassword().equals(password) && branch.equals(code)){				
					//登入成功將userentity存在session
					request.getSession().setAttribute("account", account);
					request.getSession().setAttribute("branch", branch);
					request.getSession().setAttribute("userid", userid);
					request.getSession().setAttribute("usercode", code);
					results.put("status", "success");
					results.put("message", "success");
				}else{
					results.put("status", "error");
					results.put("message", "password or branch error");
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			results.put("status", "error");
			results.put("message", e);
		}
		 	
		 return results.toJSONString();
	 }
	 

}
