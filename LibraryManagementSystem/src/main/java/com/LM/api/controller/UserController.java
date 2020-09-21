package com.LM.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LM.api.dao.UserDao;
import com.LM.api.model.Book;
import com.LM.api.model.GlobalResponse;
import com.LM.api.model.User;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	
	@GetMapping("/")
	public String hello()
	{
		System.out.println("hello");
		return "/index.html";
	}
	
	
	@PostMapping("/updateuser/{id}")

	public GlobalResponse updateuser(@RequestBody User user,@PathVariable("id") long user_id)
	{
		
		GlobalResponse globalResponse=new GlobalResponse();
		User user1=new User();
		user.setUser_id(user_id);
		
		try {
		user1=userDao.updateuser(user);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			globalResponse.setData(null);
			globalResponse.setMessage(e.getLocalizedMessage());
			globalResponse.setStatus("exception");
			globalResponse.setStatus_code(500);
			globalResponse.setError_message(e.getLocalizedMessage());
		}
		if(user1==null)
		{
			
			globalResponse.setData(null);
			globalResponse.setMessage("update Failed !! || Invalid");
			globalResponse.setStatus("fail");
			globalResponse.setStatus_code(400);
			globalResponse.setError_message(null);
		}
		else
		{
			user1.setUser_password(null);
			user1.setResetpasswordtoken(null);
			globalResponse.setData(user1);
			globalResponse.setMessage("update success!!");
			globalResponse.setStatus("succses");
			globalResponse.setStatus_code(200);
			globalResponse.setError_message(null);
		}
		
		return globalResponse;
	}
	
	
	@PostMapping("/updatepassword/{id}/{current_user_password}")
	public GlobalResponse updatepassword(@PathVariable("current_user_password") String current_user_password,@RequestBody User user,@PathVariable("id") long user_id)
	{
		System.out.print("worked");
		GlobalResponse globalResponse=new GlobalResponse();
		String user1 = null;

		try {
		user1=userDao.updatepassword(current_user_password,user.getUser_password(),user_id);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			globalResponse.setData(null);
			globalResponse.setMessage(e.getLocalizedMessage());
			globalResponse.setStatus("exception");
			globalResponse.setStatus_code(500);
			globalResponse.setError_message(e.getLocalizedMessage());
		}
		if(user1==null)
		{
			
			globalResponse.setData(null);
			globalResponse.setMessage("update Failed !! || Invalid");
			globalResponse.setStatus("fail");
			globalResponse.setStatus_code(400);
			globalResponse.setError_message(null);
		}
		else
		{
			
			globalResponse.setData(user1);
			globalResponse.setMessage("update success!!");
			globalResponse.setStatus("succses");
			globalResponse.setStatus_code(200);
			globalResponse.setError_message(null);
		}
		
		return globalResponse;
	}
	
	
	//passwordresetlink="http://localhost:8080/dbkn/passwordreset?resetpasswordtoken="+generatedString;
	
	@PostMapping("/passwordreset")
	public String passwordreset(@RequestParam String resetpasswordtoken,@RequestParam String user_email,@RequestBody User user)
	{
		return userDao.passwordreset(resetpasswordtoken,user_email,user.getUser_password());
	}
	
	
	
	
	@GetMapping("/usernamevalidate")
	public String usernamevalidate(@RequestParam String user_email,@RequestParam String user_fullname)
	{
		System.out.println(user_email+user_fullname);
		return userDao.validateusername(user_email,user_fullname);
	}
	
	
	@PostMapping("/register")
	public GlobalResponse RegisterStudent(@RequestBody User user)
	{
		GlobalResponse globalResponse=new GlobalResponse();
		long code = 0;
	System.out.println(user.getUser_email());
		try {
		code=userDao.add(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			globalResponse.setData(null);
			globalResponse.setMessage(e.getLocalizedMessage());
			globalResponse.setStatus("exception");
			globalResponse.setStatus_code(500);
			globalResponse.setError_message(e.getLocalizedMessage());
		}
		if(code==400)
		{
			globalResponse.setData(null);
			globalResponse.setMessage("Registration Failed !! || User already registered! click on forget password to reset");
			globalResponse.setStatus("fail");
			globalResponse.setStatus_code(code);
			globalResponse.setError_message(null);
		}
		else if(code==200)
		{
			globalResponse.setData(null);
			globalResponse.setMessage("Registration success!! || ");
			globalResponse.setStatus("succses");
			globalResponse.setStatus_code(code);
			globalResponse.setError_message(null);
		}
		
		return globalResponse;
	}
	
	
	@GetMapping("/userlist")
	public List<User> getallstudents()
	{
		
		return (List<User>) userDao.getUserList();
	}
	
	
	@PostMapping("/forgetpassword")
	public String forgetpassword(@RequestBody User user)
	{
	return userDao.forgetpassword(user.getUser_email(), user.getUser_fullname());	
	}
	
	@PostMapping("/login")
	public GlobalResponse login(@RequestBody User user)
	{
		
		//System.out.println(student.getStudent_reference_number()+student.getStudent_password());
		GlobalResponse globalResponse=new GlobalResponse();
		User user1=new User();
		
		
		try {
		user1=userDao.login(user);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			globalResponse.setData(null);
			globalResponse.setMessage(e.getLocalizedMessage());
			globalResponse.setStatus("exception");
			globalResponse.setStatus_code(500);
			globalResponse.setError_message(e.getLocalizedMessage());
		}
		if(user1==null)
		{
			
			globalResponse.setData(null);
			globalResponse.setMessage("Login Failed !! || Invalid username or password");
			globalResponse.setStatus("fail");
			globalResponse.setStatus_code(400);
			globalResponse.setError_message(null);
		}
		else
		{
			user1.setUser_password(null);
			user1.setResetpasswordtoken(null);
			globalResponse.setData(user1);
			globalResponse.setMessage("Login success!!");
			globalResponse.setStatus("succses");
			globalResponse.setStatus_code(200);
			globalResponse.setError_message(null);
		}
		
		return globalResponse;
	}
	
	
	//import excel
	
	@PostMapping("/uploadExcelFile")
	public GlobalResponse uploadFile(@RequestBody List<Book> importExcelStudentModel)
	{
		
		GlobalResponse globalResponse=new GlobalResponse();
		long code = 0;
		try {
			
				//code =dbknDao.excelimport(importExcelStudentModel);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			globalResponse.setData(null);
			globalResponse.setMessage(e.getLocalizedMessage());
			globalResponse.setStatus("exception");
			globalResponse.setStatus_code(500);
			globalResponse.setError_message(e.getLocalizedMessage());
		}
		if(code==400)
		{
			globalResponse.setData(null);
			globalResponse.setMessage("Import Failed !! || User Details ALready exist");
			globalResponse.setStatus("fail");
			globalResponse.setStatus_code(code);
			globalResponse.setError_message(null);
		}
		else if(code==200)
		{
			globalResponse.setData(null);
			globalResponse.setMessage("Import success!! || ");
			globalResponse.setStatus("succses");
			globalResponse.setStatus_code(code);
			globalResponse.setError_message(null);
		}
		
		return globalResponse;
	}

}
