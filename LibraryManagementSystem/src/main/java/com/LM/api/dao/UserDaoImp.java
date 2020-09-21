package com.LM.api.dao;

import java.util.List;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.LM.api.model.User;
import com.LM.api.utilityclasses.EmailService;
import com.LM.api.utilityclasses.Security;

@Transactional(readOnly = true)
@Repository
public class UserDaoImp implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	private EmailService emailService=new EmailService();

	private Security security=new Security();
	
	@Transactional
	@Override
	public long add(User user) {
		//session=sessionFactory.getCurrentSession();
		long m = 0;
//		boolean exits = false;
//		String registered = null;
		boolean exists=true;
		if (user.getUser_fullname() == null || user.getUser_email() == null) {

			System.out.println(user.getUser_email());
			return 408;
		}

		Session hsession = sessionFactory.getCurrentSession();

		try {

			exists = hsession
					.createQuery(
							"select 1 from lmuser u where u.user_email='" + user.getUser_email() + "' and u.user_fullname='"+user.getUser_fullname()+"'")
					.list().size() != 0;

			System.out.println(exists);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			m = 400;
		}
		if (!exists)
		{
			System.out.println(user.getUser_password());
			
			user.setUser_registered("true");
			user.setUser_password(security.md5(user.getUser_password()));
			System.out.println(security.md5(user.getUser_password()));
			hsession.save(user);
		sendregistrationmail(user);
			//sendregistrationmail(sm);
			m = 200;
		}
		else
		{
			m=400;
		}

		return m;

	}

	@Override
	public List<User> getUserList() {
		Session session = sessionFactory.getCurrentSession();
		// Create CriteriaBuilder
		CriteriaBuilder builder = session.getCriteriaBuilder();

		// Create CriteriaQuery
		CriteriaQuery<User> criteria = builder.createQuery(User.class);

		// Specify criteria root
		criteria.from(User.class);

		// Execute query
		List<User> entityList = session.createQuery(criteria).getResultList();
		return entityList;
	}

	@Override
	public long delete(long userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long update(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getDetailsById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	private void sendregistrationmail(User user) {
		String toEmail = user.getUser_email();

		String subject = "Library Management System - Online Registration Details";
		String body = "Hello " + user.getUser_fullname().toString() + "\r\n" + "\r\n"
				+ "You have completed the Registration at LMS." + "\r\n" + "Your Username is : <b>"
				+ user.getUser_id() + "</b>" + "\r\n" + "\r\n"
				+ "Note 1: Please note down the Reference number given above, which will be required for further interaction with LMS.\r\n"
				+ "\r\n" +
				// "Note 2: Application will be treated as incomplete/rejected unless
				// accompanied by the PART-II Registration - which again consist of two parts -
				// filling up payment details of Examination Fee (where applicable)and uploading
				// Photograph and Signature.\r\n" +

				// "For payment of exam fees, upload of photograph and signature proceed to
				// PART-II Registration.\r\n" +
				"Best Regards,\r\n" + "Candidate Care\r\n" + "LMS Online Application Portal\r\n" + "\r\n"
				+ "This is an auto generated reply to let you know that your registration in the LMS Online Application Portal has been made. Do not reply to this Email id";

		emailService.sendmail(toEmail, subject, body);

	}
	
	
	
	@Transactional
	@Override
	public String forgetpassword(String user_email, String user_fullname) {
		Session hsession = sessionFactory.getCurrentSession();
		boolean exits = hsession.createQuery("select 1 from lmuser u where u.user_email='" + user_email
				+ "' and u.user_fullname='" + user_fullname + "'").list().size() != 0;
		if (!exits) {
			return "Details doest not exist... please register in the portal first.";
		}
		// token generation
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 32;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		String toEmail = user_email;
		String passwordresetlink = null;
		if (hsession.createQuery("update lmuser u set u.resetpasswordtoken= '" + generatedString
				+ "' where u.user_email='" + user_email + "'").executeUpdate() == 1) {
			passwordresetlink = "file:///C:/Users/Sweet/OneDrive/finalyearproject/LibraryManagementSystemUI/resetpassword.html?resetpasswordtoken="
					+ generatedString + "&user_email=" + user_email;
		}
		;
		String subject = "Forgot password request at LM";
		String body = "Hello " + user_fullname + "\r\n \r\n" + "\r\n"
				+ "we have got a 'forgot password' request for your account " + toEmail
				+ " at LM from the ip address --------, if you do not recognize this request you might simply ignore this link, your account is still safe."
				+ " \r\n \r\n In order to change your password please follow this link:\r\n \r\n" + "\r\n"
				+ passwordresetlink;
//		String body = "Hello <b>" + student_name + "</b><br><br>" + "\r\n"
//			+ "<p>we have got a 'forgot password' request for your account " + toEmail
//				+ " at DBKN from the ip address --------, if you do not recognize this request you might simply ignore this link, your account is still safe."
//				+ " <br><br> In order to change your password please follow this link:<p> <br><br>"
//				+ "<br>"+passwordresetlink;
//		String body= "<html>" +
//				"<head><title>"+subject+"</title></head>" +
//				"<body>" +
//				"Click <a href=\"" + passwordresetlink + "\">here</a> to activate your free subscription." +
//				"</body>" +
//				"</html>";		
//		 String body = "<i>Greetings!</i><br>";
//		 body += "<b>Wish you a nice day!</b><br>";
//		 body += "<font color=red>Duke</font>";

		return emailService.sendmail(toEmail, subject, body);
	}

	@Override
	public User login(User user) {
		User msg;
		Session session = sessionFactory.getCurrentSession();
		// boolean exits=session.createQuery("select 1 from StudentModel sm where
		// sm.student_email='"+student_email+"'").getSingleResult()!=null;
		if (user.getUser_name() == null || user.getUser_password() == null) {
			return null;
		}
		boolean validcredentialexits = false;
		try {
			validcredentialexits = session.createQuery("select 1 from lmuser u where u.user_email='"
					+ user.getUser_name() + "' and u.user_password='" + security.md5(user.getUser_password()) + "'and u.user_type='" + user.getUser_type() + "'").list().size() != 0;
			System.out.println(security.md5(user.getUser_password()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (validcredentialexits) {
			msg = (User) session.createQuery("from lmuser u where u.user_email='"
					+ user.getUser_name() + "' and u.user_password='" + security.md5(user.getUser_password()) + "'and u.user_type='" + user.getUser_type() + "'").getSingleResult();
			

		} else {
			msg = null;
		}
		return msg;
	}

	
	
	
	
	@Override
	public String validateusername(String user_email, String user_fullname) {
		Session session = sessionFactory.getCurrentSession();
		Boolean b = false;
		try {
			b = session
					.createQuery("select 1 from lmuser u where u.user_email='"
							+ user_email + "'")
					.list().size() == 0;
			System.out.println(b.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		// TODO Auto-generated method stub
		return b.toString();
	}

	@Override
	public String passwordreset(String student_email, String resetpasswordtoken, String student_password) {
		Session session = sessionFactory.getCurrentSession();
		if (session.createQuery("select 1 from lmuser sm where sm.resetpasswordtoken='" + resetpasswordtoken
				+ "'and sm.user_email='" + student_email + "'").list().size() == 1) {
			session.createQuery("update lmuser sm set sm.resetpasswordtoken= '" + null + "',sm.user_password='"
					+ security.md5(student_password) + "' where sm.user_email='" + student_email + "'").executeUpdate();
			return "Password Reset Successful!";
		} else {
			return "Password Reset UnSuccessful!";
		}
	}

	@Transactional
	@Override
	public User updateuser(User user) {
		Session session=sessionFactory.getCurrentSession();
		User usr=new User();
		usr=(User)session.load(User.class,user.getUser_id());
		//usr.setUser_fullname(user.getUser_fullname());
		usr.setUser_dob(user.getUser_dob());
		usr.setUser_phone(user.getUser_phone());
		//usr.setUser_email(user.getUser_email());
		usr.setUser_address(user.getUser_address());
		usr.setUser_state(user.getUser_state());
		usr.setUser_city(user.getUser_city());
		usr.setUser_gender(user.getUser_gender());
		usr.setUser_semester(user.getUser_semester());
		session.update(usr);
		return usr;
	}

	@Transactional
	@Override
	public String updatepassword(String current_user_password, String user_password, long user_id) {
		Session session1=sessionFactory.getCurrentSession();
		User usr=new User();
		usr=(User)session1.load(User.class,user_id);
	
		if(user_password.equals(current_user_password))
		{
			return "new password can not be old password";
		}
		else if(usr.getUser_password().equals(security.md5(current_user_password)))
		{
			
		
			usr.setUser_password(security.md5(user_password));
			session1.update(usr);

			return "password update success";
			
		}
		else {
			return "current password is wrong";
		}
		
	}

	

}
