package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.junit.*;

import tw.com.afw.dao.UserDao;
import tw.com.afw.service.UserService;
import tw.com.afw.utility.BaseTest;

public class TestExample extends BaseTest {

	public static void main(String[] args) {
        
        //Create Spring application context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring-database.xml");
         
        //Get service from context. (service's dependency (ProductDAO) is autowired in ProductService)
        UserService service = ctx.getBean(UserService.class);
         
        //Test transaction rollback (duplicated key)
         
        try {
        	//service.findAll();
        } catch (DataAccessException dataAccessException) {
        	dataAccessException.printStackTrace();
        }
         
        //Test element list after rollback
        System.out.println("listAll: " + service.findAll());
         
        ctx.close();
    }
	
	@Autowired
	UserDao dao;
	
	@Test
	public void testFindAll(){
		dao.findAll();
	}
}
