package tw.com.afw.controller;


import javax.servlet.http.HttpSession;

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
public class UserController {
	
	 @Autowired
	 private UserService UserService;
	
	 @RequestMapping(value = "/new/user/login", method = RequestMethod.POST)
	 public String checkLogin(@RequestBody String newUserJson)  {
		 JSONObject results = new JSONObject();
		 //HttpSession session= HttpServletRequest.getSession();
		 try {
			//抓取前端帳密
			JSONObject obj = (JSONObject) new JSONParser().parse(newUserJson);
			String account  = obj.get("account").toString();
			String password = obj.get("password").toString();
			
			
			//驗證帳密
			UserEntity useraccount=UserService.checkAccount(account);
			if(useraccount == null){
				results.put("status", "error");
				results.put("message", "帳號輸入錯誤");
			}else{
				UserEntity userpassword = UserService.checkpassword(account, password);
				if(userpassword == null){
					results.put("status", "error");
					results.put("message", "密碼輸入錯誤");
				}else{
					results.put("status", "success");
					results.put("message", "success");
					//登入成功將userentity存在session
					
				}
			}
			
		
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	
		 return results.toJSONString();
	 }

}
