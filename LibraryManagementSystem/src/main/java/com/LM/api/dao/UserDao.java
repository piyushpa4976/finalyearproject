package com.LM.api.dao;

import java.util.List;

import com.LM.api.model.User;

public interface UserDao
{

	long add(User user);

	List<User> getUserList();

	long delete(long userid);
	
	long update(User user);
	
	User getDetailsById(long id);
	
	String forgetpassword(String user_email,String user_fullname);

	

	String validateusername(String user_email, String user_fullname);

	String passwordreset(String student_email, String resetpasswordtoken,String student_password);

	User login(User user);

	User updateuser(User user);

	String updatepassword(String current_user_password, String user_password, long user_id);

	

	
	

}
