package tw.com.afw.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

	
	 @RequestMapping(value = "/new/user/getAllUser", method = RequestMethod.POST, produces = "application/json")
	 public String userAdd(@RequestBody String newUserJson , HttpServletRequest request)  {
		 Gson gson = new Gson();
		 List<UserEntity> user = UserService.findAll();
		 String userjson   = gson.toJson(user);
		 	
		 return userjson;
	 }
	 
	 
	 


}
