package tw.com.afw.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.afw.entity.UserEntity;
import tw.com.afw.service.UserService;


@Controller
public class LoginController {
	
	 @Autowired
	 private UserService UserService;

	
	 @RequestMapping(value = "/new/user/login", method = RequestMethod.POST)
	 public String checkLogin(@RequestBody String newUserJson , HttpServletRequest request)  {
		 JSONObject results = new JSONObject();
		
		 try {
			//抓取前端帳密
			JSONObject obj = (JSONObject) new JSONParser().parse(newUserJson);
			String account  = obj.get("account").toString();
			String password = obj.get("password").toString();
			String branch   = obj.get("branch").toString();
			//驗證帳密
			UserEntity useraccount=UserService.checkAccount(account);
			String userid = Integer.toString(useraccount.getUser_Id());
			if(useraccount != null){
				results.put("status", "error");
				results.put("message", "帳號輸入錯誤");
			}else{
				
				//if(useraccount.getUser_Password().equals(password) && userid.equals(branch)){
				if(useraccount.getUser_Password().equals(password)){
				
					//登入成功將userentity存在session
					request.getSession().setAttribute("account", account);
					request.getSession().setAttribute("branch", branch);
					request.getSession().setAttribute("userid", userid);
					results.put("status", "success");
					results.put("message", "success");
				}else{
					results.put("status", "error");
					results.put("message", "密碼輸入錯誤");
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
