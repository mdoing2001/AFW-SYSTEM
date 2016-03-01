package tw.com.afw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import tw.com.afw.entity.CompanyEntity;
import tw.com.afw.service.CompanyService;


@Controller
public class CompanyController {

	
	
	 @Autowired
	 private CompanyService CompanyService;
	 
	 
	 
	    //-------------------Create a User--------------------------------------------------------
     
	    @RequestMapping(value = "/XXX", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody CompanyEntity company,    UriComponentsBuilder ucBuilder) {
	        //System.out.println("Creating Company " + company.getCompany_Name());

	        CompanyService.ins(company);
	     
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/xxx").buildAndExpand(company.getCompany_Id()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	 
	 
	 
	 
	 
}
