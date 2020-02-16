package user.model.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


public class User implements Serializable, HttpSessionBindingListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String nickName;
	private String password;
	private String email;
	private String phone;
	private Date enrollDate;
	private String userRole;
	private String address;
	private Date preLogDate;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String userName, String nickName, String password, String email, String phone,
			Date enrollDate, String userRole, String address, Date preLogDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.nickName = nickName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.enrollDate = enrollDate;
		this.userRole = userRole;
		this.address = address;
		this.preLogDate = preLogDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", nickName=" + nickName + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", enrollDate=" + enrollDate + ", userRole=" + userRole
				+ ", address=" + address + ", preLogDate=" + preLogDate + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getPreLogDate() {
		return preLogDate;
	}

	public void setPreLogDate(Date preLogDate) {
		this.preLogDate = preLogDate;
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println(userName+"["+userId+"]님이 로그인했습니다.");
	}

	/**
	 * session 속성에 등록된 Member객체가 해제될 때 호출됨.
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println(userName+"["+userId+"]님이 로그아웃했습니다.");
	}
	
	
	
}
