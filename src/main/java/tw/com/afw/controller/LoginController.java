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


@RestController
public class LoginController {
	
	 @Autowired
	 private UserService userService;

	
	 @SuppressWarnings("unchecked")
	 @RequestMapping(value = "/new/user/login", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	 public String checkLogin(@RequestBody String newUserJson , HttpServletRequest request)  {
		 JSONObject results = new JSONObject();
		
		 try {
			//抓取前端帳密
			JSONObject obj = (JSONObject) new JSONParser().parse(newUserJson);
			String account  = obj.get("account").toString();
			String password = obj.get("password").toString();
			String branch   = obj.get("branch").toString();
			//驗證帳密
			UserEntity user = userService.checkAccount(account);
			
			if(user == null) {
				results.put("status", "error");
				results.put("message", "帳號有誤");
			} else {
				String code =user.getBranchId().getBranchCode();
				if(user.getUserPassword().equals(password) && branch.equals(code)){				
					//登入成功將userentity存在session
					//request.getSession().setAttribute("account", account);
					request.getSession().setAttribute("branch", branch);
					request.getSession().setAttribute("userid", user);
					request.getSession().setAttribute("usercode", code);
					results.put("status", "success");
					results.put("message", "success");
					results.put("userCode", code);
				}else{
					results.put("status", "error");
					results.put("message", "密碼 或 分店 有誤");
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			results.put("status", "error");
			results.put("message", e);
		}
		 	
		 return results.toJSONString();
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	 @RequestMapping(value = "/new/user/logout", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	 public String checkLogout(HttpServletRequest request) {
		 JSONObject results = new JSONObject();
		 
		 try {
			 request.getSession().invalidate();
			 results.put("status", "success");
			 results.put("message", "success");
		 } catch (Exception e) {
			 e.printStackTrace();
			 results.put("status", "error");
			 results.put("message", e);
		 }
		 return results.toJSONString();
	 }
	 

}
