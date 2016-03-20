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
public class UserController {
	
	 @Autowired
	 private UserService UserService;

	
	 @RequestMapping(value = "/new/user/usercc", method = RequestMethod.POST)
	 public String userAdd(@RequestBody String newUserJson , HttpServletRequest request)  {
		

		 	
		 return "";
	 }
	 
	 
	 


}
