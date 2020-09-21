package com.LM.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "lmuser")
public class User implements Comparable<User>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;
	private String user_name;
	private String user_fullname;
	private String user_password;
	private String user_phone;
	private String user_email;
	private String user_gender;
	private String user_dob;
	
	
	private String user_class;
	private String user_semester;
	
	private String user_state;
	private String user_city;
	private String user_pincode;
	private String user_address;
	
	private String user_type;
	
	private boolean user_isemailverified;
	
	private boolean user_isservicesenabled;
	
	private String user_registered;
	
	private String resetpasswordtoken;
	
	
	
	
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_dob() {
		return user_dob;
	}
	public void setUser_dob(String user_dob) {
		this.user_dob = user_dob;
	}
	public String getResetpasswordtoken() {
		return resetpasswordtoken;
	}
	public void setResetpasswordtoken(String resetpasswordtoken) {
		this.resetpasswordtoken = resetpasswordtoken;
	}
	public String getUser_registered() {
		return user_registered;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public String getUser_pincode() {
		return user_pincode;
	}
	public void setUser_pincode(String user_pincode) {
		this.user_pincode = user_pincode;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	
	
	
	public boolean isUser_isemailverified() {
		return user_isemailverified;
	}
	public void setUser_isemailverified(boolean user_isemailverified) {
		this.user_isemailverified = user_isemailverified;
	}
	public boolean isUser_isservicesenabled() {
		return user_isservicesenabled;
	}
	public void setUser_isservicesenabled(boolean user_isservicesenabled) {
		this.user_isservicesenabled = user_isservicesenabled;
	}
	public String isUser_registered() {
		return user_registered;
	}
	public void setUser_registered(String user_registered) {
		this.user_registered = user_registered;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_fullname() {
		return user_fullname;
	}
	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_class() {
		return user_class;
	}
	public void setUser_class(String user_class) {
		this.user_class = user_class;
	}
	public String getUser_semester() {
		return user_semester;
	}
	public void setUser_semester(String user_semester) {
		this.user_semester = user_semester;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	@Override
	public int compareTo(User arg0) {
		// TODO Auto-generated method stub
		return (int)arg0.getUser_id();
	}
	
	
	
	

}
