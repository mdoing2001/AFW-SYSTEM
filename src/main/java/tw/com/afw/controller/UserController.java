package tw.com.afw.controller;


import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import tw.com.afw.entity.UserEntity;
import tw.com.afw.service.UserService;


@RestController
public class UserController {
	
	 @Autowired
	 private UserService UserService;
	 
	 @SuppressWarnings("unchecked")
	 @RequestMapping(value = "/new/user/getAllUser", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
	 public String findAllUser()  {
		 JSONObject result = new JSONObject();
		 String userjson = null;
		 Gson gson = new Gson();
		 try {
			 List<UserEntity> user = UserService.findAll();
			 userjson = gson.toJson(user);
		 } catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
			result.put("message", e);
		 }
		 
		 result.put("status", "success");
		 result.put("message", userjson);
		 
		 return result.toJSONString();
	 }
	 
	 
	 


}
